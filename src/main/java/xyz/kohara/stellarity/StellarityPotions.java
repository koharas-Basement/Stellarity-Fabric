package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import java.util.HashMap;

public class StellarityPotions {
  public static final HashMap<Potion, Integer> COLORS = new HashMap<>(){{
    put(BLIND_RAGE, 7230976);
  }};

  public static final Potion BLIND_RAGE = register("blind_rage", "blind_rage",
    new MobEffectInstance(MobEffects.DARKNESS, 15*20, 0),
    new MobEffectInstance(MobEffects.DAMAGE_BOOST, 15*20, 2));

  private static Potion register(String id, String name, MobEffectInstance ...effects) {
    return Registry.register(BuiltInRegistries.POTION, Stellarity.of(id), new Potion("stellarity." + name, effects));
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Potions");
  }
}
