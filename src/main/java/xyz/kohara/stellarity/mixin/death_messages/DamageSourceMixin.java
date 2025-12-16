package xyz.kohara.stellarity.mixin.death_messages;

import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DamageSource.class)
public abstract class DamageSourceMixin {
  @Unique
  private final RandomSource random = RandomSource.create();

  @Shadow
  public abstract String getMsgId();

  @Expression("? + '.player'")
  @ModifyExpressionValue(method = "getLocalizedDeathMessage", at = @At("MIXINEXTRAS:EXPRESSION"))
  private String specialStellarityDeathMessages(String original, @Local(ordinal = 1) LivingEntity livingEntity) {
    String id = getMsgId();
    if (id.equals("stellarity.tamaris_execute")) {
      return "death.attack.stellarity.tamaris_execute." + random.nextInt(1, 4);
    }

    return original;
  }

}
