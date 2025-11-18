package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.kohara.stellarity.block.AshenFroglight;
import xyz.kohara.stellarity.block.EnderDirt;
import xyz.kohara.stellarity.block.EnderGrassBlock;

import java.util.function.Function;

public class StellarityBlocks {
    //? <= 1.21.1 {
    public static final Block ENDER_DIRT = register(new EnderDirt(), "ender_dirt");
    public static final Block ENDER_GRASS_BLOCK = register(new EnderGrassBlock(), "ender_grass_block");
    public static final Block ASHEN_FROGLIGHT = register(new AshenFroglight(), "ashen_froglight");

    public static Block register(Block block, String id) {
        return Registry.register(BuiltInRegistries.BLOCK,Stellarity.of(id),block);
    }

    //?} else {
    /*public static final Block ENDER_DIRT = register("ender_dirt", EnderDirt::new, EnderDirt.blockProperties());
    public static final Block ENDER_GRASS_BLOCK = register("ender_grass_block", EnderGrassBlock::new, EnderGrassBlock.blockProperties());
    public static final Block ASHEN_FROGLIGHT = register("ashen_froglight", AshenFroglight::new, AshenFroglight.blockProperties());

    public static Block register(String id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Stellarity.of(id));
        Block block = blockFactory.apply(settings.setId(blockKey));

        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

        return block;
    }
    *///?}





    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Blocks");
    }
}
