package xyz.kohara.stellarity.mixin.dragon_fight;

import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.kohara.stellarity.interface_injection.ExtEndDragonFight;

@Mixin(EndDragonFight.class)
public class EndDragonFightMixin implements ExtEndDragonFight {
  @Shadow
  private boolean dragonKilled;

  @Override
  public boolean stellarity$dragonKilled() {
    return dragonKilled;
  }
}
