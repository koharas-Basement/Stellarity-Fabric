package xyz.kohara.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.world.item.Items;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.function.BiConsumer;

//? > 1.21 {
/*import java.util.concurrent.CompletableFuture;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import it.unimi.dsi.fastutil.ints.IntArrayList;

import net.minecraft.tags.EnchantmentTags;
*///? } else {
import net.minecraft.resources.ResourceLocation;

 //? }

import java.util.HashMap;

import static xyz.kohara.stellarity.datagen.loot_table.LootTableUtils.*;

public class FishingLootTableProvider extends SimpleFabricLootTableProvider {

    //? 1.20.1 {
    public FishingLootTableProvider(FabricDataOutput output) {
        super(output, LootContextParamSets.FISHING);
    }
    //? } else {
    /*private final CompletableFuture<HolderLookup.Provider> registryLookup;

    public FishingLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup, LootContextParamSets.FISHING);
        this.registryLookup = registryLookup;

    }
    *///? }


    public static final HashMap<String, LootTable.Builder> LOOT_TABLES = new HashMap<>();

    public static void define(/*? > 1.21 { *//*HolderLookup.Provider lookup*//*? } */) {
        LOOT_TABLES.put("void_fishing/fish", LootTable.lootTable().withPool(new LootPool.Builder()
            .add(item(StellarityItems.ENDER_KOI).setWeight(15).apply(count(range(2, 4))))
            .add(item(StellarityItems.CRYSTAL_HEARTFISH).setWeight(4)
            )));


        LOOT_TABLES.put("void_fishing/junk", LootTable.lootTable().withPool(new LootPool.Builder()
            .add(item(Items.CRYING_OBSIDIAN).apply(count(range(1, 3))))
            .add(item(Items.OBSIDIAN).apply(count(range(1, 4))))
            .add(item(Items.END_STONE).apply(count(range(3, 9))))
            .add(item(Items.CHORUS_FRUIT).apply(count(range(1, 5))))
            .add(item(Items.POPPED_CHORUS_FRUIT).setWeight(2).apply(count(range(2, 5))))
            .add(item(Items.PURPUR_BLOCK).apply(count(range(6, 12))))
            .add(item(Items.PHANTOM_MEMBRANE).setWeight(3).apply(count(range(1, 3))))
            .add(item(Items.ENDER_PEARL).setWeight(3).apply(count(range(1, 2))))
            .add(item(Items.SHULKER_SHELL).setWeight(5).apply(count(range(1, 2))))
            .add(item(Items.FIREWORK_STAR).apply(
                //? 1.20.1 {
                nbt("""
                    {"Explosion": {Type:0,Flicker:0,Trail:0,Colors:[I;8073150],FadeColors:[I;12801229]}}
                    """)
                //? } else {
                /*component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
                    FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, false
                ))
                *///? }
            ))
            .add(item(Items.FIREWORK_STAR).apply(
                //? 1.20.1 {
                nbt("""
                    {"Explosion": {Type:0,Flicker:1,Trail:0,Colors:[I;8073150],FadeColors:[I;12801229]}}
                    """)
                //? } else {
                /*component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
                    FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, true
                ))
                *///? }
            ))
            .add(item(Items.FIREWORK_STAR).apply(
                //? 1.20.1 {
                nbt("""
                    {"Explosion": {Type:0,Flicker:0,Trail:1,Colors:[I;12801229],FadeColors:[I;8073150]}}
                    """)
                //? } else {
                /*component(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
                    FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{12801229}), new IntArrayList(new int[]{8073150}), true, false
                ))
                *///? }
            ))
            .add(item(Items.PAPER).setWeight(3).apply(count(range(1, 5))))
            .add(item(Items.END_ROD).setWeight(3).apply(count(range(1, 5))))
            .add(item(Items.MAGENTA_DYE))
            .add(item(Items.PURPLE_DYE))
            .add(item(Items.BLACK_DYE))
        ));

        LOOT_TABLES.put("void_fishing/treasure", LootTable.lootTable().withPool(new LootPool.Builder()
            .add(item(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
            .add(item(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(7).apply(count(range(1, 2))))
            .add(item(Items.END_CRYSTAL).setWeight(5).apply(count(range(1, 2))))
            .add(item(Items.END_CRYSTAL).setWeight(3).apply(count(range(2, 3))))
            .add(item(StellarityItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE).setWeight(11))
            .add(item(StellarityItems.WINGED_KEY).setWeight(11))
            .add(item(Items.BOOK).setWeight(2).apply(
                    //? 1.20.1 {
                enchantLevels(30, 40).allowTreasure()
             //? } else {
            /*enchantLevels(lookup, 30, 40).fromOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
            *///? }
        )
        ).add(item(Items.BOOK).setWeight(5).apply(
            //? 1.20.1 {
            enchantLevels(17, 29).allowTreasure()
             //? } else {
            /*enchantLevels(lookup, 17, 29).fromOptions(lookup.lookup(Registries.ENCHANTMENT).orElseThrow().getOrThrow(EnchantmentTags.ON_RANDOM_LOOT))
            *///? }
        )
        )
    ));

    LOOT_TABLES.put("void_fishing/fisher_of_voids", LootTable.lootTable().withPool(new LootPool.Builder().add(item(StellarityItems.FISHER_OF_VOIDS)
        .apply(damage(0.15f, 0.75f))
    )));

    LOOT_TABLES.put("void_fishing/event", LootTable.lootTable().withPool(new LootPool.Builder()
        .add(lootTable(Stellarity.id("void_fishing/junk")).setWeight(15).setQuality(-2))
        .add(lootTable(Stellarity.id("void_fishing/treasure")).setWeight(4).setQuality(2))
        .add(lootTable(Stellarity.id("void_fishing/fish")).setWeight(70).setQuality(-1))
    ));
    }


    //? 1.20.1 {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
    define();
    for (var entry : LOOT_TABLES.entrySet()) {
        consumer.accept(Stellarity.id(entry.getKey()), entry.getValue());
    }
    }

    //? } else {
    /*@Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {

    define(registryLookup.join());

    for (var entry : LOOT_TABLES.entrySet()) {
        consumer.accept(Stellarity.key(Registries.LOOT_TABLE, entry.getKey()), entry.getValue());
    }
    }
    *///? }


}
