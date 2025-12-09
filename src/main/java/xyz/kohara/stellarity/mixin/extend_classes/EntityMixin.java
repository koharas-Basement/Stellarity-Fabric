package xyz.kohara.stellarity.mixin.extend_classes;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.interface_injection.ExtEntity;

@Mixin(Entity.class)
public class EntityMixin implements ExtEntity {

  @Shadow
  @Final
  protected SynchedEntityData entityData;

  @Override
  public int stellarity$getGlowColor() {
    return entityData.get(DATA_GLOW_COLOR);
  }

  @Override
  public void stellarity$setGlowColor(int color) {
    entityData.set(DATA_GLOW_COLOR, color);
  }

  //? 1.20.1 {
  @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/syncher/SynchedEntityData;define(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)V", ordinal = 0))
  private void addSynchedData(EntityType<?> entityType, Level level, CallbackInfo ci) {
    entityData.define(DATA_GLOW_COLOR, -1);
  }

  //?} else {
  /*@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/syncher/SynchedEntityData$Builder;define(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;)Lnet/minecraft/network/syncher/SynchedEntityData$Builder;", ordinal = 0))
  private void addSynchedData(EntityType<?> entityType, Level level, CallbackInfo ci, @Local SynchedEntityData.Builder builder) {
    builder.define(DATA_GLOW_COLOR, -1);
  }
  *///?}

  @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
  private void customGlowColor(CallbackInfoReturnable<Integer> cir) {
    int color = stellarity$getGlowColor();
    if (color < 0) return;

    cir.setReturnValue(color);
    cir.cancel();
  }

}
