//? 1.20.1 {
package xyz.kohara.stellarity.mixin.phantom_item_frame_model;


import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;

import static xyz.kohara.stellarity.registry.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
    @Shadow
    protected abstract void loadTopLevel(ModelResourceLocation par1);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V", ordinal = 0))
    private void addStellarityModels(CallbackInfo ci) {
        FAKE_STATE_DEFINITION.getPossibleStates().forEach((blockState) -> this.loadTopLevel(BlockModelShaper.stateToModelLocation(Stellarity.id("phantom_item_frame"), blockState)));

        Stellarity.LOGGER.info("Registered Phantom Item Frame block states for model loading.");
    }
}
//? }