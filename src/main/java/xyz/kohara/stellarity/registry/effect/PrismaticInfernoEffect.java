package xyz.kohara.stellarity.registry.effect;

//? > 1.21 {

/*import net.minecraft.tags.EntityTypeTags;
*///?} else {

import net.minecraft.world.entity.MobType;
 //?}
//? > 1.21.9 {
/*import net.minecraft.server.level.ServerLevel;
    *///? }
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;

public class PrismaticInfernoEffect extends MobEffect {
    public PrismaticInfernoEffect() {
        super(MobEffectCategory.HARMFUL, 0xff4d00);
    }


    @Override

    public /*? > 1.21 {*/ /*boolean *//*? } else { */ void /*? } */ applyEffectTick(/*? > 1.21.9 {*/ /*ServerLevel serverLevel,  *//*? } */LivingEntity livingEntity, int amplifier) {

        Level level = livingEntity.level();
        if (level.isClientSide()) return /*? if > 1.21 {*//*false *//*?}*/;
        boolean isInDaylight = level.canSeeSky(livingEntity.blockPosition()) &&
            level.dimension() == Level.OVERWORLD &&
            (!level.isRaining() && !level.isThundering() && level./*? < 1.21.9 {*/ isDay() /*? } else { */ /*isBrightOutside() *//*? } */);
        //? if < 1.21.1 {
        boolean isUndead = livingEntity.getMobType() == MobType.UNDEAD;
         //?} else {
        /*boolean isUndead = livingEntity.getType().is(EntityTypeTags.UNDEAD);
        *///?}
        float damage = 1f;
        if (isUndead) damage *= 2;
        if (isInDaylight) damage *= 2;

        livingEntity./*? < 1.21.9 {*/ hurt( /*? } else { */ /*hurtServer(serverLevel, *//*? }*/livingEntity.damageSources().source(StellarityDamageTypes.PRISMATIC_INFERNO), damage);
        /*? if >= 1.21.1 {*/
        /*return true; *//*?}*/
    }

    @Override
        //? < 1.21.1
    public boolean isDurationEffectTick(int duration, int amplifier) {
        //? >= 1.21.1
    //public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}