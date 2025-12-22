package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityBlocks;
//? <= 1.21.1 {
import net.minecraft.data.models.BlockModelGenerators;

import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.model.TexturedModel;
import xyz.kohara.stellarity.StellarityItems;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
//?} else {

/*import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.util.random.WeightedList;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.color.item.GrassColorSource;
import xyz.kohara.stellarity.StellarityItems;

import java.util.List;
*///?}


public class ModelProvider extends FabricModelProvider {
  public ModelProvider(FabricDataOutput output) {
    super(output);
  }

  public final static Item[] FLAT_ITEMS = new Item[]{
    StellarityItems.SUSHI,
    StellarityItems.GOLDEN_CHORUS_FRUIT,
    StellarityItems.FRIED_CHORUS_FRUIT,
    StellarityItems.FROZEN_CARPACCIO,
    StellarityItems.ENDERMAN_FLESH,
    StellarityItems.CRYSTAL_HEARTFISH,
    StellarityItems.GRILLED_ENDERMAN_FLESH,
    StellarityItems.FLAREFIN_KOI,
    StellarityItems.AMETHYST_BUDFISH,
    StellarityItems.CRIMSON_TIGERFISH,
    StellarityItems.ENDER_KOI,
    StellarityItems.FLESHY_PIRANHA,
    StellarityItems.BUBBLEFISH,
    StellarityItems.PRISMITE,
    StellarityItems.OVERGROWN_COD,
    StellarityItems.PRISMATIC_SUSHI,
    StellarityItems.SHEPHERDS_PIE,
    StellarityItems.CHORUS_PIE,
    StellarityItems.PHO,
    StellarityItems.PHANTOM_ITEM_FRAME,
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

  @Override
  public void generateBlockStateModels(BlockModelGenerators generators) {
    generators.createTrivialCube(StellarityBlocks.ENDER_DIRT);
    generators.createTrivialCube(StellarityBlocks.ROOTED_ENDER_DIRT);
    generators.createNonTemplateModelBlock(StellarityBlocks.ENDER_DIRT_PATH);
    generators.createNonTemplateModelBlock(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

    //? <= 1.21.1 {
    generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
    generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new Variant().with(VariantProperties.MODEL, Stellarity.id("block/ender_grass_block_snowy")));
    //?} else {
    /*generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
    generators.registerSimpleItemModel(StellarityBlocks.ASHEN_FROGLIGHT, Stellarity.id("block/ashen_froglight"));
    generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, new MultiVariant(WeightedList.<Variant>builder()
      .add(new Variant(Stellarity.id("block/ender_grass_block")))
      .add(new Variant(Stellarity.id("block/ender_grass_block")), 90)
      .add(new Variant(Stellarity.id("block/ender_grass_block")), 180)
      .add(new Variant(Stellarity.id("block/ender_grass_block")), 270)
      .build()), new MultiVariant(WeightedList.<Variant>builder().add(new Variant(Stellarity.id("block/ender_grass_block_snowy"))).build()));
    generators.registerSimpleTintedItemModel(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new GrassColorSource(1.0f, 0.5f));
    *///?}

  }

  @Override
  public void generateItemModels(ItemModelGenerators generators) {
    //? >= 1.21.4 {
    /*generators.generateBow(StellarityItems.CALL_OF_THE_VOID);
    generators.itemModelOutput.accept(StellarityItems.SHULKER_BODY, new BlockModelWrapper.Unbaked(Stellarity.id("item/shulker_body"), List.of()));
    generators.generateFishingRod(StellarityItems.FISHER_OF_VOIDS);
    *///?} else {
    generators.generateFlatItem(StellarityItems.FISHER_OF_VOIDS, "_cast", ModelTemplates.FLAT_HANDHELD_ROD_ITEM);
    //?}

    generators.generateFlatItem(StellarityItems.TAMARIS, ModelTemplates.FLAT_HANDHELD_ITEM);

    for (Item item : FLAT_ITEMS) {
      generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

  }
}