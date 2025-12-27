package xyz.kohara.stellarity.registry.effect;

//? if >= 1.21.1 {
/*import net.minecraft.server.level.ServerLevel;
*///?}
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import xyz.kohara.stellarity.utils.CastingUtil;

public class FrostburnEffect extends MobEffect {
    public FrostburnEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }
    
    @Override
    //? if 1.21.1 {
    /*public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
     *///?} else {
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        //?}
        if (livingEntity.level().isClientSide) return /*? if >= 1.21.1 {*//*false *//*?}*/;
        float damage = 1f;
        if (livingEntity.getType().is(EntityTypeTags.FREEZE_HURTS_EXTRA_TYPES)) damage *= 2;
        livingEntity.hurt(CastingUtil.damageSources(livingEntity.damageSources()).stellarity$stellaritySources().frostburn(), damage);
        /*? if >= 1.21.1 {*//*return true; *//*?}*/
    }
    
    @Override
    //? < 1.21.1
    public boolean isDurationEffectTick(int duration, int amplifier) {
        //? >= 1.21.1
        /*public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {*/
        return duration % 20 == 0;
    }
}