package xyz.kohara.stellarity.utils;

import dev.architectury.networking.NetworkManager;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class NetworkingUtils {
    /**
     * this may sound wild but this is the client method
     */
    public static void registerS2CReceiver(ResourceLocation id, NetworkManager.NetworkReceiver receiver) {
        if (Platform.getEnvironment() == Env.CLIENT) throw new IllegalStateException("Client method accessed from server!");
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, id, receiver);
    }
    
    public static void registerC2SReceiver(ResourceLocation id, NetworkManager.NetworkReceiver receiver) {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, id, receiver);
    }
    
    public static void sendPacketS2C(ServerPlayer player, ResourceLocation id, FriendlyByteBuf buf) {
        NetworkManager.sendToPlayer(player, id, buf);
    }
    
    public static void sendPacketC2S(ResourceLocation id, FriendlyByteBuf buf) {
        if (Platform.getEnvironment() == Env.CLIENT) throw new IllegalStateException("Client method accessed from server!");
        NetworkManager.sendToServer(id, buf);
    }
}
