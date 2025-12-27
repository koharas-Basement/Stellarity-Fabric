package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import xyz.kohara.stellarity.Stellarity;

public class StellaritySounds {
    public static final SoundEvent TAMARIS_EXECUTE = register("item.tamaris.execute");
    public static final SoundEvent TAMARIS_EXECUTE_SPECIAL = register("item.tamaris.execute_special");
    public static final SoundEvent TAMARIS_CHIME = register("item.tamaris.chime");
    public static final SoundEvent TAMARIS_EXECUTE_BG = register("item.tamaris.execute_bg");
    public static final SoundEvent TAMARIS_RAVE = register("item.tamaris.rave");

    private static SoundEvent register(String id) {
        var location = Stellarity.id(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createVariableRangeEvent(location));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Sounds");
    }
}
