package xyz.kohara.stellarity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;


public class StellarityDamageTypes {
  public static final ResourceKey<DamageType> TAMARIS_EXECUTE = ResourceKey.create(Registries.DAMAGE_TYPE, Stellarity.id("tamaris_execute"));
}
