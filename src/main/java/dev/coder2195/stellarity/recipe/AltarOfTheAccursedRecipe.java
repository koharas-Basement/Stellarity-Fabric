package dev.coder2195.stellarity.recipe;


import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.registry.StellarityCriteriaTriggers;
import dev.coder2195.stellarity.registry.StellarityRecipeBookCategories;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import dev.coder2195.stellarity.registry.StellaritySoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public interface AltarOfTheAccursedRecipe extends Recipe<AltarOfTheAccursedRecipe.Input> {
	class Input extends SimpleContainer implements RecipeInput {
		@Override
		public int size() {
			return this.items.size();
		}
	}

	record Output(HashMap<ItemStack, Integer> remainders, ItemStack... result) {
		public Output(HashMap<ItemStack, Integer> remainders, ItemStack result) {
			this(remainders, new ItemStack[]{result});
		}
	}

	@Nullable Output craft(List<ItemStack> itemStacks);

	@Override
	default PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

	@Override
	default RecipeBookCategory recipeBookCategory() {
		return StellarityRecipeBookCategories.ALTAR_OF_THE_ACCURSED;
	}

	@Override
	default RecipeType<? extends Recipe<Input>> getType() {
		return StellarityRecipeTypes.ALTAR_OF_THE_ACCURSED;
	}


	@Override
	default boolean matches(Input container, Level level) {
		return craft(container.items) == null;
	}


	static void handleItems(ServerLevel serverLevel, double x, double y, double z, boolean locked) {
		handleItems(serverLevel, x, y, z, locked, new AABB(
			x - 0.5, y + 0.75d - 0.5, z - 0.5,
			x + 0.5, y + 0.75d + 0.5, z + 0.5
		), (_) -> true);
	}

	static void handleItems(ServerLevel serverLevel, double x, double y, double z, boolean locked, AABB bounding, Predicate<ItemEntity> predicate) {

		List<ItemEntity> itemEntities = serverLevel.getEntitiesOfClass(ItemEntity.class, bounding, entity -> entity.stellarity$getItemMode() != ExtItemEntity.ItemMode.RESULT && predicate.test(entity));

		Player player = serverLevel.getNearestPlayer(x, y, z, 10, false);

		if (locked) {
			if (!itemEntities.isEmpty() && player instanceof ServerPlayer serverPlayer) {
				serverPlayer.connection.send(
					new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.altar_of_the_accursed_locked").withStyle(ChatFormatting.DARK_PURPLE))
				);
			}
			return;
		}

		List<ItemStack> itemStacks = itemEntities.stream().map(ItemEntity::getItem).toList();
		ExtItemEntity.ItemMode itemMode = player != null && player.isCrouching() ? ExtItemEntity.ItemMode.DEFAULT : ExtItemEntity.ItemMode.ALTAR_CRAFTING;

		for (var entity : itemEntities) {
			if (!entity.stellarity$getItemMode().equals(itemMode)) entity.stellarity$setItemMode(itemMode);
		}
		if (itemEntities.isEmpty()) return;

		AltarOfTheAccursedRecipe.Output output = null;

		if (itemMode == ExtItemEntity.ItemMode.ALTAR_CRAFTING) {
			var allRecipes = serverLevel.getServer().getRecipeManager().getAllOfType(StellarityRecipeTypes.ALTAR_OF_THE_ACCURSED);

			for (var recipeHolder : allRecipes) {
				var recipe = recipeHolder.value();

				output = recipe.craft(itemStacks);
				if (output != null) {
					break;
				}
			}
		}

		if (output == null) return;

		var remainders= output.remainders();
		for (var itemStack: itemStacks) {
			var remaining = remainders.getOrDefault(itemStack, 0);
			var craftingRemainder = itemStack.getCraftingRemainder();
			if (craftingRemainder == null) continue;
			int toGive = itemStack.getCount() - remaining;
			if (toGive <= 0) continue;

			ItemEntity remainderItem = new ItemEntity(serverLevel, x, y + 0.75, z, craftingRemainder.apply(toGive, DataComponentPatch.builder().build()));
			remainderItem.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT);
			serverLevel.addFreshEntity(remainderItem);
		}

		for (var entity : itemEntities) {
			entity.stellarity$updateResults(remainders);
		}

		var stacks = output.result();
		for (var stack : stacks) {
			ItemEntity resultItem = new ItemEntity(serverLevel, x, y + 0.75, z, stack);
			resultItem.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT);
			serverLevel.addFreshEntity(resultItem);

			serverLevel.sendParticles(ColorParticleOption.create(ParticleTypes.FLASH, -1), x, y + 1, z, 1, 0, 0, 0, 0);
			serverLevel.sendParticles(ParticleTypes.END_ROD, x, y + 1, z, 17, 0, 0, 0, 0.13);
			serverLevel.playSound(null, x, y, z, StellaritySoundEvents.ALTAR_OF_THE_ACCURSED_CRAFT, SoundSource.BLOCKS);

			serverLevel.getEntitiesOfClass(ServerPlayer.class, new AABB(x - 5, y - 5, z - 5, x + 5, y + 5, z + 5)).forEach(p -> StellarityCriteriaTriggers.SPECIAL_CRAFT.trigger(p, BlockPos.containing(x, y, z), stack));
		}
	}


	@Override
	default boolean showNotification() {
		return true;
	}


	@Override
	default ItemStack assemble(Input recipeInput) {
		// stupid mojang recipes
		return ItemStack.EMPTY;
	}

	@Override
	default String group() {
		return "";
	}
}
