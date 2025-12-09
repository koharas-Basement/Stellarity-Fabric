package xyz.kohara.stellarity.mixin.exit_portal;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtEndCrystal;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;

import java.util.List;

@Mixin(EndDragonFight.class)
public abstract class EndDragonFightMixin implements ExtEndDragonFight {
  @Shadow
  @Final
  private ServerLevel level;

  @Shadow
  @Nullable
  private BlockPos portalLocation;

  @Redirect(method = "tryRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;relative(Lnet/minecraft/core/Direction;I)Lnet/minecraft/core/BlockPos;"))
  private BlockPos adjustPosition(BlockPos blockPos, Direction direction, int i) {
    return blockPos.relative(direction, 4).above(2);
  }

  @Override
  public BlockPos stellarity$getPortalLocation() {
    return portalLocation;
  }
}
