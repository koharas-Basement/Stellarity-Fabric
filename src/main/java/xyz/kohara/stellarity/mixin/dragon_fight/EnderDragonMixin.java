package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.expression.Expression;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragon.class)
public abstract class EnderDragonMixin extends Mob implements Enemy {
  @Shadow
  @Nullable
  private EndDragonFight dragonFight;

  protected EnderDragonMixin(EntityType<? extends Mob> entityType, Level level) {
    super(entityType, level);
  }


  //? < 1.21.9 {
  @Redirect(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;hurt(Lnet/minecraft/world/entity/boss/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
  private boolean blockDamage(EnderDragon instance, EnderDragonPart enderDragonPart, DamageSource damageSource, float f) {
    //? } else {
  /*@Redirect(method = "onCrystalDestroyed", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;hurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/boss/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
  private boolean blockDamage(EnderDragon instance, ServerLevel serverLevel, EnderDragonPart enderDragonPart, DamageSource damageSource, float v) {
    *///? }
    // squash - ender dragon doesn't take damage from crystals
    return false;
  }


  @Inject(method = "hurt(Lnet/minecraft/world/entity/boss/EnderDragonPart;Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At("HEAD"), cancellable = true)
  private void invulnerable(EnderDragonPart enderDragonPart, DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
    if (dragonFight != null && dragonFight.getCrystalsAlive() != 0) {
      cir.setReturnValue(false);
      cir.cancel();
    }

  }

}
