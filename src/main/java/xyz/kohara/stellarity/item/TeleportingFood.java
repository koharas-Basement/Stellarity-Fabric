package xyz.kohara.stellarity.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.StellarityItems;

//? >=1.21.10 {
/*import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.TeleportRandomlyConsumeEffect;
*///?} else {
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.NotNull;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
//?}

public abstract class TeleportingFood extends Item {
  private final int teleportDiameter;

  public TeleportingFood(Properties properties, int teleportDiameter) {
    super(properties);
    this.teleportDiameter = teleportDiameter;
  }

  public int getTeleportDiameter() {
    return teleportDiameter;
  }

  //? >=1.21.9 {
/*
    public static Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
        return foodProperties(
                Consumables.defaultFood(),
                nutrition,
                saturation,
                alwaysEat,
                teleportDiameter,
                effectChances
        );
    }

     public static Properties foodProperties(Consumable.Builder consumable, int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
        return StellarityItems.foodProperties(
                new Item.Properties(),
                new FoodProperties.Builder(),
                consumable.onConsume(new TeleportRandomlyConsumeEffect(teleportDiameter)),
                nutrition,
                saturation,
                alwaysEat,
                effectChances
        );
    }


    *///?}

  public static Properties foodProperties(int nutrition, float saturation, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
    return foodProperties(
      nutrition,
      saturation,
      false,
      teleportDiameter,
      effectChances
    );
  }

  //? <= 1.21.1 {

  public static Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, int teleportDiameter, StellarityItems.EffectChance... effectChances) {
    return StellarityItems.foodProperties(
      new Item.Properties(),
      new FoodProperties.Builder(),
      nutrition,
      saturation,
      alwaysEat,
      effectChances
    );
  }


  @Override
  @NotNull
  public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
    ItemStack itemStack2 = super.finishUsingItem(itemStack, level, livingEntity);
    if (!level.isClientSide) {
      for (int i = 0; i < teleportDiameter / 2; i++) {
        double d = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5) * teleportDiameter;
        double e = Mth.clamp(
          livingEntity.getY() + (livingEntity.getRandom().nextInt(teleportDiameter) - teleportDiameter / 2.0),
          (double) level.getMinBuildHeight(),
          (double) (level.getMinBuildHeight() + ((ServerLevel) level).getLogicalHeight() - 1)
        );
        double f = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5) * teleportDiameter;
        if (livingEntity.isPassenger()) {
          livingEntity.stopRiding();
        }

        Vec3 vec3 = livingEntity.position();
        if (livingEntity.randomTeleport(d, e, f, true)) {
          level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
          SoundSource soundSource;
          SoundEvent soundEvent;
          if (livingEntity instanceof Fox) {
            soundEvent = SoundEvents.FOX_TELEPORT;
            soundSource = SoundSource.NEUTRAL;
          } else {
            soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
            soundSource = SoundSource.PLAYERS;
          }

          level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundEvent, soundSource, 1.0f, 1.0f);
          livingEntity.resetFallDistance();
          break;
        }
      }

      if (livingEntity instanceof Player player) {
        //? = 1.21.1
        //player.resetCurrentImpulseContext();
        player.getCooldowns().addCooldown(this, 20);
      }
    }

    return itemStack2;
  }
  //?}
}
