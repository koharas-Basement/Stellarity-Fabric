package xyz.kohara.stellarity.registry.effect;

//? if 1.21.1 {
/*import net.minecraft.server.level.ServerLevel;
import xyz.kohara.stellarity.Stellarity;
import net.minecraft.tags.EntityTypeTags;
*///?} else {
import net.minecraft.world.entity.MobType;
//?}
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;

public class PrismaticInfernoEffect extends MobEffect {
    public PrismaticInfernoEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }
    
    @Override
    //? if 1.21.1 {
    /*public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
    *///?} else {
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
    //?}
        Level level = livingEntity.level();
        if (level.isClientSide) return /*? if >= 1.21.1 {*//*false *//*?}*/;
        boolean isInDaylight = level.canSeeSky(livingEntity.blockPosition()) &&
            level.dimension() == Level.OVERWORLD &&
            (!level.isRaining() && !level.isThundering() && level.isDay());
        //? if < 1.21.1 {
        boolean isUndead = livingEntity.getMobType() == MobType.UNDEAD;
        //?} else {
        /*boolean isUndead = livingEntity.getType().is(EntityTypeTags.UNDEAD);
         *///?}
        float damage = 1f;
        if (isUndead) damage *= 2;
        if (isInDaylight) damage *= 2;
        livingEntity.hurt(livingEntity.damageSources().source(StellarityDamageTypes.PRISMATIC_INFERNO), damage);
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