package xyz.kohara.stellarity.interface_injection;

import net.minecraft.world.item.ItemStack;

import java.util.HashMap;

public interface ExtItemEntity {
  enum ItemMode {
    CRAFTING,
    PICKUP,
    RESULT
  }

  default ItemMode stellarity$getItemMode() {
    throw new AssertionError("Not transformed!");
  }

  default void stellarity$setItemMode(ItemMode mode) {
    throw new AssertionError("Not transformed!");
  }

  default void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
    throw new AssertionError("Not transformed!");
  }
}
