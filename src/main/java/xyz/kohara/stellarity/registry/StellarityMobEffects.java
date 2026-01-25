package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.*;

public class StellarityMobEffects {
    private static final Registrar<MobEffect> MOB_EFFECTS = StellarityRegistries.MANAGER.get().get(Registries.MOB_EFFECT);
    
    public static final RegistrySupplier<VoidedEffect> VOIDED = register("voided", new VoidedEffect());
    public static final RegistrySupplier<JinxEffect> JINX = register("jinx", new JinxEffect());

    public static final RegistrySupplier<BrittleEffect> BRITTLE = register("brittle", new BrittleEffect());
    public static final RegistrySupplier<CreativeShockEffect> CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
    public static final RegistrySupplier<FrostburnEffect> FROSTBURN = register("frostburn", new FrostburnEffect());
    public static final RegistrySupplier<PrismaticInfernoEffect> PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


    public static <T extends MobEffect> RegistrySupplier<T> register(String name, T effect) {
        return MOB_EFFECTS.register(Stellarity.id(name), () -> effect);
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
    }
}
