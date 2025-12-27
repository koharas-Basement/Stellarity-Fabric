package xyz.kohara.stellarity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

@Environment(EnvType.CLIENT)
public class StellarityClientNetworking {
    public static void init() {
        //? 1.20.1 {
        ClientPlayNetworking.registerGlobalReceiver(S2CSetStellarityEntityDataPacket.ID, (client, handler, buf, responseSender) -> {
            var packet = S2CSetStellarityEntityDataPacket.unpack(buf);
            client.execute(() -> {
                Entity entity = client.level.getEntity(packet.id());
                if (entity == null) return;
                entity.stellarity$entityData().assignValues(packet.list());
            });
        });
        //? } else {

        /*ClientPlayNetworking.registerGlobalReceiver(S2CSetStellarityEntityDataPacket.TYPE, (packet, context) -> {
            @SuppressWarnings("resource") ClientLevel world = context.client().level;
            if (world == null) return;

            Entity entity = world.getEntity(packet.id());
            if (entity == null) return;

            entity.stellarity$entityData().assignValues(packet.list());

        });
        *///? }
    }
}
