package xyz.kohara.stellarity.interface_injection;

public interface ExtFishingHook {
	default void stellarity$buffVoidFishing(boolean buff) {
		throw new AssertionError("Not transformed!");
	}
}
