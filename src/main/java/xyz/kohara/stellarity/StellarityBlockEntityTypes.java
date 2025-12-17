package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kohara.stellarity.block_entity.AltarOfTheAccursedBlockEntity;
//? > 1.21.9 {
/*import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
*///? }

public class StellarityBlockEntityTypes {
  public static final BlockEntityType<AltarOfTheAccursedBlockEntity> ALTAR_OF_THE_ACCURSED = register("altar_of_the_accursed", AltarOfTheAccursedBlockEntity::new, StellarityBlocks.ALTAR_OF_THE_ACCURSED);

  //? <= 1.21.1 {
  public static <T extends BlockEntity> BlockEntityType<T> register(String path, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
    return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.id(path).toString(), BlockEntityType.Builder.of(factory, blocks).build(null));
  }
  //? } else {
  /*public static <T extends BlockEntity> BlockEntityType<T> register(
    String name,
    FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
    Block... blocks
  ) {
    return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Stellarity.id(name), FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
  }
  *///? }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Block Entities");
  }
}
