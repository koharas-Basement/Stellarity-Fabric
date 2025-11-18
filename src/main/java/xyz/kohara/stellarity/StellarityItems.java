package xyz.kohara.stellarity;

import com.mojang.datafixers.types.Func;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.item.CallOfTheVoid;

import java.util.function.Function;

public class StellarityItems {
    //? <= 1.21.1 {
    public static final Item CALL_OF_THE_VOID = register(new CallOfTheVoid(), "call_of_the_void");
    public static final Item ENDER_DIRT = register(new BlockItem(StellarityBlocks.ENDER_DIRT, new Item.Properties()), "ender_dirt");
    public static final Item ENDER_GRASS_BLOCK = register(new BlockItem(StellarityBlocks.ENDER_GRASS_BLOCK, new Item.Properties()), "ender_grass_block");
    public static final Item ASHEN_FROGLIGHT = register(new BlockItem(StellarityBlocks.ASHEN_FROGLIGHT, new Item.Properties()), "ashen_froglight");

    public static Item register(Item item, String id) {
        return Registry.register(BuiltInRegistries.ITEM,Stellarity.of(id),item);
    }
    //?} else {
    /*public static final Item CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.properties());


    // IMPORTANT: blocks must be registered with useBlockDescriptionPrefix to have correct translation keys
    public static final Item ENDER_DIRT = register("ender_dirt", props -> new BlockItem(StellarityBlocks.ENDER_DIRT, props), new Item.Properties().useBlockDescriptionPrefix());
    public static final Item ENDER_GRASS_BLOCK = register("ender_grass_block", props -> new BlockItem(StellarityBlocks.ENDER_GRASS_BLOCK, props), new Item.Properties().useBlockDescriptionPrefix());
    public static final Item ASHEN_FROGLIGHT = register("ashen_froglight", props -> new BlockItem(StellarityBlocks.ASHEN_FROGLIGHT, props), new Item.Properties().useBlockDescriptionPrefix());

    public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    *///?}

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Items");
    }


}