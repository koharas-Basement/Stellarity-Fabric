package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.*;

public class StellarityMobEffects {
    public static final MobEffect VOIDED = register("voided", new VoidedEffect());
    public static final MobEffect JINX = register("jinx", new JinxEffect());

    public static final MobEffect BRITTLE = register("brittle", new BrittleEffect());
    public static final MobEffect CREATIVE_SHOCK = register("creative_shock", new CreativeShockEffect());
    public static final MobEffect FROSTBURN = register("frostburn", new FrostburnEffect());
    public static final MobEffect PRISMATIC_INFERNO = register("prismatic_inferno", new PrismaticInfernoEffect());


    public static MobEffect register(String name, MobEffect effect) {
        return Registry.register(BuiltInRegistries.MOB_EFFECT, Stellarity.id(name), effect);
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
    }
}
