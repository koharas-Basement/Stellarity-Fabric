package xyz.kohara.stellarity.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityRecipeTypes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public record AltarRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
                          ItemStack result) implements Recipe<SimpleContainer> {
  public AltarRecipe(ResourceLocation id, HashMap<Ingredient, Integer> ingredients,
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

  public HashMap<ItemStack,Integer> recipeRemainder(List<ItemStack> itemStacks) {
    HashMap<Ingredient, Integer> required = new HashMap<>(ingredients);
    HashMap<ItemStack, Integer> available = new HashMap<>();
    for (var itemStack : itemStacks) {
      available.put(itemStack, itemStack.getCount());
    }

    for (var availableEntry : available.entrySet()) {
      ItemStack itemStack = availableEntry.getKey();
      int availableCount = availableEntry.getValue();

      boolean exists = false;

      for (var requiredEntry: required.entrySet()) {
        Ingredient requirement = requiredEntry.getKey();
        int requiredCount = requiredEntry.getValue();

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

  @Override
  public boolean matches(SimpleContainer container, Level level) {
    return recipeRemainder(container.items) == null;
  }

  @Override
  public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
    return null;
  }

  @Override
  public boolean canCraftInDimensions(int i, int j) {
    return true;
  }

  @Override
  public ItemStack getResultItem(RegistryAccess registryAccess) {
    return null;
  }

  @Override
  public ResourceLocation getId() {
    return null;
  }

  @Override
  public RecipeSerializer<?> getSerializer() {
    return null;
  }

  @Override
  public RecipeType<?> getType() {
    return StellarityRecipeTypes.ALTAR_RECIPE;
  }

  public static class Serializer implements RecipeSerializer<AltarRecipe> {

    @Override
    public AltarRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
      HashMap<Ingredient, Integer> ingredients = ingredientsFromJson(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
      ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));

      return new AltarRecipe(resourceLocation, ingredients, itemStack);
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
        Ingredient ingredient = Ingredient.fromJson(entry);
        ingredients.put(ingredient, count);
      }

      return ingredients;
    }

    @Override
    public AltarRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
      int size = buf.readInt();
      HashMap<Ingredient, Integer> ingredients = new HashMap<>();
      for (int i = 0; i < size; i++) {
        Ingredient ingredient = Ingredient.fromNetwork(buf);
        int count = buf.readInt();
        ingredients.put(ingredient, count);
      }
      ItemStack itemStack = buf.readItem();

      return new AltarRecipe(resourceLocation, ingredients, itemStack);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, AltarRecipe recipe) {
      buf.writeInt(recipe.ingredients.size());
      for (var entry : recipe.ingredients.entrySet()) {
        entry.getKey().toNetwork(buf);
        buf.writeInt(entry.getValue());
      }

      buf.writeItem(recipe.result);

    }
  }
}
