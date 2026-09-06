package dev.coder2195.stellarity.mixin.crafting;

import dev.coder2195.stellarity.block.AltarOfTheAccursed;
import dev.coder2195.stellarity.entity.SatchelSigil;
import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import dev.coder2195.stellarity.recipe.ConsecrationRecipe;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import dev.coder2195.stellarity.tags.StellarityBiomeTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
	@Shadow
	public abstract ItemStack getItem();

	@Shadow
	public abstract void setItem(ItemStack itemStack);

	public ItemEntityMixin(EntityType<?> type, Level level) {
		super(type, level);
	}

	@Unique
	private int tickCounter = 0;

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	public void movedOffRecipeBlock(CallbackInfo ci) {
		if (!stellarity$getItemMode().equals(ExtItemEntity.ItemMode.ALTAR_CRAFTING)) return;
		if (!(level() instanceof ServerLevel level)) return;
		var position = this.position();
		for (var corner : List.of(
			position.add(0, -0.75, 0),
			position.add(0.5, -0.75, 0.5),
			position.add(0.5, -0.75, -0.5),
			position.add(-0.5, -0.75, -0.5),
			position.add(-0.5, -0.75, 0.5)

		)) {
			var blockstate = level.getBlockState(BlockPos.containing(corner));

			if (blockstate.is(StellarityBlocks.ALTAR_OF_THE_ACCURSED) && !blockstate.getValue(AltarOfTheAccursed.LOCKED))
				return;
		}

		if (level.getEntitiesOfClass(SatchelSigil.class, this.getBoundingBox()).stream().noneMatch(SatchelSigil::isActive))
			stellarity$setItemMode(ExtItemEntity.ItemMode.DEFAULT);

	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
	public void consecrationTick(CallbackInfo ci) {
		if (
			stellarity$getItemMode() != ItemMode.DEFAULT ||
			!isInWater() ||
				(tickCounter = (++tickCounter) % 5) != 0 ||
				!(level() instanceof ServerLevel level) ||
				!level.getBiome(blockPosition()).is(StellarityBiomeTags.ALLOWS_CONSECRATION)
		) return;

		var input = new ConsecrationRecipe.Input(getItem());
		var recipe = level.recipeAccess().getRecipeFor(StellarityRecipeTypes.CONSECRATION, input, level);
		if (recipe.isEmpty()) return;
		var position = position();

		setItem(recipe.get().value().assemble(input));
	}

}
