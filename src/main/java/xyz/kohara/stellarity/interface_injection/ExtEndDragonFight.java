package xyz.kohara.stellarity.interface_injection;

import net.minecraft.core.BlockPos;

public interface ExtEndDragonFight {
    default boolean stellarity$dragonKilled() {
        throw new AssertionError("Not transformed!");
    }

    default BlockPos stellarity$getPortalLocation() {
        throw new AssertionError("Not transformed!");
    }
}
