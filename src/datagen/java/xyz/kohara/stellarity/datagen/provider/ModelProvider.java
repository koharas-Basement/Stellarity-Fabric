package xyz.kohara.stellarity.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import net.minecraft.data.models.BlockModelGenerators;

import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.model.TexturedModel;
import xyz.kohara.stellarity.registry.StellarityItems;

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
        StellarityItems.WINGED_KEY,
        StellarityItems.PRISMATIC_PEARL,
        StellarityItems.ENDONOMICON
    };

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.createTrivialCube(StellarityBlocks.ENDER_DIRT);
        generators.createTrivialCube(StellarityBlocks.ROOTED_ENDER_DIRT);
        generators.createNonTemplateModelBlock(StellarityBlocks.ENDER_DIRT_PATH);
        generators.createNonTemplateModelBlock(StellarityBlocks.ALTAR_OF_THE_ACCURSED);

        generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
        generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.id("block/ender_grass_block"), new Variant().with(VariantProperties.MODEL, Stellarity.id("block/ender_grass_block_snowy")));
        
    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
        generators.generateFlatItem(StellarityItems.FISHER_OF_VOIDS, "_cast", ModelTemplates.FLAT_HANDHELD_ROD_ITEM);

        generators.generateFlatItem(StellarityItems.TAMARIS, ModelTemplates.FLAT_HANDHELD_ITEM);

        for (Item item : FLAT_ITEMS) {
            generators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
        }

    }
}