package xyz.kohara.stellarity.registry.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import xyz.kohara.stellarity.registry.StellarityItems;

//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

*///?}

public class ShulkerBody extends TeleportingFood {
    private static final int TELEPORT_DIAMETER = 16;

    public ShulkerBody(Properties properties) {
        super(properties, TELEPORT_DIAMETER);
    }

    public static Properties properties() {
        var hunger = new MobEffectInstance(MobEffects.HUNGER, 40 * 20, 0);

        return TeleportingFood.foodProperties(4, 0.8f, true, TELEPORT_DIAMETER, new StellarityItems.EffectChance(new MobEffectInstance(MobEffects.HUNGER, 40 * 20, 0), 0.3f));
    }
}