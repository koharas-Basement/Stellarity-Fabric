package xyz.kohara.stellarity.mixin.extend_classes;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.networking.S2CSetStellarityEntityDataPacket;

import java.util.List;
import java.util.function.Consumer;

@Mixin(ServerEntity.class)
public abstract class ServerEntityMixin {
  @Shadow
  @Final
  private Entity entity;

  @Shadow
  @Final
  private ServerLevel level;
  @Unique
  private List<SynchedEntityData.DataValue<?>> trackedDataValues;

  @Inject(method = "<init>", at = @At("TAIL"))
  private void init(ServerLevel serverLevel, Entity entity, int i, boolean bl, Consumer consumer, CallbackInfo ci) {
    trackedDataValues = entity.stellarity$entityData().getNonDefaultValues();
  }

  @Inject(method = "sendDirtyEntityData", at = @At("HEAD"))
  private void sendDirtyStellarityData(CallbackInfo ci) {
    SynchedEntityData synchedEntityData = entity.stellarity$entityData();
    List<SynchedEntityData.DataValue<?>> list = synchedEntityData.packDirty();
    if (list == null) return;
    trackedDataValues = synchedEntityData.getNonDefaultValues();
    if (trackedDataValues == null) return;
    for (var serverPlayer : level.players()) {
      var packet = new S2CSetStellarityEntityDataPacket(entity.getId(), list);
      ServerPlayNetworking.send(serverPlayer, S2CSetStellarityEntityDataPacket.ID, packet.pack());

    }
  }

  @Inject(method = "addPairing", at = @At("TAIL"))
  private void sendStellarityPairingData(ServerPlayer serverPlayer, CallbackInfo ci) {
    if (trackedDataValues != null) {
      var packet = new S2CSetStellarityEntityDataPacket(entity.getId(), trackedDataValues);
      ServerPlayNetworking.send(serverPlayer, S2CSetStellarityEntityDataPacket.ID, packet.pack());

    }
  }
}
