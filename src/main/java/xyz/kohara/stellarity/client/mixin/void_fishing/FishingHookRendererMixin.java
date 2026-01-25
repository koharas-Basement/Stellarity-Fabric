//? <= 1.21.1 {
package xyz.kohara.stellarity.client.mixin.void_fishing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.kohara.stellarity.registry.StellarityItems;

@Mixin(FishingHookRenderer.class)
public class FishingHookRendererMixin {
    //? if 1.20.1 {
    //? if fabric {
    /*@WrapOperation(method = "getPlayerHandPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    public boolean addFisherOfVoids(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.is(StellarityItems.FISHER_OF_VOIDS) || original.call(instance, item);
    }
    *///? } else if forge {
    @WrapOperation(
        method = "render(Lnet/minecraft/world/entity/projectile/FishingHook;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;canPerformAction(Lnet/minecraftforge/common/ToolAction;)Z")
    )
    public boolean addFisherOfVoids(ItemStack instance, ToolAction toolAction, Operation<Boolean> original) {
        return instance.is(StellarityItems.FISHER_OF_VOIDS.get()) || original.call(instance, toolAction);
    }
    //? }
    //? } else if = 1.21.1 {
    /*@WrapOperation(method = "getPlayerHandPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean addFisherOfVoids(ItemStack instance, Item item, Operation<Boolean> original) {
        return instance.is(StellarityItems.FISHER_OF_VOIDS.get()) || original.call(instance, item);
    }
    *///? }
}
//?}
