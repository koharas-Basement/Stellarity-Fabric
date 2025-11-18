package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;




import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityBlocks;
//? <= 1.21.1 {
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.model.TexturedModel;
//?} else {
/*import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.util.random.WeightedList;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.color.item.GrassColorSource;
*///?}


public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generators) {
        generators.createTrivialCube(StellarityBlocks.ENDER_DIRT);
       

        //? <= 1.21.1 {
        generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
        generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.of("block/ender_grass_block"), new Variant().with(VariantProperties.MODEL, Stellarity.of("block/ender_grass_block_snowy")));
         //?} else {
        /*generators.createAxisAlignedPillarBlock(StellarityBlocks.ASHEN_FROGLIGHT, TexturedModel.COLUMN);
        generators.registerSimpleItemModel(StellarityBlocks.ASHEN_FROGLIGHT, Stellarity.of("block/ashen_froglight"));
        generators.createGrassLikeBlock(StellarityBlocks.ENDER_GRASS_BLOCK, new MultiVariant(WeightedList.<Variant>builder()
                .add(new Variant(Stellarity.of("block/ender_grass_block")))
                .add(new Variant(Stellarity.of("block/ender_grass_block")), 90)
                .add(new Variant(Stellarity.of("block/ender_grass_block")), 180)
                .add(new Variant(Stellarity.of("block/ender_grass_block")), 270)
                .build()), new MultiVariant(WeightedList.<Variant>builder().add(new Variant(Stellarity.of("block/ender_grass_block_snowy"))).build()));
        generators.registerSimpleTintedItemModel(StellarityBlocks.ENDER_GRASS_BLOCK, Stellarity.of("block/ender_grass_block"), new GrassColorSource(1.0f, 0.5f));
        *///?}

    }

    @Override
    public void generateItemModels(ItemModelGenerators generators) {
    }
}