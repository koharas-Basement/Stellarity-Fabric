package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import static xyz.kohara.stellarity.registry.StellarityDamageTypes.*;

public class StellarityDamageSources {
    private final Registry<DamageType> damageTypes;
    private final DamageSource frostburn;
    private final DamageSource prismaticInferno;
    
    private DamageSource getDamageSource(ResourceKey<DamageType> damageType, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
        return new DamageSource(
            damageTypes.getHolderOrThrow(damageType),
            directEntity,
            causingEntity
        );
    }
    
    private DamageSource getDamageSource(ResourceKey<DamageType> damageType, @Nullable Entity entity) {
        return getDamageSource(damageType, entity, entity);
    }
    
    private DamageSource getDamageSource(ResourceKey<DamageType> damageType) {
        return getDamageSource(damageType, null, null);
    }
    
    public StellarityDamageSources(RegistryAccess registryAccess) {
        damageTypes = registryAccess.registryOrThrow(Registries.DAMAGE_TYPE);
        frostburn = getDamageSource(FROSTBURN);
        prismaticInferno = getDamageSource(PRISMATIC_INFERNO);
    }
    
    //DOT damage sources
    public DamageSource frostburn() {
        return frostburn;
    }
    
    public DamageSource prismaticInferno() {
        return prismaticInferno;
    }
    
    //TODO
    //EOL damage sources
    public DamageSource eolDash(LivingEntity eol) {
        return getDamageSource(EOL_DASH, eol);
    }
    
    public DamageSource eolProjectile(LivingEntity eol, LivingEntity projectile) {
        return getDamageSource(EOL_PROJECTILE, projectile, eol);
    }
    
    //AP damage sources
    public DamageSource armorPiercing(Entity directEntity, Entity causingEntity) {
        return getDamageSource(ARMOR_PIERCING, directEntity, causingEntity);
    }
    
    public DamageSource armorPiercing(Entity entity) {
        return getDamageSource(ARMOR_PIERCING, entity);
    }
    
    public DamageSource armorPiercingScaling(Entity directEntity, Entity causingEntity) {
        return getDamageSource(ARMOR_PIERCING_SCALING, directEntity, causingEntity);
    }
    
    public DamageSource armorPiercingScaling(Entity entity) {
        return getDamageSource(ARMOR_PIERCING_SCALING, entity);
    }
    
    public DamageSource armorPiercingNoKB(Entity directEntity, Entity causingEntity) {
        return getDamageSource(ARMOR_PIERCING_NO_KB, directEntity, causingEntity);
    }
    
    public DamageSource armorPiercingNoKB(Entity entity) {
        return getDamageSource(ARMOR_PIERCING_NO_KB, entity);
    }
    
    //"true damage" damage sources
    public DamageSource trueDamage(Entity directEntity, Entity causingEntity) {
        return getDamageSource(TRUE_DAMAGE, directEntity, causingEntity);
    }
    
    public DamageSource trueDamage(Entity entity) {
        return getDamageSource(TRUE_DAMAGE, entity);
    }
    
    public DamageSource trueDamageScaling(Entity directEntity, Entity causingEntity) {
        return getDamageSource(TRUE_DAMAGE_SCALING, directEntity, causingEntity);
    }
    
    public DamageSource trueDamageScaling(Entity entity) {
        return getDamageSource(TRUE_DAMAGE_SCALING, entity);
    }
    
    public DamageSource trueDamageRespectsResistance(Entity directEntity, Entity causingEntity) {
        return getDamageSource(TRUE_DAMAGE_RESPECTS_RESISTANCE, directEntity, causingEntity);
    }
    
    public DamageSource trueDamageRespectsResistance(Entity entity) {
        return getDamageSource(TRUE_DAMAGE_RESPECTS_RESISTANCE, entity);
    }
    
    //TODO
    //everything else
    public DamageSource bloom(LivingEntity entity) {
        return getDamageSource(BLOOM, entity);
    }
    
    public DamageSource carcanet(LivingEntity entity) {
        return getDamageSource(CARCANET, entity);
    }
    
    public DamageSource carcanet(Entity directEntity, LivingEntity causingEntity) {
        return getDamageSource(CARCANET, directEntity, causingEntity);
    }
    
    public DamageSource dragonblade(LivingEntity entity) {
        return getDamageSource(DRAGONBLADE, entity);
    }
    
    public DamageSource echo(LivingEntity entity) {
        return getDamageSource(ECHO, entity);
    }
    
    public DamageSource electric(LivingEntity entity) {
        return getDamageSource(ELECTRIC, entity);
    }
    
    public DamageSource example(Entity entity) {
        return getDamageSource(EXAMPLE, entity);
    }
    
    public DamageSource fluffySlice(LivingEntity entity) {
        return getDamageSource(FLUFFY_SLICE, entity);
    }
    
    public DamageSource ignoresIframes(Entity entity) {
        return getDamageSource(IGNORES_IFRAMES, entity);
    }
    
    public DamageSource ignoresIframes(Entity directEntity, Entity causingEntity) {
        return getDamageSource(IGNORES_IFRAMES, directEntity, causingEntity);
    }
    
    public DamageSource kaleidoscope(LivingEntity entity) {
        return getDamageSource(KALEIDOSCOPE, entity);
    }
    
    public DamageSource naturesWrath(LivingEntity entity) {
        return getDamageSource(NATURES_WRATH, entity);
    }
    
    public DamageSource noKnockback(Entity entity) {
        return getDamageSource(NO_KNOCKBACK, entity);
    }
    
    public DamageSource noKnockback(Entity directEntity, Entity causingEntity) {
        return getDamageSource(NO_KNOCKBACK, directEntity, causingEntity);
    }
    
    public DamageSource noKnockbackIgnoresIFrames(Entity entity) {
        return getDamageSource(NO_KNOCKBACK_IGNORES_IFRAMES, entity);
    }
    
    public DamageSource noKnockbackIgnoresIFrames(Entity directEntity, Entity causingEntity) {
        return getDamageSource(NO_KNOCKBACK_IGNORES_IFRAMES, directEntity, causingEntity);
    }
    
    public DamageSource prismember(LivingEntity entity) {
        return getDamageSource(PRISMEMBER, entity);
    }
    
    public DamageSource simulatedExplosion(Entity entity) {
        return getDamageSource(SIMULATED_EXPLOSION, entity);
    }
    
    public DamageSource tamarisExecute(LivingEntity entity) {
        return getDamageSource(TAMARIS_EXECUTE, entity);
    }
}