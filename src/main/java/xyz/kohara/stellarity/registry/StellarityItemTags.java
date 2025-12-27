package xyz.kohara.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;

public class StellarityItemTags {
  public static final TagKey<Item> FISHES = bind("fishes");


  private static TagKey<Item> bind(String id) {
    return TagKey.create(Registries.ITEM, Stellarity.id(id));
  }

}
