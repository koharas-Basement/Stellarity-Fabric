package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import xyz.kohara.stellarity.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.block.EnderDirtPath;
import xyz.kohara.stellarity.block.EnderGrassBlock;

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
  public static final Block ROOTED_ENDER_DIRT = register("rooted_ender_dirt", Block::new, BlockBehaviour.Properties.of()
    .mapColor(MapColor.DIRT)
    .strength(0.5F)
    .sound(SoundType.ROOTED_DIRT));
  public static final Block ENDER_DIRT_PATH = register("ender_dirt_path", EnderDirtPath::new, EnderDirtPath.blockProperties());
  public static final Block ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursed::new, AltarOfTheAccursed.blockProperties());
//  public static final Block _PHANTOM_ITEM_FRAME = register("phantom_item_frame", Block::new, BlockBehaviour.Properties.of()); // Placeholder block for registration purposes only


  public static Block register(String id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
    ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Stellarity.of(id));
    //? >= 1.21.9 {
    /*settings = settings.setId(blockKey);
     *///?}

    Block block = blockFactory.apply(settings);
    Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

    return block;
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Blocks");
  }
}
