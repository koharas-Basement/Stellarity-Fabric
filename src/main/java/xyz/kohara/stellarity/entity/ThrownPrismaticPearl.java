package xyz.kohara.stellarity.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.projectile./*? > 1.21.9 { *//*throwableitemprojectile.*//*?}*/ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import xyz.kohara.stellarity.StellarityEntities;
import xyz.kohara.stellarity.StellarityItems;

public class ThrownPrismaticPearl extends ThrowableItemProjectile {
  public ThrownPrismaticPearl(EntityType<? extends ThrownPrismaticPearl> entityType, Level level) {
    super(entityType, level);
  }

  public ThrownPrismaticPearl(Level level, LivingEntity livingEntity, ItemStack itemStack) {
    //? > 1.21.9 {
    /*super(StellarityEntities.PRISMATIC_PEARL, livingEntity, level, itemStack);
    *///? } else {
    super(StellarityEntities.PRISMATIC_PEARL, livingEntity, level);
    setItem(itemStack);
    
    //? }
  }

  @Override
  protected Item getDefaultItem() {
    return StellarityItems.PRISMATIC_PEARL;
  }

  @Override
  protected void onHit(HitResult hitResult) {
    super.onHit(hitResult);

    if (level().isClientSide() || isRemoved()) return;


    this.discard();
  }
}
