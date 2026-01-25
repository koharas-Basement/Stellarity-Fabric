package xyz.kohara.stellarity.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {
import net.minecraft.world.item.alchemy.PotionUtils;
//?} else {
/*import net.minecraft.world.item.alchemy.PotionContents;
*///?}

import java.util.function.Supplier;

public class StellarityCreativeModeTabs {
    private static final Registrar<CreativeModeTab> CREATIVE_MODE_TABS = StellarityRegistries.MANAGER.get().get(Registries.CREATIVE_MODE_TAB);
    
    public static final Supplier<ItemLike[]> BLOCKS_ITEMS = () -> new ItemLike[]{
        StellarityItems.ASHEN_FROGLIGHT.get(),
        StellarityItems.ENDER_DIRT.get(),
        StellarityItems.ENDER_GRASS_BLOCK.get(),
        StellarityItems.ROOTED_ENDER_DIRT.get(),
        StellarityItems.ENDER_DIRT_PATH.get(),
        StellarityItems.ALTAR_OF_THE_ACCURSED.get(),
        StellarityItems.PHANTOM_ITEM_FRAME.get()
    };

    public static final Supplier<ItemLike[]> FOOD_ITEMS = () -> new ItemLike[]{
        StellarityItems.CRYSTAL_HEARTFISH.get(),
        StellarityItems.SUSHI.get(),
        StellarityItems.GOLDEN_CHORUS_FRUIT.get(),
        StellarityItems.FRIED_CHORUS_FRUIT.get(),
        StellarityItems.FROZEN_CARPACCIO.get(),
        StellarityItems.ENDERMAN_FLESH.get(),
        StellarityItems.GRILLED_ENDERMAN_FLESH.get(),
        StellarityItems.FLAREFIN_KOI.get(),
        StellarityItems.AMETHYST_BUDFISH.get(),
        StellarityItems.CRIMSON_TIGERFISH.get(),
        StellarityItems.ENDER_KOI.get(),
        StellarityItems.FLESHY_PIRANHA.get(),
        StellarityItems.BUBBLEFISH.get(),
        StellarityItems.PRISMITE.get(),
        StellarityItems.OVERGROWN_COD.get(),
        StellarityItems.SHULKER_BODY.get(),
        StellarityItems.PRISMATIC_SUSHI.get(),
        StellarityItems.SHEPHERDS_PIE.get(),
        StellarityItems.CHORUS_PIE.get(),
        StellarityItems.PHO.get()
    };

    public static final Supplier<ItemStack[]> FOOD_ITEMSTACKS = () -> new ItemStack[]{
        //? 1.20.1 {
        PotionUtils.setPotion(new ItemStack(Items.POTION), StellarityPotions.BLIND_RAGE.get())
        //?} else {
        /*PotionContents.createItemStack(Items.POTION, StellarityPotions.BLIND_RAGE_HOLDER)
         *///?}
    };

    public static final Supplier<ItemLike[]> EQUIPMENT_ITEMS = () -> new ItemLike[]{
        StellarityItems.CALL_OF_THE_VOID.get(),
        StellarityItems.FISHER_OF_VOIDS.get(),
        StellarityItems.TAMARIS.get()
    };

    public static final Supplier<ItemLike[]> INGREDIENT_ITEMS = () -> new ItemLike[]{
        StellarityItems.CHORUS_PLATING.get(),
        StellarityItems.ENDERITE_SHARD.get(),
        StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get(),
        StellarityItems.HALLOWED_INGOT.get(),
        StellarityItems.SAND_RUNE.get(),
        StellarityItems.STARLIGHT_SOOT.get(),
        StellarityItems.GILDED_PURPUR_KEY.get(),
        StellarityItems.PURPUR_KEY.get(),
        StellarityItems.WINGED_KEY.get()
    };

    public static final Supplier<ItemLike[]> TRINKET_ITEMS = () -> new ItemLike[]{
        StellarityItems.PRISMATIC_PEARL.get(),
        StellarityItems.ENDONOMICON.get()
    };
    /*
    public static final ResourceKey<CreativeModeTab> FOOD_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "food");
    public static final ResourceKey<CreativeModeTab> BLOCKS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "building_blocks");
    public static final ResourceKey<CreativeModeTab> EQUIPMENT_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "equipment");
    public static final ResourceKey<CreativeModeTab> INGREDIENTS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "ingredients");
    public static final ResourceKey<CreativeModeTab> TRINKETS_KEY = Stellarity.key(CREATIVE_MODE_TAB.key(), "trinkets");
    */
    public static final RegistrySupplier<CreativeModeTab> FOOD = CREATIVE_MODE_TABS.register(Stellarity.id("food"),
        () -> CreativeTabRegistry.create(builder -> builder
            .icon(() -> new ItemStack(StellarityItems.SUSHI.get()))
            .title(Component.translatable("itemGroup.stellarity.food"))
            .build()));
    public static final RegistrySupplier<CreativeModeTab> BLOCKS = CREATIVE_MODE_TABS.register(Stellarity.id("food"),
        () -> CreativeTabRegistry.create(builder -> builder
            .icon(() -> new ItemStack(StellarityItems.ENDER_GRASS_BLOCK.get()))
            .title(Component.translatable("itemGroup.stellarity.blocks"))
            .build()));
    public static final RegistrySupplier<CreativeModeTab> EQUIPMENT = CREATIVE_MODE_TABS.register(Stellarity.id("food"),
        () -> CreativeTabRegistry.create(builder -> builder
            .icon(() -> new ItemStack(StellarityItems.CALL_OF_THE_VOID.get()))
            .title(Component.translatable("itemGroup.stellarity.equipment"))
            .build()));
    public static final RegistrySupplier<CreativeModeTab> INGREDIENTS = CREATIVE_MODE_TABS.register(Stellarity.id("food"),
        () -> CreativeTabRegistry.create(builder -> builder
            .icon(() -> new ItemStack(StellarityItems.ENDERITE_SHARD.get()))
            .title(Component.translatable("itemGroup.stellarity.ingredients"))
            .build()));
    public static final RegistrySupplier<CreativeModeTab> TRINKETS = CREATIVE_MODE_TABS.register(Stellarity.id("food"),
        () -> CreativeTabRegistry.create(builder -> builder
            .icon(() -> new ItemStack(StellarityItems.PRISMATIC_PEARL.get()))
            .title(Component.translatable("itemGroup.stellarity.trinkets"))
            .build()));

    public static void init() {
        finishSetup(FOOD, FOOD_ITEMS, FOOD_ITEMSTACKS);
        finishSetup(BLOCKS, BLOCKS_ITEMS);
        finishSetup(EQUIPMENT, EQUIPMENT_ITEMS);
        finishSetup(INGREDIENTS, INGREDIENT_ITEMS);
        finishSetup(TRINKETS, TRINKET_ITEMS);
        

        Stellarity.LOGGER.info("Registering Stellarity Creative Mode Tabs");

    }
    
    public static void finishSetup(RegistrySupplier<CreativeModeTab> tab, Supplier<ItemLike[]> items, Supplier<ItemStack[]> stacks) {
        tab.listen(creativeModeTab -> {
            CreativeTabRegistry.appendBuiltin(creativeModeTab, items.get());
            CreativeTabRegistry.appendBuiltinStack(creativeModeTab, stacks.get());
        });
    }

    public static void finishSetup(RegistrySupplier<CreativeModeTab> tab, Supplier<ItemLike[]> items) {
        finishSetup(tab, items, () -> new ItemStack[]{});
    }
    
}
