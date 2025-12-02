package xyz.kohara.stellarity;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;

//? 1.20.1 {
import net.minecraft.world.item.alchemy.PotionUtils;
//?} else {
/*import net.minecraft.world.item.alchemy.PotionContents;
*///?}

import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;

public class StellarityCreativeModeTabs {
  public static final Item[] BLOCKS_ITEMS = new Item[]{
    StellarityItems.ASHEN_FROGLIGHT,
    StellarityItems.ENDER_DIRT,
    StellarityItems.ENDER_GRASS_BLOCK,
    StellarityItems.ROOTED_ENDER_DIRT,
    StellarityItems.ENDER_DIRT_PATH,
    StellarityItems.ALTAR_OF_THE_ACCURSED,
    StellarityItems.PHANTOM_ITEM_FRAME
  };

  public static final Item[] FOOD_ITEMS = new Item[]{
    StellarityItems.CRYSTAL_HEARTFISH,
    StellarityItems.SUSHI,
    StellarityItems.GOLDEN_CHORUS_FRUIT,
    StellarityItems.FRIED_CHORUS_FRUIT,
    StellarityItems.FROZEN_CARPACCIO,
    StellarityItems.ENDERMAN_FLESH,
    StellarityItems.GRILLED_ENDERMAN_FLESH,
    StellarityItems.FLAREFIN_KOI,
    StellarityItems.AMETHYST_BUDFISH,
    StellarityItems.CRIMSON_TIGERFISH,
    StellarityItems.ENDER_KOI,
    StellarityItems.FLESHY_PIRANHA,
    StellarityItems.BUBBLEFISH,
    StellarityItems.PRISMITE,
    StellarityItems.OVERGROWN_COD,
    StellarityItems.SHULKER_BODY,
    StellarityItems.PRISMATIC_SUSHI,
    StellarityItems.SHEPHERDS_PIE,
    StellarityItems.CHORUS_PIE,
    StellarityItems.PHO
  };

  public static final Item[] EQUIPMENT_ITEMS = new Item[]{
    StellarityItems.CALL_OF_THE_VOID,
    StellarityItems.FISHER_OF_VOIDS
  };

  public static final ResourceKey<CreativeModeTab> FOOD_KEY = ResourceKey.create(CREATIVE_MODE_TAB.key(), Stellarity.of("food"));
  public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = ResourceKey.create(CREATIVE_MODE_TAB.key(), Stellarity.of("building_blocks"));
  public static final ResourceKey<CreativeModeTab> EQUIPMENT_KEY = ResourceKey.create(CREATIVE_MODE_TAB.key(), Stellarity.of("equipment"));

  public static final CreativeModeTab FOOD = FabricItemGroup.builder()
    .icon(() -> new ItemStack(StellarityItems.SUSHI))
    .title(Component.translatable("itemGroup.stellarity.food"))
    .build();
  public static final CreativeModeTab BLOCKS = FabricItemGroup.builder()
    .icon(() -> new ItemStack(StellarityItems.ENDER_GRASS_BLOCK))
    .title(Component.translatable("itemGroup.stellarity.blocks"))
    .build();
  public static final CreativeModeTab EQUIPMENT = FabricItemGroup.builder()
    .icon(() -> new ItemStack(StellarityItems.CALL_OF_THE_VOID))
    .title(Component.translatable("itemGroup.stellarity.equipment"))
    .build();

  public static void init() {
    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, FOOD_KEY, FOOD);
    ItemGroupEvents.modifyEntriesEvent(FOOD_KEY).register(itemGroup -> {
      for (Item item : FOOD_ITEMS) {
        itemGroup.accept(item);
      }

      itemGroup.accept(
        //? 1.20.1 {
        PotionUtils.setPotion(new ItemStack(Items.POTION), StellarityPotions.BLIND_RAGE)
        //?} else {
        /*PotionContents.createItemStack(Items.POTION, StellarityPotions.BLIND_RAGE_HOLDER)
        *///?}
      );
    });

    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BLOCKS_KEY, BLOCKS);
    ItemGroupEvents.modifyEntriesEvent(BLOCKS_KEY).register(itemGroup -> {
      for (Item item : BLOCKS_ITEMS) {
        itemGroup.accept(item);
      }
    });

    Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EQUIPMENT_KEY, EQUIPMENT);
    ItemGroupEvents.modifyEntriesEvent(EQUIPMENT_KEY).register(itemGroup -> {
      for (Item item : EQUIPMENT_ITEMS) {
        itemGroup.accept(item);
      }
    });


    Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

  }

}
