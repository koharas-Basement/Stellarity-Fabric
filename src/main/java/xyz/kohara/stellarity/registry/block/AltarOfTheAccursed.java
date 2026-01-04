package xyz.kohara.stellarity.registry.block;


import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.registry.block_entity.AltarOfTheAccursedBlockEntity;
import xyz.kohara.stellarity.registry.StellarityBlockEntityTypes;

//? > 1.21 {
/*import com.mojang.serialization.MapCodec;
 *///? } else {

//? }

public class AltarOfTheAccursed extends BaseEntityBlock {
    public enum PlaceType implements StringRepresentable {
        NORMAL,
        CREATIVE,
        SATCHEL;

        @Override

        public String getSerializedName() {
            return switch (this) {
                case NORMAL -> "normal";
                case CREATIVE -> "creative";
                case SATCHEL -> "satchel";
            };
        }

        public boolean bypassesDragon() {
            return this == CREATIVE;
        }
    }

    public static final BooleanProperty LOCKED = BooleanProperty.create("locked");
    public static final EnumProperty<PlaceType> PLACE_TYPE = EnumProperty.create("place_type", PlaceType.class);


    public AltarOfTheAccursed(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(LOCKED, false).setValue(PLACE_TYPE, PlaceType.CREATIVE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LOCKED).add(PLACE_TYPE);
    }


    public static final VoxelShape SHAPE = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 13.0F, 16.0F);

    public static Properties blockProperties() {
        return Properties.of()
            .mapColor(MapColor.COLOR_GREEN)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.GLASS)
            .lightLevel((blockStatex) -> 7)
            .strength(-1.0F, 6700000.0F);
    }


    //? = 1.20.1
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }


    //? > 1.21 {

    /*@Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(AltarOfTheAccursed::new);
    }
    *///? } else {

    //? }


    @Override
    public RenderShape getRenderShape(BlockState blockState) {
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
