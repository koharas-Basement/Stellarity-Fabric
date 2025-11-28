package xyz.kohara.stellarity.mixin.datagen_patch;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Advancement.Builder.class)
public class AdvancementBuilderMixin {
  //? = 1.20.1 {
  @Inject(method = "canBuild", at=@At("HEAD"), cancellable = true)
  private void forceTrue(Function<ResourceLocation, Advancement> function, CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(true);
    cir.cancel();
  }
  //? }
}
