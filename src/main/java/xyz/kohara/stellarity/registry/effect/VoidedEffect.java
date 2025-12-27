package xyz.kohara.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import xyz.kohara.stellarity.Stellarity;
//? > 1.21.10 {
/*import net.minecraft.server.level.ServerLevel;
*///? }

public class VoidedEffect extends MobEffect {
  public VoidedEffect() {
    super(MobEffectCategory.HARMFUL, 3801170);
    addAttributeModifier(Attributes.MAX_HEALTH, /*? 1.20.1 {*/ "715179ff-98e5-4d18-8bf5-363e524fff76" /*? } else { */ /*Stellarity.id("voided_effect") *//*? } */, -0.2, AttributeModifier.Operation./*? 1.20.1 {*/ MULTIPLY_TOTAL /*? } else { */ /*ADD_MULTIPLIED_TOTAL *//*? } */);
  }


  @Override
  public boolean /*? 1.20.1 {*/ isDurationEffectTick /*? } else { */ /*shouldApplyEffectTickThisTick *//*? } */(int i, int j) {
    return true;
  }


  @Override
  public /*? 1.20.1 {*/ void /*? } else { */ /*boolean *//*? } */ applyEffectTick(/*? > 1.21.10 {*/ /*ServerLevel level, *//*? } */ LivingEntity livingEntity, int i) {
    if (livingEntity.getHealth() > livingEntity.getMaxHealth()) {
      livingEntity.setHealth(livingEntity.getMaxHealth());
    }

    /*? > 1.21 {*/
    /*return *//*? } */
      super.applyEffectTick(/*? > 1.21.10 {*/ /*level, *//*? } */livingEntity, i);
  }

}
