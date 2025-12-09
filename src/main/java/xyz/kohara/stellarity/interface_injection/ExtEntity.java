package xyz.kohara.stellarity.interface_injection;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

public interface ExtEntity {
  /**
   * ⚠️ any negative number means the color is not overriden
   */
  default void stellarity$setGlowColor(int color) {

  }

  /**
   * ⚠️ any negative number means the color is not overriden
   */
  default int stellarity$getGlowColor() {
    return -1;
  }

  EntityDataAccessor<Integer> DATA_GLOW_COLOR = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.INT);
}
