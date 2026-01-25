package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.block_entity.AltarOfTheAccursedBlockEntity;

public class StellarityBlockEntityTypes {
    private static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = StellarityRegistries.MANAGER.get().get(Registries.BLOCK_ENTITY_TYPE);
    
    public static final RegistrySupplier<BlockEntityType<AltarOfTheAccursedBlockEntity>> ALTAR_OF_THE_ACCURSED =
        register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);
    
    @SafeVarargs
    public static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(String path, BlockEntityType.BlockEntitySupplier<T> factory, RegistrySupplier<Block>... blocks) {
        return BLOCK_ENTITY_TYPES.register(Stellarity.id(path), () -> {
            Block[] theBlocks = new Block[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                theBlocks[i] = blocks[i].get();
            }
            return BlockEntityType.Builder.of(factory, theBlocks).build(null);
        });
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Block Entities");
    }
}
