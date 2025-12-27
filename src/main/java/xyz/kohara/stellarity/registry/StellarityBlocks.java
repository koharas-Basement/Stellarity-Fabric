package xyz.kohara.stellarity.registry;

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.registry.block.EnderDirtPath;
import xyz.kohara.stellarity.registry.block.EnderGrassBlock;

import java.util.function.Function;

public class StellarityBlocks {

    public static final Block ENDER_DIRT = register("ender_dirt", Block::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final Block ENDER_GRASS_BLOCK = register("ender_grass_block", EnderGrassBlock::new, EnderGrassBlock.blockProperties());
    public static final Block ASHEN_FROGLIGHT = register("ashen_froglight", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.SAND)
        .strength(0.3F)
        .lightLevel((state) -> 15)
        .sound(SoundType.FROGLIGHT));
    public static final Block ROOTED_ENDER_DIRT = register("rooted_ender_dirt", RootedDirtBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final Block ENDER_DIRT_PATH = register("ender_dirt_path", EnderDirtPath::new, EnderDirtPath.blockProperties());
    public static final Block ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursed::new, AltarOfTheAccursed.blockProperties());


    public static Block register(String id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {

        //? >= 1.21.9 {
        /*var location = Stellarity.key(Registries.BLOCK, id);
        settings = settings.setId(location);
        *///?} else {
        var location = Stellarity.id(id);
        //? }


        Block block = blockFactory.apply(settings);
        Registry.register(BuiltInRegistries.BLOCK, location, block);

        return block;
    }

    public static void init() {

        Stellarity.LOGGER.info("Registering Stellarity Blocks");

        TillableBlockRegistry.register(ROOTED_ENDER_DIRT, (unused) -> true, HoeItem.changeIntoStateAndDropItem(StellarityBlocks.ENDER_DIRT.defaultBlockState(), Items.HANGING_ROOTS));
        FlattenableBlockRegistry.register(ENDER_DIRT, ENDER_DIRT_PATH.defaultBlockState());
        FlattenableBlockRegistry.register(ENDER_GRASS_BLOCK, ENDER_DIRT_PATH.defaultBlockState());

    }
}
