package xyz.kohara.stellarity.mixin.dragon_fight;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonHoldingPatternPhase;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DragonHoldingPatternPhase.class)
public abstract class DragonHoldingPatternPhaseMixin extends AbstractDragonPhaseInstance {
  public DragonHoldingPatternPhaseMixin(EnderDragon enderDragon) {
    super(enderDragon);
  }

  @Definition(id = "dragon", field = "Lnet/minecraft/world/entity/boss/enderdragon/phases/DragonHoldingPatternPhase;dragon:Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;")
  @Definition(id = "getRandom", method = "Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;getRandom()Lnet/minecraft/util/RandomSource;")
  @Expression("this.dragon.getRandom().?(?) == 0")
  @ModifyExpressionValue(method = "findNewTarget", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
  private boolean landWhenCrystalsGone(boolean original, @Local int i) {
    return i == 0;
  }
}
