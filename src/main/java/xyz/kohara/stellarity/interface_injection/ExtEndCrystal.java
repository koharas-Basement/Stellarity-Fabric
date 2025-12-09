package xyz.kohara.stellarity.interface_injection;

public interface ExtEndCrystal {
  enum Type {
    NORMAL,
    RESPAWN,
    VAULT,
  }

  default Type stellarity$getType() {
    return null;
  }


  default void stellarity$setType(Type type) {

  }


}
