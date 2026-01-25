package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import xyz.kohara.stellarity.Stellarity;

public class StellaritySounds {
    private static final Registrar<SoundEvent> SOUND_EVENTS = StellarityRegistries.MANAGER.get().get(Registries.SOUND_EVENT);
    
    public static final RegistrySupplier<SoundEvent> TAMARIS_EXECUTE = register("item.tamaris.execute");
    public static final RegistrySupplier<SoundEvent> TAMARIS_EXECUTE_SPECIAL = register("item.tamaris.execute_special");
    public static final RegistrySupplier<SoundEvent> TAMARIS_CHIME = register("item.tamaris.chime");
    public static final RegistrySupplier<SoundEvent> TAMARIS_EXECUTE_BG = register("item.tamaris.execute_bg");
    public static final RegistrySupplier<SoundEvent> TAMARIS_RAVE = register("item.tamaris.rave");
    
    public static final RegistrySupplier<SoundEvent> PRISMATIC_PEARL_THROW = register("item.prismatic_pearl.throw");

    private static RegistrySupplier<SoundEvent> register(String id) {
        var location = Stellarity.id(id);
        return SOUND_EVENTS.register(location, () -> SoundEvent.createVariableRangeEvent(location));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Sounds");
    }
}
