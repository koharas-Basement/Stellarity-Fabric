package xyz.kohara.stellarity.interface_injection;

import net.minecraft.core.BlockPos;

public interface ExtEndDragonFight {
  default boolean stellarity$dragonKilled() {
    return false;
  }

  default BlockPos stellarity$getPortalLocation() {
    return null;
  }
}
