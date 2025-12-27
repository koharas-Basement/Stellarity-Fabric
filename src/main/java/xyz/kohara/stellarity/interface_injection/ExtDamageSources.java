package xyz.kohara.stellarity.interface_injection;

import org.jetbrains.annotations.ApiStatus;
import xyz.kohara.stellarity.registry.StellarityDamageSources;

@ApiStatus.Internal
public interface ExtDamageSources {
    default StellarityDamageSources stellarity$stellaritySources() {
        throw new AssertionError("Not transformed!");
    }
}