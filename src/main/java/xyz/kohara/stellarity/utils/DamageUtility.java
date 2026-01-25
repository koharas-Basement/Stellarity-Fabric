package xyz.kohara.stellarity.utils;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.Nullable;
//? if 1.20.1 {
import java.util.UUID;
//? } else {
/*import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;
*///? }


public final class DamageUtility {
    private final DamageSource damageSource;
    private final DamageSource apDamageSource;
    private final float apRatio;
    private final float damageBoostEfficiency;

    private DamageUtility(Builder builder) {
        if (builder.damageSource == null) throw new NullPointerException("Damage source is null");
        damageSource = builder.damageSource;
        apDamageSource = builder.apDamageSource;
        apRatio = builder.apRatio;
        damageBoostEfficiency = builder.damageBoostEfficiency;
    }

    private DamageUtility(DamageUtility other) {
        damageSource = other.damageSource;
        apDamageSource = other.apDamageSource;
        apRatio = other.apRatio;
        damageBoostEfficiency = other.damageBoostEfficiency;
    }

    /**
     * convenience method
     */
    public static void damageEntity(LivingEntity entity, float damage, float apRatio, float damageBoostEfficiency, DamageSource damageSource, @Nullable DamageSource apDamageSource) {
        DamageUtility.builder()
            .setApDamageSource(apDamageSource)
            .setDamageSource(damageSource)
            .setApRatio(apRatio)
            .setDamageBoostEfficiency(damageBoostEfficiency)
            .build()
            .damageEntity(entity, damage);
    }

    public void damageEntity(LivingEntity entity, float damage) {
        float damageWithBonus = calculateDamageBoostEfficiency(entity, damage, damageBoostEfficiency);
        ModifiableArg<Float> actualNonAPDamage = new ModifiableArg<>(damageWithBonus);
        ModifiableArg<Float> apDamage = new ModifiableArg<>(0.0f);
        if (apRatio >= 0 && apDamageSource != null) {
            actualNonAPDamage.setArg(damageWithBonus * (1 - apRatio));
            apDamage.setArg(damageWithBonus - actualNonAPDamage.getArg());
        }
        preDamage(entity, actualNonAPDamage, apDamage);
        //the actual damaging part
        if (apDamage.getArg() != 0)
            entity.hurt(apDamageSource, apDamage.getArg());
        entity.hurt(damageSource, actualNonAPDamage.getArg());
        postDamage(entity, actualNonAPDamage.getArg(), apDamage.getArg());
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public DamageSource getApDamageSource() {
        return apDamageSource;
    }

    public float getApRatio() {
        return apRatio;
    }

    public float getDamageBoostEfficiency() {
        return damageBoostEfficiency;
    }

    @SuppressWarnings("DataFlowIssue")
    private static float calculateDamageBoostEfficiency(LivingEntity entity, float damage, float damageBoostEfficiency) {
        AttributeInstance attackDamage = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        assert attackDamage != null;
        float currentDamage = (float) attackDamage.getValue() * 10000;
        float combinedDamage;
        float baseDamage = (float) attackDamage.getBaseValue() * 100;
        float weaponDamage;
        float strengthDamage;
        try {
            //? if 1.20.1 {
            weaponDamage = (float) attackDamage.getModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF")).getAmount() * 100;
            strengthDamage = (float) attackDamage.getModifier(UUID.fromString("648D7064-6A60-4F59-8ABE-C2C23A6DD7A9")).getAmount() * 100;
            //?} else {
            /*weaponDamage = (float) attackDamage.getModifier(Item.BASE_ATTACK_DAMAGE_ID).amount() * 100;
            strengthDamage = (float) attackDamage.getModifier(Stellarity.mcId("effect.strength")).amount() * 100;
            *///?}
        } catch (Exception e) {
            weaponDamage = 0.0f;
            strengthDamage = 0.0f;
        }
        combinedDamage = Mth.clamp(baseDamage + weaponDamage + strengthDamage, 0.0f, Float.MAX_VALUE);
        float damageRatio = (((combinedDamage / currentDamage) - 100) * damageBoostEfficiency) / 100;
        return (damage * damageRatio / 100) + damage;
    }

    private void preDamage(LivingEntity entity, ModifiableArg<Float> nonAPDamage, ModifiableArg<Float> apDamage) {
        // add all things here for pre damage
    }

    private void postDamage(LivingEntity entity, float nonAPDamage, float apDamage) {
        // and here for post damage
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DamageUtility{");
        sb.append("damageSource=").append(damageSource);
        if (apDamageSource != null || apRatio >= 0) {
            sb.append(",apDamageSource=").append(apDamageSource);
            sb.append(",apRatio=").append(apRatio);
        }
        sb.append(",damageBoostEfficiency=").append(damageBoostEfficiency);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private DamageSource damageSource;
        private DamageSource apDamageSource = null;
        private float damageBoostEfficiency = 0.0f;
        private float apRatio = 0.0f;

        private Builder() {
        }

        public Builder setDamageSource(DamageSource val) {
            damageSource = val;
            return this;
        }

        public Builder setApDamageSource(DamageSource val) {
            apDamageSource = val;
            return this;
        }

        public Builder setDamageBoostEfficiency(float val) {
            damageBoostEfficiency = Mth.clamp(val, 0.0f, 1.0f);
            return this;
        }

        public Builder setApRatio(float val) {
            apRatio = Mth.clamp(val, 0.0f, 1.0f);
            return this;
        }

        public DamageUtility build() {
            return new DamageUtility(this);
        }
    }
}