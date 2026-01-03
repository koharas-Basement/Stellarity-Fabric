package xyz.kohara.stellarity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityClientNetworking;
import xyz.kohara.stellarity.registry.StellarityEntityRenderers;
import xyz.kohara.stellarity.registry.StellarityModels;
import xyz.kohara.stellarity.registry.StellarityTooltips;

@Environment(EnvType.CLIENT)
public class StellarityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Stellarity.LOGGER.info("Stellarity Client Initializing");

        StellarityModels.init();
        StellarityEntityRenderers.init();
        StellarityTooltips.init();
        StellarityClientNetworking.init();
    }
}
