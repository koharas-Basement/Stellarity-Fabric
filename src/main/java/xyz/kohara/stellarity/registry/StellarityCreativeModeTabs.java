package xyz.kohara.stellarity.registry;

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
import net.minecraft.world.level.ItemLike;
import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {
import net.minecraft.world.item.alchemy.PotionUtils;

    //?} else {
/*import net.minecraft.world.item.alchemy.PotionContents;
 *///?}

import static net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB;

public class StellarityCreativeModeTabs {
    public static final ItemLike[] BLOCKS_ITEMS = new ItemLike[]{
        StellarityItems.ASHEN_FROGLIGHT,
        StellarityItems.ENDER_DIRT,
        StellarityItems.ENDER_GRASS_BLOCK,
        StellarityItems.ROOTED_ENDER_DIRT,
        StellarityItems.ENDER_DIRT_PATH,
        StellarityItems.ALTAR_OF_THE_ACCURSED,
        StellarityItems.PHANTOM_ITEM_FRAME
    };

    public static final ItemLike[] FOOD_ITEMS = new ItemLike[]{
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

    public static final ItemStack[] FOOD_ITEMSTACKS = new ItemStack[]{
        //? 1.20.1 {
        PotionUtils.setPotion(new ItemStack(Items.POTION), StellarityPotions.BLIND_RAGE)
        //?} else {
        /*PotionContents.createItemStack(Items.POTION, StellarityPotions.BLIND_RAGE_HOLDER)
         *///?}
    };

    public static final ItemLike[] EQUIPMENT_ITEMS = new ItemLike[]{
        StellarityItems.CALL_OF_THE_VOID,
        StellarityItems.FISHER_OF_VOIDS,
        StellarityItems.TAMARIS
    };

    public static final ItemLike[] INGREDIENT_ITEMS = new ItemLike[]{
        StellarityItems.CHORUS_PLATING,
        StellarityItems.ENDERITE_SHARD,
        StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE,
        StellarityItems.HALLOWED_INGOT,
        StellarityItems.SAND_RUNE,
        StellarityItems.STARLIGHT_SOOT,
        StellarityItems.GILDED_PURPUR_KEY,
        StellarityItems.PURPUR_KEY,
        StellarityItems.WINGED_KEY
    };

    public static final ItemLike[] TRINKET_ITEMS = new ItemLike[]{
        StellarityItems.PRISMATIC_PEARL,
        StellarityItems.ENDONOMICON
    };

    public static final ResourceKey<CreativeModeTab> FOOD_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "food");
    public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "building_blocks");
    public static final ResourceKey<CreativeModeTab> EQUIPMENT_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "equipment");
    public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "ingredients");
    public static final ResourceKey<CreativeModeTab> TRINKETS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "trinkets");

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

    public static final CreativeModeTab INGREDIENTS = FabricItemGroup.builder()
        .icon(() -> new ItemStack(StellarityItems.ENDERITE_SHARD))
        .title(Component.translatable("itemGroup.stellarity.ingredients"))
        .build();

    public static final CreativeModeTab TRINKETS = FabricItemGroup.builder()
        .icon(() -> new ItemStack(StellarityItems.PRISMATIC_PEARL))
        .title(Component.translatable("itemGroup.stellarity.trinkets"))
        .build();

    public static void init() {
        register(FOOD_KEY, FOOD, FOOD_ITEMS, FOOD_ITEMSTACKS);
        register(BLOCKS_KEY, BLOCKS, BLOCKS_ITEMS);
        register(EQUIPMENT_KEY, EQUIPMENT, EQUIPMENT_ITEMS);
        register(INGREDIENTS_KEY, INGREDIENTS, INGREDIENT_ITEMS);
        register(TRINKETS_KEY, TRINKETS, TRINKET_ITEMS);

        Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

    }

    public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items, ItemStack[] stacks) {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab);
        ItemGroupEvents.modifyEntriesEvent(key).register(itemGroup -> {
            for (ItemLike item : items) {
                itemGroup.accept(item);
            }

            for (ItemStack stack : stacks) {
                itemGroup.accept(stack);
            }
        });
    }

    public static void register(ResourceKey<CreativeModeTab> key, CreativeModeTab tab, ItemLike[] items) {
        register(key, tab, items, new ItemStack[]{});
    }

}
