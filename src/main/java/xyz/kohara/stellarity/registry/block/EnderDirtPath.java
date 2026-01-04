package xyz.kohara.stellarity.registry.block;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.registry.StellarityBlocks;
//? > 1.21.9 {
/*import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.util.RandomSource;
*///? } else {
import net.minecraft.world.level.LevelAccessor;
    //? }


public class EnderDirtPath extends DirtPathBlock {
    public EnderDirtPath(Properties properties) {
        super(properties);
    }

    public static Properties blockProperties() {
        return Properties.of()
            .mapColor(MapColor.DIRT)
            .strength(0.65F)
            .sound(SoundType.GRASS)
            .isViewBlocking((a, b, c) -> true)
            .isSuffocating((a, b, c) -> true).forceSolidOn();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return
            !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ?
                Block.pushEntitiesUp(this.defaultBlockState(), StellarityBlocks.ENDER_DIRT.defaultBlockState(), context.getLevel(), context.getClickedPos()) :
                super.getStateForPlacement(context);
    }

    //? <= 1.21.1 {
    @Override
    public BlockState updateShape(BlockState blockState, Direction direction, BlockState blockState2, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos blockPos2) {
        if (direction == Direction.UP && !blockState.canSurvive(levelAccessor, blockPos)) {
            return StellarityBlocks.ENDER_DIRT.defaultBlockState();
        }

        return super.updateShape(blockState, direction, blockState2, levelAccessor, blockPos, blockPos2);
    }
    //? } else {

    /*@Override
    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        if (direction == Direction.UP && !blockState.canSurvive(levelReader, blockPos)) {
            return StellarityBlocks.ENDER_DIRT.defaultBlockState();
        }

        return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
    }


    *///? }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState blockState2 = levelReader.getBlockState(blockPos.above());
        return !(blockState2.isSolid() || blockState2.getBlock() instanceof DirtPathBlock) || blockState2.getBlock() instanceof FenceGateBlock;
    }
}