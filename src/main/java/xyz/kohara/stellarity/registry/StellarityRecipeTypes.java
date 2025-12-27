package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.recipe.AltarRecipe;

public class StellarityRecipeTypes {
	public static final RecipeType<AltarRecipe> ALTAR_RECIPE = register("altar_of_the_accursed");


	private static <T extends Recipe<?>> RecipeType<T> register(final String id) {
		final var path = Stellarity.id(id);
		final var string = path.toString();
		return Registry.register(BuiltInRegistries.RECIPE_TYPE, path, new RecipeType<T>() {
			public String toString() {
				return string;
			}
		});
	}


	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Recipe Types");
	}
}
