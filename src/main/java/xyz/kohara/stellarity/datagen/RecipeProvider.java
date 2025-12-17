package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.recipe.AltarRecipe;
import xyz.kohara.stellarity.recipe.AltarSimpleRecipe;

import java.util.HashMap;


//? < 1.21 {
import net.minecraft.data.recipes.FinishedRecipe;
import java.util.function.Consumer;
//? } else {
/*import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import net.minecraft.data.recipes.RecipeOutput;
*///? }

public class RecipeProvider extends FabricRecipeProvider {
  //? < 1.21 {
  public RecipeProvider(FabricDataOutput output) {
    super(output);
  }
  //? } else {
  /*public RecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
    super(output, registriesFuture);
  }
  *///? }

  //? < 1.21 {
  public static void altarOfTheAccursed(Consumer<FinishedRecipe> output, AltarRecipe recipe) {
    output.accept(recipe.finished());
  }
  //? } else {
  /*public static void altarOfTheAccursed(RecipeOutput output, AltarRecipe recipe) {
    output.accept(
      //? = 1.21.1
      //recipe.id(),
      //? > 1.21.9
      //ResourceKey.create(Registries.RECIPE, recipe.id()),
      recipe, null);
  }
  *///? }

  //? > 1.21.9 {
  /*@Override
  public net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
    return new net.minecraft.data.recipes.RecipeProvider(provider, recipeOutput) {
      @Override
      public void buildRecipes() {
        RecipeProvider.this.buildRecipes(provider, output);
      }
    };
  }
  *///? }

  //? = 1.20.1 {
  @Override
  public void buildRecipes(Consumer<FinishedRecipe> output) {
  //? } = 1.21.1 {
  /*@Override
  public void buildRecipes(RecipeOutput output) {
    *///? } else {
  /*public void buildRecipes(HolderLookup.Provider provider, RecipeOutput output) {
    *///? }
    altarOfTheAccursed(output, new AltarSimpleRecipe(
      Stellarity.id("altar_of_the_accursed/lapis_to_amethyst"),
      new HashMap<>() {{
        put(Ingredient.of(Items.DIAMOND), 1);
        put(Ingredient.of(Items.LAPIS_LAZULI), 1);
      }},
      new ItemStack(Items.AMETHYST_SHARD)
    ));
  }

  @Override
  public String getName() {
    return "stellarity";
  }
}
