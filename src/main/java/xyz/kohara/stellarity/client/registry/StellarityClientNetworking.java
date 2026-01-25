package xyz.kohara.stellarity.client.registry;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;
//? if 1.20.1 && fabric {
/*import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
*///? } else if fabric {
/*import net.minecraft.client.multiplayer.ClientLevel;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
*///? }

import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;
import xyz.kohara.stellarity.utils.NetworkingUtils;

//$ clientOnly
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public class StellarityClientNetworking {
    public static void init() {
        //? if 1.20.1 && fabric {
        /*ClientPlayNetworking.registerGlobalReceiver(S2CSetStellarityEntityDataPacket.ID, (client, handler, buf, responseSender) -> {
            var packet = S2CSetStellarityEntityDataPacket.unpack(buf);
            client.execute(() -> {
                Entity entity = client.level.getEntity(packet.id());
                if (entity == null) return;
                entity.stellarity$entityData().assignValues(packet.list());
            });
        });
        *///? } else if fabric {
        /*ClientPlayNetworking.registerGlobalReceiver(S2CSetStellarityEntityDataPacket.TYPE, (packet, context) -> {
            @SuppressWarnings("resource") ClientLevel world = context.client().level;
            if (world == null) return;

            Entity entity = world.getEntity(packet.id());
            if (entity == null) return;

            entity.stellarity$entityData().assignValues(packet.list());

        });
        *///? }
        NetworkingUtils.registerS2CReceiver(S2CSetStellarityEntityDataPacket.ID, (buf, context) -> {
            var packet = S2CSetStellarityEntityDataPacket.unpack(buf);
            // should run on the main thread
            Minecraft.getInstance().execute(() -> {
                Entity entity = Minecraft.getInstance().level.getEntity(packet.id());
                if (entity == null) return;
                entity.stellarity$entityData().assignValues(packet.list());
            });
        });
    }
}