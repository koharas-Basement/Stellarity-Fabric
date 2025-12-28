//? 1.21.1 {
/*package xyz.kohara.stellarity.client.mixin.phantom_item_frame_model;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.resources.model.BlockStateModelLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;

import java.util.Map;

import static xyz.kohara.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@Mixin(BlockStateModelLoader.class)
@MixinEnvironment("client")
public abstract class BlockStateModelLoaderMixin {
    @Shadow
    protected abstract void loadBlockStateDefinitions(ResourceLocation resourceLocation, StateDefinition<Block, BlockState> stateDefinition);

    @Inject(method = "loadAllBlockStates", at = @At("HEAD"))
    private void addStellarityBlockStates(CallbackInfo ci) {
        loadBlockStateDefinitions(Stellarity.id("phantom_item_frame"), FAKE_STATE_DEFINITION);
    }
}
*///? }