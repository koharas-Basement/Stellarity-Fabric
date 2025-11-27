package xyz.kohara.stellarity.recipe;


import com.mojang.serialization.codecs.ListCodec;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityRecipeSerializers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import net.minecraft.network.FriendlyByteBuf;

//? < 1.21 {
/*import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

*///? } else {

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.core.HolderLookup;
//? }


public record AltarSimpleRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
                                ItemStack result) implements AltarRecipe {

  public AltarSimpleRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
                           ItemStack result) {
    this.id = id;
    this.ingredients = ingredients;
    this.result = result;

    HashSet<Item> items = new HashSet<>();
    for (var entry : ingredients.keySet()) {
      for (ItemStack stack : entry.getItems()) {
        if (!items.add(stack.getItem())) {
          Stellarity.LOGGER.error("Ingredients are overlapping and may not work correctly. Altar Recipe ID: {}", id);
        }
      }
    }
  }

  public HashMap<ItemStack, Integer> recipeRemainder(List<ItemStack> itemStacks) {
    HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
    HashMap<ItemStack, Integer> available = new HashMap<>();

    for (var itemStack : itemStacks) {
      int availableCount = itemStack.getCount();

      available.put(itemStack, itemStack.getCount());

      boolean exists = false;

      for (var requirement : ingredients.keySet()) {
        Integer requiredCount = required.get(requirement);

        if (!requirement.test(itemStack)) continue;

        exists = true;
        if (availableCount == requiredCount) {
          required.remove(requirement);
          available.remove(itemStack);
          continue;
        }

        if (availableCount > requiredCount) {
          available.put(itemStack, availableCount - requiredCount);
          required.remove(requirement);
          continue;
        }

        required.put(requirement, requiredCount - availableCount);
      }

      if (!exists) return null;
    }

    if (!required.isEmpty()) return null;

    return available;

  }

  //? < 1.21
  //@Override
  public ResourceLocation getId() {
    return id;
  }

  @Override
  public @NotNull RecipeSerializer<?> getSerializer() {
    return StellarityRecipeSerializers.ALTAR_SIMPLE;
  }

  public static class Serializer implements RecipeSerializer<AltarSimpleRecipe> {
    //? < 1.21 {
    /*@Override
    public AltarSimpleRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
      HashMap<Ingredient, Integer> ingredients = ingredientsFromJson(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
      ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

      return new AltarSimpleRecipe(resourceLocation, ingredients, itemStack);
    }

    private static HashMap<Ingredient, Integer> ingredientsFromJson(JsonArray jsonArray) {
      if (jsonArray.isEmpty()) {
        throw new JsonParseException("No ingredients for altar recipe");
      }
      HashMap<Ingredient, Integer> ingredients = new HashMap<>();

      for (int i = 0; i < jsonArray.size(); ++i) {
        JsonObject entry = jsonArray.get(i).getAsJsonObject();

        int count = 1;
        if (entry.has("count")) count = entry.get("count").getAsInt();
        Ingredient ingredient = Ingredient.fromJson(entry.get("ingredient"));
        ingredients.put(ingredient, count);
      }

      return ingredients;
    }

    @Override
    public AltarSimpleRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
      int size = buf.readInt();
      HashMap<Ingredient, Integer> ingredients = new HashMap<>();
      for (int i = 0; i < size; i++) {
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        int count = buf.readInt();
        ingredients.put(ingredient, count);
      }
      ItemStack itemStack = buf.readItem();

      return new AltarSimpleRecipe(resourceLocation, ingredients, itemStack);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, AltarSimpleRecipe recipe) {
      buf.writeInt(recipe.ingredients.size());
      for (var entry : recipe.ingredients.entrySet()) {
        entry.getKey().toNetwork(buf);
        buf.writeInt(entry.getValue());
      }

      buf.writeItem(recipe.result);

    }

    *///? } else {
    private static class IngredientCount {
      
    }
    private static final ListCodec

    private static final MapCodec<AltarSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(
      recipe -> recipe.group(ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::result)
      ).apply(recipe, (result) -> {

        return new AltarSimpleRecipe(null, new HashMap<>(), result);
      }));

    @Override
    public @NotNull MapCodec<AltarSimpleRecipe> codec() {
      return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> streamCodec() {
      return null;
    }

    //? }
  }

  //? < 1.21 {

  //? } else {

  @Override
  public @NotNull ItemStack assemble(Input recipeInput, HolderLookup.Provider provider) {
    return getResultItem(provider);
  }

  @Override
  public @NotNull ItemStack getResultItem(HolderLookup.Provider provider) {
    return result.copy();
  }

  //? }
}
