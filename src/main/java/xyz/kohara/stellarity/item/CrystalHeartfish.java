package xyz.kohara.stellarity.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.AdvancementCommands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityItems;
//? < 1.21.1 {
import net.minecraft.advancements.Advancement;
  //?} else {
/*import net.minecraft.advancements.AdvancementHolder;
 *///?}


//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
 *///?}

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CrystalHeartfish extends Item {
  public CrystalHeartfish(Properties properties) {
    super(properties);
  }

  //? <= 1.21.1 {
  @Override
  public int getUseDuration(ItemStack itemStack
                            //? = 1.21.1
    //, LivingEntity livingEntity
  ) {
    return 100;
  }
  //?}


  public static Properties properties() {

    return StellarityItems.foodProperties(new Properties(), new FoodProperties.Builder(),
      //? >= 1.21.9
      //Consumables.defaultFood().consumeSeconds(5f),
      0, 0.0f, true
    );
  }

  @Override
  @NotNull
  public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
    if (!level.isClientSide()) {
      this.addHealth(livingEntity);
    }
    return super.finishUsingItem(itemStack, level, livingEntity);

  }

  public void addHealth(LivingEntity entity) {
    AttributeInstance maxHPAttribute = entity.getAttributes().getInstance(Attributes.MAX_HEALTH);
    if (maxHPAttribute == null) return;


    //? if < 1.21.1 {
    UUID uuid = UUID.fromString("019a9cd4-c40f-7032-a01f-273d3b1ed9b1");
    AttributeModifier oldModifier = maxHPAttribute.getModifier(uuid);

    double amount = oldModifier == null ? 0.0 : oldModifier.getAmount();
    //?} else {
    /*AttributeModifier oldModifier = maxHPAttribute.getModifier(Stellarity.of("crystal_heartfish_health_bonus"));

    double amount = oldModifier == null ? 0.0 : oldModifier.amount();
    *///?}

    if (amount >= 9) {
      if (entity instanceof ServerPlayer player) {
        MinecraftServer server = Objects.requireNonNull(player.level().getServer());
        ResourceLocation location = Stellarity.of("void_fishing/topped_off");

        //? if >= 1.21.1 {
        /*AdvancementHolder advancement = Objects.requireNonNull(server.getAdvancements().get(location));
         *///?} else {
        Advancement advancement = Objects.requireNonNull(server.getAdvancements().getAdvancement(location));
        //?}

        //? if <= 1.21.1 {
        AdvancementCommands.Action.GRANT.perform(player, List.of(advancement));
        //?} else {
        /*AdvancementCommands.Action.GRANT.perform(player, List.of(advancement), true);
         *///?}
      }

      if (amount >= 10) return;

    }

    amount++;


    //? if < 1.21.1 {
    AttributeModifier newModifier = new AttributeModifier(uuid, "stellarity:crystal_heartfish_health_bonus",
      amount,
      AttributeModifier.Operation.ADDITION
    );

    //?} else {
    /*AttributeModifier newModifier = new AttributeModifier(
      Stellarity.of("crystal_heartfish_health_bonus"),
      amount,
      AttributeModifier.Operation.ADD_VALUE
    );

    *///?}


    if (oldModifier != null) {
      maxHPAttribute.removeModifier(oldModifier);
    }
    maxHPAttribute.addPermanentModifier(newModifier);
  }


}
