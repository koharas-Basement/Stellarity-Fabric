package xyz.kohara.stellarity.client.registry;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;
//? if fabric {
/*import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
*///? } else if forge {
import net.minecraftforge.client.event.EntityRenderersEvent;
//? }
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.client.registry.renderer.entity.PhantomItemFrameRenderer;

//$ clientOnly
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public class StellarityEntityRenderers {

    public static void init(/*? if forge >>' {'*/ EntityRenderersEvent.RegisterRenderers event) {
        Stellarity.LOGGER.info("Registering Stellarity Entity Renderers");
        //? if fabric {
        /*EntityRendererRegistry.finishSetup(StellarityEntities.PHANTOM_ITEM_FRAME, PhantomItemFrameRenderer::new);
        EntityRendererRegistry.finishSetup(StellarityEntities.PRISMATIC_PEARL, ThrownItemRenderer::new);
        *///? } else {
        event.registerEntityRenderer(StellarityEntities.PHANTOM_ITEM_FRAME.get(), PhantomItemFrameRenderer::new);
        event.registerEntityRenderer(StellarityEntities.PRISMATIC_PEARL.get(), ThrownItemRenderer::new);
        //? }
    }

}
