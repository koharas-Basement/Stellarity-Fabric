package xyz.kohara.stellarity.interface_injection;

public interface ExtEndCrystal {
  enum Type {
    NORMAL,
    RESPAWN,
    END_CITY,
  }

  default Type stellarity$getType() {
    throw new AssertionError("Not transformed!");
  }


  default void stellarity$setType(Type type) {
    throw new AssertionError("Not transformed!");
  }


}
