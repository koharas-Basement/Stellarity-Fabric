package xyz.kohara.stellarity.block;



import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.block_entity.AltarOfTheAccursedBlockEntity;
import xyz.kohara.stellarity.StellarityBlockEntityTypes;

//? > 1.21 {

//? } else {

//? }

public class AltarOfTheAccursed extends BaseEntityBlock {
  public enum State implements StringRepresentable {
    LOCKED,
    UNLOCKED,
    CREATIVE_LOCKED,
    CREATIVE_UNLOCKED;

    @Override
    public @NotNull String getSerializedName() {
      return switch (this) {
        case LOCKED -> "locked";
        case UNLOCKED -> "unlocked";
        case CREATIVE_LOCKED -> "creative_locked";
        case CREATIVE_UNLOCKED -> "creative_unlocked";
      };
    }

    public boolean isCreative() {
      return this == CREATIVE_LOCKED || this == CREATIVE_UNLOCKED;
    }

    public boolean isUnlocked() {
      return this == UNLOCKED || this == CREATIVE_UNLOCKED;
    }

    public boolean isLocked() {
      return this == LOCKED || this == CREATIVE_LOCKED;
    }
  }

  public static final EnumProperty<State> STATE = EnumProperty.create("state", State.class);
  public AltarOfTheAccursed(Properties properties) {
    super(properties);

    registerDefaultState(defaultBlockState().setValue(STATE, State.CREATIVE_UNLOCKED));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(STATE);
  }



  public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 13.0F, 16.0F);

  public static Properties blockProperties() {
    return Properties.of()
      .mapColor(MapColor.COLOR_GREEN)
      .instrument(NoteBlockInstrument.BASEDRUM)
      .sound(SoundType.GLASS)
      .lightLevel((blockStatex) -> 7)
      .strength(-1.0F, 6700000.0F)
      .noLootTable();
  }


  //? = 1.20.1
  @SuppressWarnings("deprecation")
  @Override
  public @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
    return SHAPE;
  }


  //? > 1.21 {

  /*@Override
  public @NotNull MapCodec<? extends BaseEntityBlock> codec() {
    return simpleCodec(AltarOfTheAccursed::new);
  }
  *///? } else {

  //? }


  @Override
  public @NotNull RenderShape getRenderShape(BlockState blockState) {
    return RenderShape.MODEL;
  }

  @Override
  public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new AltarOfTheAccursedBlockEntity(blockPos, blockState);
  }

  @Override
  public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
    if (type == StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED) return AltarOfTheAccursedBlockEntity::tick;
    return null;
  }
}
