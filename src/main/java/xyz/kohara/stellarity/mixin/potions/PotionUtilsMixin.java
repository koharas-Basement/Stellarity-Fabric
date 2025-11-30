package xyz.kohara.stellarity.mixin.potions;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.StellarityPotions;

@Mixin(PotionUtils.class)
public abstract class PotionUtilsMixin {
  @Inject(method = "getColor(Lnet/minecraft/world/item/alchemy/Potion;)I", at = @At("HEAD"), cancellable = true)
  private static void getColor(Potion potion, CallbackInfoReturnable<Integer> cir) {
    Integer color = StellarityPotions.COLORS.get(potion);
    if (color == null) return;

    cir.setReturnValue(color);
    cir.cancel();
  }
}
