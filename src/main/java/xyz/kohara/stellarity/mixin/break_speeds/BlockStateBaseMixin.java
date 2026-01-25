package xyz.kohara.stellarity.mixin.break_speeds;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
//? 1.20.1 {


import com.google.common.collect.ImmutableMap;
 //? } else {

/*import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;

*///? }

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin extends StateHolder<Block, BlockState> {
    @Shadow
    public abstract Block getBlock();

    @Shadow
    public abstract boolean is(Block block);

    //? 1.20.1 {
    protected BlockStateBaseMixin(Block object, ImmutableMap<Property<?>, Comparable<?>> immutableMap, MapCodec<BlockState> mapCodec) {
        super(object, immutableMap, mapCodec);
    }
    //? } else {
    /*protected BlockStateBaseMixin(Block object, Reference2ObjectArrayMap<Property<?>, Comparable<?>> reference2ObjectArrayMap, MapCodec<BlockState> mapCodec) {
        super(object, reference2ObjectArrayMap, mapCodec);
    }
    *///? }

    @WrapMethod(method = "getDestroySpeed")
    private float dynamicDestroySpeed(BlockGetter blockGetter, BlockPos blockPos, Operation<Float> original) {
        if (is(StellarityBlocks.ALTAR_OF_THE_ACCURSED.get()) && getValue(AltarOfTheAccursed.PLACE_TYPE) == AltarOfTheAccursed.PlaceType.SATCHEL)
            return 50;

        return original.call(blockGetter, blockPos);
    }
}
