package xyz.kohara.stellarity.registry;

import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RootedDirtBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.mixin.accessors.HoeItemAccessor;
import xyz.kohara.stellarity.mixin.accessors.ShovelItemAccessor;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.registry.block.EnderDirtPath;
import xyz.kohara.stellarity.registry.block.EnderGrassBlock;

import java.util.function.Function;

public class StellarityBlocks {
    private static final Registrar<Block> BLOCKS = StellarityRegistries.MANAGER.get().get(Registries.BLOCK);
    
    public static final RegistrySupplier<Block> ENDER_DIRT = register("ender_dirt", Block::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final RegistrySupplier<Block> ENDER_GRASS_BLOCK = register("ender_grass_block", EnderGrassBlock::new, EnderGrassBlock.blockProperties());
    public static final RegistrySupplier<Block> ASHEN_FROGLIGHT = register("ashen_froglight", RotatedPillarBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.SAND)
        .strength(0.3F)
        .lightLevel((state) -> 15)
        .sound(SoundType.FROGLIGHT));
    public static final RegistrySupplier<Block> ROOTED_ENDER_DIRT = register("rooted_ender_dirt", RootedDirtBlock::new, BlockBehaviour.Properties.of()
        .mapColor(MapColor.DIRT)
        .strength(0.5F)
        .sound(SoundType.ROOTED_DIRT));
    public static final RegistrySupplier<Block> ENDER_DIRT_PATH = register("ender_dirt_path", EnderDirtPath::new, EnderDirtPath.blockProperties());
    public static final RegistrySupplier<Block> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursed::new, AltarOfTheAccursed.blockProperties());


    public static RegistrySupplier<Block> register(String id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings) {
        var location = Stellarity.id(id);
        return BLOCKS.register(location, () -> blockFactory.apply(settings));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Blocks");
        
        ROOTED_ENDER_DIRT.listen(block -> ENDER_DIRT.listen(block1 ->
            HoeItemAccessor.getTillables().put(block, Pair.of((unused) -> true, HoeItem.changeIntoStateAndDropItem(block1.defaultBlockState(), Items.HANGING_ROOTS)))));
        ENDER_DIRT.listen(block -> ENDER_DIRT_PATH.listen(block1 -> ShovelItemAccessor.getFlattenables().put(block, block1.defaultBlockState())));
        ENDER_GRASS_BLOCK.listen(block -> ENDER_DIRT_PATH.listen(block1 -> ShovelItemAccessor.getFlattenables().put(block, block1.defaultBlockState())));

    }
}
