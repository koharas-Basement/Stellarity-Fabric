package xyz.kohara.stellarity.datagen.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;

import net.minecraft.world.item.Items;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityItems;

import java.util.function.BiConsumer;

//? > 1.21 {
/*import java.util.concurrent.CompletableFuture;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction;
import org.jetbrains.annotations.NotNull;
import net.minecraft.resources.ResourceKey;
import it.unimi.dsi.fastutil.ints.IntArrayList;
*///? } else {
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
  //? }

import java.util.HashMap;

import static xyz.kohara.stellarity.datagen.loot_table.LootTableUtils.*;

public class FishingLootTableProvider extends SimpleFabricLootTableProvider {
  //? 1.20.1 {
  public FishingLootTableProvider(FabricDataOutput output) {
    super(output, LootContextParamSets.FISHING);
  }
  //? } 1.21.1 {
  /*public FishingLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(output, registryLookup, LootContextParamSets.FISHING);
  }
  *///? } else {
  /*public FishingLootTableProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(output, registryLookup, LootContextParamSets.FISHING);
  }
  *///? }


  public static final HashMap<String, LootTable.Builder> LOOT_TABLES = new HashMap<>();

  static {
    LOOT_TABLES.put("void_fishing/fish", LootTable.lootTable().withPool(new LootPool.Builder()
      .add(item(StellarityItems.ENDER_KOI).setWeight(15).apply(count(range(2, 4))))
      .add(item(StellarityItems.CRYSTAL_HEARTFISH).setWeight(4)
      )));

    try {
      //noinspection deprecation
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
          SetNbtFunction.setTag(TagParser.parseTag("""
            {"Explosion": {Type:0,Flicker:0,Trail:0,Colors:[I;8073150],FadeColors:[I;12801229]}}
            """))
          //? } else {
          /*SetComponentsFunction.setComponent(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
            FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, false
          ))
          *///? }
        ))
        .add(item(Items.FIREWORK_STAR).apply(
          //? 1.20.1 {
          SetNbtFunction.setTag(TagParser.parseTag("""
            {"Explosion": {Type:0,Flicker:1,Trail:0,Colors:[I;8073150],FadeColors:[I;12801229]}}
            """))
          //? } else {
          /*SetComponentsFunction.setComponent(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
            FireworkExplosion.Shape.SMALL_BALL, new IntArrayList(new int[]{8073150}), new IntArrayList(new int[]{12801229}), false, true
          ))
          *///? }
        ))
        .add(item(Items.FIREWORK_STAR).apply(
          //? 1.20.1 {
          SetNbtFunction.setTag(TagParser.parseTag("""
            {"Explosion": {Type:0,Flicker:0,Trail:1,Colors:[I;12801229],FadeColors:[I;8073150]}}
            """))
          //? } else {
          /*SetComponentsFunction.setComponent(DataComponents.FIREWORK_EXPLOSION, new FireworkExplosion(
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
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //? 1.20.1 {
  @Override
  public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
    for (var entry : LOOT_TABLES.entrySet()) {
      consumer.accept(Stellarity.id(entry.getKey()), entry.getValue());
    }
  }

  //? } else {
  /*@Override
  public void generate(@NotNull BiConsumer<ResourceKey<@NotNull LootTable>, LootTable.Builder> consumer) {
    for (var entry : LOOT_TABLES.entrySet()) {
      consumer.accept(Stellarity.key(Registries.LOOT_TABLE, entry.getKey()), entry.getValue());
    }
  }
  *///? }


}
