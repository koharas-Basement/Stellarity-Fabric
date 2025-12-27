package xyz.kohara.stellarity.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import xyz.kohara.stellarity.Stellarity;

@Environment(EnvType.CLIENT)
public class StellarityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Stellarity.LOGGER.info("Stellarity Client Initializing");
        
        StellarityModels.init();
        StellarityEntityRenderers.init();
        StellarityClientNetworking.init();
    }
}
