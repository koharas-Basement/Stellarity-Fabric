package xyz.kohara.stellarity.interface_injection;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;

public interface ExtEntity {
  /**
   * ⚠️ any negative number means the color is not overriden
   */
  default void stellarity$setGlowColor(int color) {
    throw new AssertionError("Not transformed!");
  }

  default SynchedEntityData stellarity$entityData() {
    throw new AssertionError("Not transformed!");
  }

  /**
   * ⚠️ any negative number means the color is not overriden
   */
  default int stellarity$getGlowColor() {
    throw new AssertionError("Not transformed!");
  }


  /**
   * When defining synched data, make sure the accessors are indexed correctly with parent class offsets if needed
   */
  default void stellarity$defineSynchedData(
    //? > 1.21
    ArrayList<SynchedEntityData.DataItem<?>> dataItems
  ) {

  }

  // change this accordingly! when subclassing, find the first entity that declares this
  int stellarity$DATA_SIZE = 1;

  EntityDataAccessor<Integer> DATA_GLOW_COLOR = new EntityDataAccessor<>(0, EntityDataSerializers.INT);
}
