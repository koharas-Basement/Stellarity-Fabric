package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import xyz.kohara.stellarity.recipe.AltarRecipe;

public class StellarityRecipes {
  public static RecipeType<AltarRecipe> ALTAR_RECIPE_TYPE = registerType("altar_of_the_accursed");
  public static RecipeSerializer<AltarRecipe> ALTAR_RECIPE_SERIALIZER = registerSerializer("altar_of_the_accursed", new AltarRecipe.Serializer());

  private static <T extends Recipe<?>> RecipeType<T> registerType(final String id) {
    final var path = Stellarity.of(id);
    final var string = path.toString();
    return Registry.register(BuiltInRegistries.RECIPE_TYPE, path, new RecipeType<T>() {
      public String toString() {
        return string;
      }
    });
  }

  private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(final String id, RecipeSerializer<T> serializer) {
    return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER,  Stellarity.of(id), serializer);
  }


  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Recipe Types and Serializers");
  }
}
