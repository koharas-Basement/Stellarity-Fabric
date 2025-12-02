package xyz.kohara.stellarity.recipe;



import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityRecipeSerializers;

import java.util.*;



//? < 1.21 {
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import net.minecraft.network.FriendlyByteBuf;
//? } else {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.core.HolderLookup;
*///? }

//? < 1.21.9 {
import net.minecraft.world.item.Item;
//? }

public record AltarSimpleRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
                                ItemStack result) implements AltarRecipe {

  public AltarSimpleRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
                           ItemStack result) {
    this.id = id;
    this.ingredients = ingredients;
    this.result = result;

    //? < 1.21.9 {
    HashSet<Item> items = new HashSet<>();
    for (var entry : ingredients.keySet()) {
      for (ItemStack stack : entry.getItems()) {
        if (!items.add(stack.getItem())) {
          Stellarity.LOGGER.error("Ingredients are overlapping and may not work correctly. Altar Recipe ID: {}", id);
        }
      }
    }
    //? } else {
    /*Stellarity.LOGGER.info("For the sake of convience, recipe validation is skipped. Please confirm on older versions!");
    *///? }
  }

  public Output craft(List<ItemStack> itemStacks) {
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
        if (requiredCount == 0) break;
        if (availableCount == requiredCount) {
          required.put(requirement, 0);
          available.remove(itemStack);
          break;
        }

        if (availableCount > requiredCount) {
          required.put(requirement, 0);
          available.put(itemStack, availableCount - requiredCount);

          break;
        }

        required.put(requirement, requiredCount - availableCount);
      }

      if (!exists) return null;
    }

    for (var counts : required.values()) {
      if (counts > 0) return null;
    }

    return new Output(available, result.copy());

  }

  @Override
  public @NotNull RecipeSerializer<? extends Recipe<Input>> getSerializer() {
    return StellarityRecipeSerializers.ALTAR_SIMPLE;
  }

  public static class Serializer implements RecipeSerializer<AltarSimpleRecipe> {
    //? < 1.21 {
    @Override
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

    //? } else {
    /*private static final MapCodec<Map.Entry<Ingredient, Integer>> INGREDIENT_CODEC = RecordCodecBuilder.mapCodec(
      instance -> instance.group(
        Ingredient.CODEC.fieldOf("ingredient").forGetter(Map.Entry::getKey),
        Codec.INT.optionalFieldOf("count", 1).forGetter(Map.Entry::getValue)
      ).apply(instance, Map::entry)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

    public static final MapCodec<AltarSimpleRecipe> CODEC = RecordCodecBuilder.mapCodec(

      instance -> instance.group(
        INGREDIENT_CODEC.codec().listOf().fieldOf("ingredients").forGetter((recipe) ->
          recipe.ingredients.entrySet().stream().toList()
        ),
        ItemStack.CODEC.fieldOf("result").forGetter(AltarRecipe::result)

      ).apply(instance, (ingredients, result) -> {
        HashMap<Ingredient, Integer> ingredientMap = new HashMap<>();

        for (var ingredient : ingredients) {
          ingredientMap.put(ingredient.getKey(), ingredient.getValue());
        }
        return new AltarSimpleRecipe(null, ingredientMap, result);
      }));




    @Override
    public @NotNull MapCodec<AltarSimpleRecipe> codec() {
      return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, AltarSimpleRecipe> streamCodec() {
      return STREAM_CODEC;
    }

    private static AltarSimpleRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
      int size = buf.readInt();
      HashMap<Ingredient, Integer> ingredients = new HashMap<>();
      for (int i = 0; i < size; i++) {
        Ingredient ingredient = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
        int count = buf.readInt();
        ingredients.put(ingredient, count);
      }

      ItemStack itemStack = ItemStack.STREAM_CODEC.decode(buf);
      return new AltarSimpleRecipe(null, ingredients, itemStack);
    }

    private static void toNetwork(RegistryFriendlyByteBuf buf, AltarSimpleRecipe recipe) {
      buf.writeInt(recipe.ingredients.size());
      for (var entry : recipe.ingredients.entrySet()) {
        Ingredient.CONTENTS_STREAM_CODEC.encode(buf, entry.getKey());
        buf.writeInt(entry.getValue());
      }

      ItemStack.STREAM_CODEC.encode(buf, recipe.result);
    }
    *///? }
  }

  //? < 1.21 {
  //? } else {

  /*@Override
  public @NotNull ItemStack assemble(Input recipeInput, HolderLookup.Provider provider) {
    return getResultItem(provider);
  }

  *///? }
}
