package xyz.kohara.stellarity.recipe;


import net.minecraft.core.HolderLookup;

//? <= 1.21.10 {
import net.minecraft.resources.ResourceLocation;
 //? } else {
/*import net.minecraft.resources.Identifier;
*///? }
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.StellarityRecipeTypes;
//? 1.20.1 {
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
//? }


import java.util.HashMap;
import java.util.List;

//? > 1.21 {

//? } else {
import net.minecraft.data.recipes.FinishedRecipe;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
//? }

public interface AltarRecipe extends Recipe<AltarRecipe.Input> {
  class Input extends SimpleContainer
    //? < 1.21 {
    {
     //? } else {
    /*implements RecipeInput {
    @Override
      *///? }
    public int size() {
      return this.items.size();
    }
  }

  record Output(HashMap<ItemStack, Integer> remainders, ItemStack result) {
  }

  @Nullable Output craft(List<ItemStack> itemStacks);

  HashMap<Ingredient, Integer> ingredients();

  ItemStack result();

  /*? <1.21.11 {*/ ResourceLocation/*?} else {*//*Identifier *//*? }*/ id();

  //? 1.20.1 {
  default void toJson(JsonObject jsonObject) {
    var entrySet = this.ingredients().entrySet();
    JsonArray ingredientsArray = new JsonArray();
    for (var entry : entrySet) {
      JsonObject ingredient = new JsonObject();
      ingredient.add("ingredient", entry.getKey().toJson());
      var count = entry.getValue();
      if (count > 1) ingredient.addProperty("count", count);

      ingredientsArray.add(ingredient);
    }

    jsonObject.add("ingredients", ingredientsArray);
    JsonObject resultObj = new JsonObject();
    var result = this.result();
    resultObj.addProperty("item", BuiltInRegistries.ITEM.getKey(result.getItem()).toString());
    int count = result.getCount();
    if (count > 1) {
      resultObj.addProperty("count", count);
    }

    jsonObject.add("result", resultObj);
  }

  record Finished(AltarRecipe recipe) implements FinishedRecipe {

    @Override
    public void serializeRecipeData(JsonObject jsonObject) {
      recipe.toJson(jsonObject);
    }

    @Override
    public ResourceLocation getId() {
      return recipe.id();
    }

    @Override
    public RecipeSerializer<?> getType() {
      return recipe.getSerializer();
    }

    @Override
    public @Nullable JsonObject serializeAdvancement() {
      return null;
    }

    @Override
    public @Nullable ResourceLocation getAdvancementId() {
      return null;
    }
  }

  default Finished finished() {
    return new Finished(this);
  }

  @Override
  default ItemStack getResultItem(RegistryAccess registryAccess) {
    return result();
  }

  @Override
  default ItemStack assemble(Input container, RegistryAccess registryAccess) {
    return null;
  }

  @Override
  default ResourceLocation getId() {
    return id();
  }


  //? } else {
  /*//? = 1.21.1
  //@Override
  default @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
    return result().copy();
  }

  *///? }

  //? > 1.21.9 {
  /*@Override
  default @NotNull PlacementInfo placementInfo() {
    return PlacementInfo.NOT_PLACEABLE;
  }

  ;

  @Override
  default @NotNull RecipeBookCategory recipeBookCategory() {
    return RecipeBookCategories.CRAFTING_MISC;
  }

  *///? } else {
  @Override
  default boolean canCraftInDimensions(int i, int j) {
    return true;
  }

  //? }


  @Override
  default @NotNull RecipeType<? extends Recipe<Input>> getType() {
    return StellarityRecipeTypes.ALTAR_RECIPE;
  }


  @Override
  default boolean matches(Input container, Level level) {
    return craft(container.items) == null;
  }
}
