package xyz.kohara.stellarity.registry.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import xyz.kohara.stellarity.registry.StellarityDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import xyz.kohara.stellarity.registry.StellaritySounds;
//? < 1.21.9 {

import xyz.kohara.stellarity.registry.StellarityItems;

 //? } else {
/*import net.minecraft.world.item.ToolMaterial;

import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.Nullable;
*///? }

import java.util.Comparator;

public class Tamaris extends SwordItem {
    public Tamaris(Properties properties) {
        //? if 1.20.1 {
        super(Tiers.NETHERITE, 2, -2.4f, properties);
        //? } else {
        /*super(Tiers.NETHERITE, properties.attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.4F)));
        *///? }
    }


    public static Properties properties() {
        return new Item.Properties().stacksTo(1).durability(1561);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        super.inventoryTick(itemStack, level, entity, i, bl);

        boolean isClient = level.isClientSide();
        Vec3 position = entity.position();

        if (entity instanceof Player player) {
            if (player.getCooldowns().isOnCooldown(itemStack/*? < 1.21.9 {*/.getItem() /*?}*/) || !player.isHolding(itemStack::equals))
                return;

            var nearbyEntities = level.getEntitiesOfClass(
                LivingEntity.class,
                new AABB(position.subtract(10, 10, 10), position.add(10, 10, 10)),
                e -> !e.is(player) && e.isAlive() && e.getHealth() / e.getMaxHealth() < 0.25f
            );

            if (isClient) {
                for (var nearbyEntity : nearbyEntities) {
                    var particlePos = nearbyEntity.position().add(0, nearbyEntity.getBbHeight() + 0.5, 0);
                    level.addParticle(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
                }
            } else {
                if (!player.isCrouching()) return;
                nearbyEntities.sort(Comparator.comparingDouble((e) -> e.distanceTo(player)));

                for (var nearby : nearbyEntities) {
                    boolean failed = false;
                    for (InteractionHand interactionHand : InteractionHand.values()) {
                        ItemStack itemStack2 = nearby.getItemInHand(interactionHand);
                        if (itemStack2.is(Items.TOTEM_OF_UNDYING)) {
                            failed = true;
                            break;
                        }
                    }

                    if (!nearby.hurt(nearby.damageSources().source(StellarityDamageTypes.TAMARIS_EXECUTE, player), 999f))
                        continue;


                    var nearestPos = nearby.position();

                    player.teleportTo(nearestPos.x, nearestPos.y, nearestPos.z);
                    itemStack.hurtAndBreak(1, player,
                        //? 1.20.1 {
                        (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND)
                         //? } else {
                        /*EquipmentSlot.MAINHAND
                        *///? }
                    );

                    nearby.playSound(StellaritySounds.TAMARIS_EXECUTE.get());

                    if (failed) {
                        player.getCooldowns().addCooldown(StellarityItems.TAMARIS.get(), 11 * 20);
                    }

                    break;
                }
            }


        }
    }
}
