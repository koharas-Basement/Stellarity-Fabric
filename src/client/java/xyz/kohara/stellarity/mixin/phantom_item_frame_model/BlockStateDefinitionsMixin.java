//? > 1.21.9 {
/*package xyz.kohara.stellarity.mixin.phantom_item_frame_model;

import com.llamalad7.mixinextras.sugar.Local;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.resources.model.BlockStateDefinitions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.Stellarity;

import static xyz.kohara.stellarity.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Mixin(BlockStateDefinitions.class)
@MixinEnvironment("client")
public class BlockStateDefinitionsMixin {
    @Inject(method = "definitionLocationToBlockStateMapper", at = @At(value = "INVOKE", target = "Ljava/util/Objects;requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;"))
    private static void addStellarityBlockStates(CallbackInfoReturnable<Function<ResourceLocation, StateDefinition<Block, BlockState>>> cir, @Local Map<ResourceLocation, StateDefinition<Block, BlockState>> map) {
        map.put(Stellarity.id("phantom_item_frame"), FAKE_STATE_DEFINITION);
    }
}
*///? }