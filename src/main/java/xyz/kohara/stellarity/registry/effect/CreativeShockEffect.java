package xyz.kohara.stellarity.registry.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class CreativeShockEffect extends MobEffect {
    //TODO maybe use a config for it or something
    public boolean extremeCreativeShock() {
        return true;
    }

    public CreativeShockEffect() {
        super(MobEffectCategory.HARMFUL, 0xFD3DB5);
    }
}