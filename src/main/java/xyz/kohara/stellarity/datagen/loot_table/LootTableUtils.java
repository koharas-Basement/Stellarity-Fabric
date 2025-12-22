package xyz.kohara.stellarity.datagen.loot_table;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

class LootTableUtils {
  public static ConstantValue num(float num) {
    return /*? 1.20.1 { */ ConstantValue.exactly(num) /*? } else { *//*new ConstantValue(num) *//*? } */;
  }

  public static UniformGenerator range(float min, float max) {
    return  /*? 1.20.1 { */ UniformGenerator.between(min, max) /*? } else { *//*new UniformGenerator(num(min), num(max)) *//*? } */;
  }

  public static LootPoolSingletonContainer.Builder<?> item(ItemLike i) {
    return LootItem.lootTableItem(i);
  }

  public static LootItemConditionalFunction.Builder<?> count(NumberProvider provider) {
    return SetItemCountFunction.setCount(provider);
  }
}
