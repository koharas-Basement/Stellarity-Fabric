//? <= 1.21.1 {
package xyz.kohara.stellarity.client.mixin.void_fishing;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.kohara.stellarity.StellarityItems;

@Mixin(FishingHookRenderer.class)
@MixinEnvironment("client")
public class FishingHookRendererMixin {

  //? = 1.20.1
  @Redirect(method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
  /**/
  //? = 1.21.1
  //@Redirect(method = "getPlayerHandPos",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))

  private boolean addFisherOfVoids(ItemStack instance, Item item) {
    return instance.is(StellarityItems.FISHER_OF_VOIDS) || instance.is(item);
  }
}
//?}
