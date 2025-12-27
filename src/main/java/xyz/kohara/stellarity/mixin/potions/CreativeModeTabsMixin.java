package xyz.kohara.stellarity.mixin.potions;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.alchemy.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Mixin(CreativeModeTabs.class)
public class CreativeModeTabsMixin {
    @WrapOperation(method = "generatePotionEffectTypes", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;filter(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;"))
    private static Stream<Holder.Reference<Potion>> removeStellarityPotions(Stream<Holder.Reference<Potion>> instance, Predicate<? super Holder.Reference<Potion>> predicate, Operation<Stream<Holder.Reference<Potion>>> original) {
        return original.call(instance, predicate).filter((potion) -> !potion.key()./*? <1.21.11 {*/ location/*?} else {*//*identifier *//*? }*/().getNamespace().equals("stellarity") && predicate.test(potion));
    }
}
