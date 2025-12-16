package xyz.kohara.stellarity;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import java.util.Optional;


public class StellarityDamageTypes {
  public static final ResourceKey<DamageType> TAMARIS_EXECUTE = ResourceKey.create(Registries.DAMAGE_TYPE, Stellarity.of("tamaris_execute"));
}
