package xyz.kohara.stellarity.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.StellarityDamageTypes;
//? < 1.21.9 {
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
//? } else {
/*import net.minecraft.world.item.ToolMaterial;
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
  public void inventoryTick(
    //? < 1.21.9 {
    ItemStack itemStack, Level level, Entity entity, int i, boolean bl
     //? } else {
    /*@NotNull ItemStack itemStack, @NotNull ServerLevel level, @NotNull Entity entity, @Nullable EquipmentSlot equipmentSlot
    *///? }
  ) {
    //? < 1.21.9 {
    super.inventoryTick(itemStack, level, entity, i, bl);
     //? } else {
    /*super.inventoryTick(itemStack, level, entity, equipmentSlot);
    *///? }

    boolean isClient = level.isClientSide();
    Vec3 position = entity.position();

    if (entity instanceof Player player) {
      if (player.getCooldowns().isOnCooldown(itemStack/*? < 1.21.9 {*/.getItem() /*?}*/) || !player.isHolding(itemStack::equals))
        return;
      if (isClient) return;
      var nearbyEntities = level.getEntitiesOfClass(
        LivingEntity.class,
        new AABB(position.subtract(10, 10, 10), position.add(10, 10, 10)),
        e -> !e.is(player) && e.isAlive() && e.getHealth() / e.getMaxHealth() < 0.25f
      );

      LivingEntity nearest = null;

      for (var nearbyEntity : nearbyEntities) {
        var particlePos = nearbyEntity.position().add(0, nearbyEntity.getBbHeight() + 0.5, 0);
        ((ServerLevel) level).sendParticles(ParticleTypes.SMOKE, particlePos.x, particlePos.y, particlePos.z, 1, 0, 0, 0, 0);
        if (nearest == null) {
          nearest = nearbyEntity;
          continue;
        }

        if (player.closerThan(nearbyEntity, player.distanceTo(nearest))) {
          nearest = nearbyEntity;
        }
      }

      if (nearest == null || !player.isCrouching()) return;

      var nearestPos = nearest.position();


      player.teleportTo(nearestPos.x, nearestPos.y, nearestPos.z);
      itemStack.hurtAndBreak(1, player,
        //? 1.20.1 {
        (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND)
         //? } else {
        /*EquipmentSlot.MAINHAND
        *///? }
      );

      nearest./*? < 1.21.9 {*/hurt(/*? } else {*//*hurtServer(level, *//*? } */ nearest.damageSources().source(StellarityDamageTypes.TAMARIS_EXECUTE), 999f);


    }
  }
}
