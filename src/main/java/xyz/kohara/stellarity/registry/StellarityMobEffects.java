package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.effect.JinxEffect;
import xyz.kohara.stellarity.registry.effect.VoidedEffect;

public class StellarityMobEffects {
	public static final MobEffect VOIDED = register("voided", new VoidedEffect());
	public static final MobEffect JINX = register("jinx", new JinxEffect());


	public static MobEffect register(String name, MobEffect effect) {
		return Registry.register(BuiltInRegistries.MOB_EFFECT, Stellarity.id(name), effect);
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Mob Effects");
	}
}
