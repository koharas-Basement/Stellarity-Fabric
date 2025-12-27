package xyz.kohara.stellarity.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.Stellarity;

import static net.minecraft.core.registries.Registries.DAMAGE_TYPE;


public class StellarityDamageTypes {
	public static final ResourceKey<DamageType> TAMARIS_EXECUTE = Stellarity.key(DAMAGE_TYPE, "tamaris_execute");
}
