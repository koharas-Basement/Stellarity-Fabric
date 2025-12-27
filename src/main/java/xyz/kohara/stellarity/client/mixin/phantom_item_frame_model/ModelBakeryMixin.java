//? 1.20.1 {
package xyz.kohara.stellarity.client.mixin.phantom_item_frame_model;

import com.google.common.collect.ImmutableMap;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.client.renderer.entity.PhantomItemFrameRenderer;

import static xyz.kohara.stellarity.client.renderer.entity.PhantomItemFrameRenderer.FAKE_STATE_DEFINITION;

@Mixin(ModelBakery.class)
@MixinEnvironment("client")
public abstract class ModelBakeryMixin {

	@Shadow
	protected abstract void loadTopLevel(ModelResourceLocation par1);

	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Map;forEach(Ljava/util/function/BiConsumer;)V", ordinal = 0))
	private void addStellarityModels(CallbackInfo ci) {
		FAKE_STATE_DEFINITION.getPossibleStates().forEach((blockState) -> this.loadTopLevel(BlockModelShaper.stateToModelLocation(Stellarity.id("phantom_item_frame"), blockState)));
	}

}
//? }