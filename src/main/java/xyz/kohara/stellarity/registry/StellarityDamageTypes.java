package xyz.kohara.stellarity.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import xyz.kohara.stellarity.Stellarity;

import static net.minecraft.core.registries.Registries.DAMAGE_TYPE;


public class StellarityDamageTypes {
    //DOT
    public static final ResourceKey<DamageType> FROSTBURN = create("dot/frostburn");
    public static final ResourceKey<DamageType> PRISMATIC_INFERNO = create("dot/prismatic_inferno");
    //EOL
    public static final ResourceKey<DamageType> EOL_DASH = create("empress_of_light/dash");
    public static final ResourceKey<DamageType> EOL_PROJECTILE = create("empress_of_light/projectile");
    //AP
    public static final ResourceKey<DamageType> ARMOR_PIERCING = create("armor_piercing");
    public static final ResourceKey<DamageType> ARMOR_PIERCING_SCALING = create("armor_piercing_difficulty_scaling");
    public static final ResourceKey<DamageType> ARMOR_PIERCING_NO_KB = create("armor_piercing_no_knockback");
    //true damage
    public static final ResourceKey<DamageType> TRUE_DAMAGE = create("true_damage");
    public static final ResourceKey<DamageType> TRUE_DAMAGE_SCALING = create("true_damage_difficulty_scaling");
    public static final ResourceKey<DamageType> TRUE_DAMAGE_RESPECTS_RESISTANCE = create("true_damage_respects_resistance");
    //literally everything else
    public static final ResourceKey<DamageType> BLOOM = create("bloom");
    public static final ResourceKey<DamageType> CARCANET = create("carcanet");
    public static final ResourceKey<DamageType> DRAGONBLADE = create("dragonblade");
    public static final ResourceKey<DamageType> ECHO = create("echo");
    public static final ResourceKey<DamageType> ELECTRIC = create("electric");
    public static final ResourceKey<DamageType> EXAMPLE = create("example");
    public static final ResourceKey<DamageType> FLUFFY_SLICE = create("example");
    public static final ResourceKey<DamageType> IGNORES_IFRAMES = create("ignores_iframes");
    public static final ResourceKey<DamageType> KALEIDOSCOPE = create("kaleidoscope");
    public static final ResourceKey<DamageType> NATURES_WRATH = create("natures_wrath");
    public static final ResourceKey<DamageType> NO_KNOCKBACK = create("no_knockback");
    public static final ResourceKey<DamageType> NO_KNOCKBACK_IGNORES_IFRAMES = create("no_knockback_ignores_iframes");
    public static final ResourceKey<DamageType> PRISMEMBER = create("prismember");
    public static final ResourceKey<DamageType> SIMULATED_EXPLOSION = create("simulated_explosion");
    public static final ResourceKey<DamageType> TAMARIS_EXECUTE = create("tamaris_execute");
    
    private static ResourceKey<DamageType> create(String string) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Stellarity.id(string));
    }
}
