package xyz.kohara.stellarity.registry;

//? > 1.21 {

/*import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

*///? }

import xyz.kohara.stellarity.Stellarity;

public class StellarityNetworking {
    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Common Networking");
        //? 1.20.1 {

        //? } else {
        /*PayloadTypeRegistry.playS2C().register(S2CSetStellarityEntityDataPacket.TYPE, S2CSetStellarityEntityDataPacket.STREAM_CODEC);
        *///? }

    }
}
