package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.interface_injection.ExtEntity;
//? > 1.21 {
/*import net.minecraft.network.syncher.SyncedDataHolder;
 *///? }

@Mixin(Entity.class)
public abstract class EntityMixin implements ExtEntity
  //? > 1.21 {
  /*, SyncedDataHolder
   *///? }
{

  @Unique
  @Mutable
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
  @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;defineSynchedData()V", ordinal = 0))
  private void addSynchedData(EntityType<?> entityType, Level level, CallbackInfo ci) {
    entityData = new SynchedEntityData((Entity) (Object) this);
    stellarity$defineSynchedData();
  }

  @Override
  public void stellarity$defineSynchedData() {
    entityData.define(DATA_GLOW_COLOR, -1);
  }


  //?} else {
  /*@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;defineSynchedData(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V", ordinal = 0))
  private void addSynchedData(EntityType<?> entityType, Level level, CallbackInfo ci) {
    SynchedEntityData.Builder builder = new SynchedEntityData.Builder(this);
    stellarity$defineSynchedData(builder);
    entityData = builder.build();
  }

  @Override
  public void stellarity$defineSynchedData(SynchedEntityData.Builder builder) {
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

  @Override
  public SynchedEntityData stellarity$entityData() {
    return entityData;
  }
}
