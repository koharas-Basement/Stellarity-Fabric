package xyz.kohara.stellarity.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.StellarityRecipeTypes;

import java.util.HashMap;
import java.util.List;

//? > 1.21 {
/*import net.minecraft.world.item.crafting.RecipeInput;
*///? }

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

  ItemStack result();

  //? < 1.21 {
  @Override
  default ItemStack getResultItem(RegistryAccess registryAccess) {
    return result();
  }




  @Override
  default ItemStack assemble(Input container, RegistryAccess registryAccess) {
    return null;
  }


  //? } else {


  //? }

  @Override
  default boolean matches(Input container, Level level) {
    return craft(container.items) == null;
  }
  
  @Override
  default boolean canCraftInDimensions(int i, int j) {
    return true;
  }

  @Override
  default @NotNull RecipeType<?> getType() {
    return StellarityRecipeTypes.ALTAR_RECIPE;
  }
}
