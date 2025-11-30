//? >= 1.21 {
/*package xyz.kohara.stellarity.mixin.potions;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import xyz.kohara.stellarity.StellarityPotions;

import java.util.Optional;
//? < 1.21.9 {
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.Inject;
//? } else {
/^import java.util.OptionalInt;
^///? }

@Mixin(PotionContents.class)
public abstract class PotionContentsMixin {

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  @Shadow
  @Final
  private Optional<Holder<Potion>> potion;

  //? < 1.21.9 {
  @Shadow
  public static int getColor(Iterable<MobEffectInstance> iterable) {
    return 0;
  }

  @Inject(method = "getColor(Lnet/minecraft/core/Holder;)I", at=@At("HEAD"), cancellable = true)
  private static void getColorPotion(Holder<Potion> holder, CallbackInfoReturnable<Integer> cir) {
    Integer color = StellarityPotions.COLORS.get(holder.value());
    if (color == null) return;

    cir.setReturnValue(color);
    cir.cancel();
  }

  @Redirect(method = "getColor()I", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionContents;getColor(Ljava/lang/Iterable;)I"))
  private int getColorThis(Iterable<MobEffectInstance> iterable) {
    if (potion.isPresent()) {
      Integer color = StellarityPotions.COLORS.get(potion.get().value());
      if (color != null) return color;
    }

    return getColor(iterable);
  }

  //? } else {

  /^@Shadow
  public static OptionalInt getColorOptional(Iterable<MobEffectInstance> iterable) {
    return OptionalInt.empty();
  }

  @Redirect(method = "getColorOr", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/item/alchemy/PotionContents;getColorOptional(Ljava/lang/Iterable;)Ljava/util/OptionalInt;"))
  private OptionalInt getColorThis(Iterable<MobEffectInstance> iterable) {
    if (potion.isPresent()) {
      Integer color = StellarityPotions.COLORS.get(potion.get().value());
      if (color != null) return OptionalInt.of(color);
    }

    return getColorOptional(iterable);
  }

  ^///? }
}
*///? }