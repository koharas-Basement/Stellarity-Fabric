package xyz.kohara.stellarity.mixin.void_fishing;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.kohara.stellarity.StellarityItems;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {
  @Redirect(method = "use", at = @At(value = "NEW", target = "(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;II)Lnet/minecraft/world/entity/projectile/FishingHook;"))
  private FishingHook markVoidFishing(Player player, Level level, int i, int j, @Local ItemStack itemStack) {
    FishingHook hook = new FishingHook(player, level, i, j);

    if (itemStack.is(StellarityItems.FISHER_OF_VOIDS)) {

      hook.stellarity$buffVoidFishing(true);
    }

    return hook;
  }
}
