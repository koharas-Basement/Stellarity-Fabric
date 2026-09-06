package dev.coder2195.stellarity.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.coder2195.stellarity.registry.StellarityRecipeBookCategories;
import dev.coder2195.stellarity.registry.StellarityRecipeSerializers;
import dev.coder2195.stellarity.registry.StellarityRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record ConsecrationRecipe(Ingredient item, ItemStackTemplate result) implements Recipe<ConsecrationRecipe.Input>{
	public static final MapCodec<ConsecrationRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Ingredient.CODEC.fieldOf("item").forGetter(ConsecrationRecipe::item),
		ItemStackTemplate.CODEC.fieldOf("result").forGetter(ConsecrationRecipe::result)
	).apply(i, ConsecrationRecipe::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, ConsecrationRecipe> STREAM_CODEC = StreamCodec.composite(
		Ingredient.CONTENTS_STREAM_CODEC, ConsecrationRecipe::item,
		ItemStackTemplate.STREAM_CODEC, ConsecrationRecipe::result,
		ConsecrationRecipe::new
	);

	public record Input(ItemStack itemStack) implements RecipeInput {

		@Override
		public ItemStack getItem(int index) {
			return itemStack;
		}

		@Override
		public int size() {
			return 1;
		}
	}

	@Override
	public String group() {
		return "";
	}

	@Override
	public RecipeSerializer<? extends Recipe<Input>> getSerializer() {
		return StellarityRecipeSerializers.CONSECRATION;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return StellarityRecipeBookCategories.CONSECRATION;
	}

	@Override
	public boolean showNotification() {
		return true;
	}

	@Override
	public boolean matches(Input input, Level level) {
		return item.test(input.itemStack);
	}

	@Override
	public ItemStack assemble(Input input) {
		var itemStack = input.itemStack;

		return result.apply(itemStack.count(), itemStack.getComponentsPatch());
	}

	@Override
	public RecipeType<? extends Recipe<Input>> getType() {
		return StellarityRecipeTypes.CONSECRATION;
	}

	@Override
	public PlacementInfo placementInfo() {
		return PlacementInfo.NOT_PLACEABLE;
	}

}
