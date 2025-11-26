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
    return null;
  }
  default void stellarity$setItemMode(ItemMode mode) {
  }

  default void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
  }
}
