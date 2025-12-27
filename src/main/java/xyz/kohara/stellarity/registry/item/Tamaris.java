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

import org.jetbrains.annotations.Nullable;
  *///? }

import java.util.Comparator;

public class Tamaris extends
  //? < 1.21.9 {
  SwordItem
   //? } else {
  /*Item
  *///? }
{
  public Tamaris(Properties properties) {
    //? 1.20.1 {
    super(Tiers.NETHERITE, 2, -2.4f, properties);
     //? } 1.21.1 {
    /*super(Tiers.NETHERITE, properties.attributes(SwordItem.createAttributes(Tiers.NETHERITE, 2, -2.4F)));
     *///? } else {
    /*super(properties.sword(ToolMaterial.NETHERITE, 2, -2.4F));
    *///? }
  }


  public static Properties properties() {
    return new Item.Properties().stacksTo(1).durability(1561);
  }

  @Override
    //? > 1.21.9 {

  /*public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity entity, @Nullable EquipmentSlot equipmentSlot) {
    super.inventoryTick(itemStack, level, entity, equipmentSlot);

    inventoryTick(itemStack, (Level) level, entity, equipmentSlot);
  }
  *///? }

  public void inventoryTick(
    //? < 1.21.9 {
    ItemStack itemStack, Level level, Entity entity, int i, boolean bl
     //? } else {
    /*ItemStack itemStack, Level level, Entity entity, @Nullable EquipmentSlot equipmentSlot
    *///? }
  ) {
    //? < 1.21.9 {
    super.inventoryTick(itemStack, level, entity, i, bl);
     //? } else {

    //? }

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

          if (!nearby./*? < 1.21.9 {*/hurt(/*? } else {*//*hurtServer((ServerLevel) level, *//*? } */ nearby.damageSources().source(StellarityDamageTypes.TAMARIS_EXECUTE, player), 999f))
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

          nearby.playSound(StellaritySounds.TAMARIS_EXECUTE);

          if (failed) {
            player.getCooldowns().addCooldown(/*? < 1.21.10 { */StellarityItems.TAMARIS /*? } else { */ /*itemStack *//*? } */, 11 * 20);
          }

          break;
        }
      }


    }
  }
}
