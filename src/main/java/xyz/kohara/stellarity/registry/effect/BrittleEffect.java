package xyz.kohara.stellarity.registry.effect;

//? if >= 1.21.1 {
/*import net.minecraft.server.level.ServerLevel;
 *///?}
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;
import xyz.kohara.stellarity.utils.DamageUtility;

public class BrittleEffect extends MobEffect {
    public BrittleEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }
    
    private DamageUtility damageUtility;
    
    @Override
    //? if 1.21.1 {
    /*public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
    *///?} else {
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    //?}
        if (livingEntity.level().isClientSide) return /*? if >= 1.21.1 {*/ /*false *//*?}*/;
        if (damageUtility == null) damageUtility = initDamageUtility(livingEntity.damageSources());
        if (livingEntity.hurtTime != 9) return /*? if >= 1.21.1 {*/ /*false *//*?}*/;
        damageUtility.damageEntity(livingEntity, (float) amplifier + 1);
        livingEntity.hurtTime = 8;
        /*? if >= 1.21.1 {*/ /*return true; *//*?}*/
    }
    
    private DamageUtility initDamageUtility(DamageSources sources) {
        return DamageUtility.builder()
            .setDamageSource(sources.source(StellarityDamageTypes.NO_KNOCKBACK_IGNORES_IFRAMES))
            .setApDamageSource(sources.source(StellarityDamageTypes.ARMOR_PIERCING_NO_KB))
            .setApRatio(0.4f)
            .build();
    }
    
    @Override
    //? < 1.21.1
    public boolean isDurationEffectTick(int duration, int amplifier) {
    //? >= 1.21.1
    /*public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {*/
        return true;
    }
}