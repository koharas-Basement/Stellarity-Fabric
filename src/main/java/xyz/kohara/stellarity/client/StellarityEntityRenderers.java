package xyz.kohara.stellarity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.client.renderer.entity.PhantomItemFrameRenderer;

@Environment(EnvType.CLIENT)
public class StellarityEntityRenderers {

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Entity Renderers");
        EntityRendererRegistry.register(StellarityEntities.PHANTOM_ITEM_FRAME, PhantomItemFrameRenderer::new);
        EntityRendererRegistry.register(StellarityEntities.PRISMATIC_PEARL, ThrownItemRenderer::new);
    }

}
