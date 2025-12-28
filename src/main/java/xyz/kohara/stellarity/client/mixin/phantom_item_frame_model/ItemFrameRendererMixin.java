//? > 1.21.9 {
/*package xyz.kohara.stellarity.client.mixin.phantom_item_frame_model;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import xyz.kohara.stellarity.registry.StellarityEntities;

import static xyz.kohara.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@Mixin(ItemFrameRenderer.class)
@MixinEnvironment("client")
public class ItemFrameRendererMixin {
    @ModifyVariable(method = "submit(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
    at= @At(value = "STORE"))
    private BlockState phantomItemFrameState(BlockState blockState, ItemFrameRenderState itemFrameRenderState) {
        if (itemFrameRenderState.entityType == StellarityEntities.PHANTOM_ITEM_FRAME) {
            return FAKE_STATE_DEFINITION.any();
        }
        return blockState;
    }
}
*///? }