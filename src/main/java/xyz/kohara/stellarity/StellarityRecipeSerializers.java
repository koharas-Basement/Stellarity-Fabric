package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import xyz.kohara.stellarity.recipe.AltarSimpleRecipe;

public class StellarityRecipeSerializers {
  public static final RecipeSerializer<AltarSimpleRecipe> ALTAR_SIMPLE = registerSerializer("altar_of_the_accursed_simple", new AltarSimpleRecipe.Serializer());

  private static <T extends Recipe<?>> RecipeSerializer<T> registerSerializer(final String id, RecipeSerializer<T> serializer) {
    return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, Stellarity.id(id), serializer);
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Recipe Serializers");
  }
}
