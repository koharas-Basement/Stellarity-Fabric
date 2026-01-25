package xyz.kohara.stellarity.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import xyz.kohara.stellarity.Stellarity;

import java.util.function.Supplier;

class StellarityRegistries {
    static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(Stellarity.MOD_ID));
}
