package xyz.kohara.stellarity;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;

import java.util.HashMap;

public class StellarityPotions {
  public static final HashMap<Potion, Integer> COLORS = new HashMap<>();

  public static final Potion BLIND_RAGE = register("blind_rage", "blind_rage",
    new MobEffectInstance(MobEffects.DARKNESS, 15 * 20, 0),
    new MobEffectInstance(
      //? < 1.21.9 {
      MobEffects.DAMAGE_BOOST
      //? } else {
      /*MobEffects.STRENGTH
      *///? }
      , 15 * 20, 2));

  //? > 1.21 {
  /*public static final Holder<Potion> BLIND_RAGE_HOLDER = BuiltInRegistries.POTION.wrapAsHolder(BLIND_RAGE);

  *///? }


  private static Potion register(String id, String name, MobEffectInstance... effects) {
    return Registry.register(BuiltInRegistries.POTION, Stellarity.of(id), new Potion("stellarity." + name, effects));
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Potions");
    COLORS.put(BLIND_RAGE, 7230976);
  }
}
