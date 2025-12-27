package xyz.kohara.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import xyz.kohara.stellarity.Stellarity;
//? > 1.21.10 {

//? }

public class JinxEffect extends MobEffect {
    public JinxEffect() {
        super(MobEffectCategory.HARMFUL, 3801170);
        addAttributeModifier(Attributes.ARMOR, /*? 1.20.1 {*/ "0674fc9f-43fa-4249-bc43-6a4fcf414620" /*? } else { */ /*Stellarity.id("jinx_effect") *//*? } */, -0.2, AttributeModifier.Operation./*? 1.20.1 {*/ MULTIPLY_TOTAL /*? } else { */ /*ADD_MULTIPLIED_TOTAL *//*? } */);
    }


}
