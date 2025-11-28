package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.item.*;
//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.component.Consumable;
*///?}


import java.util.function.Function;

public class StellarityItems {

  public static final Item ENDER_DIRT = registerBlock("ender_dirt", StellarityBlocks.ENDER_DIRT);
  public static final Item ENDER_GRASS_BLOCK = registerBlock("ender_grass_block", StellarityBlocks.ENDER_GRASS_BLOCK);
  public static final Item ASHEN_FROGLIGHT = registerBlock("ashen_froglight", StellarityBlocks.ASHEN_FROGLIGHT);
  public static final Item ROOTED_ENDER_DIRT = registerBlock("rooted_ender_dirt", StellarityBlocks.ROOTED_ENDER_DIRT);
  public static final Item ENDER_DIRT_PATH = registerBlock("ender_dirt_path", StellarityBlocks.ENDER_DIRT_PATH);
  public static final Item ALTAR_OF_THE_ACCURSED = registerBlock("altar_of_the_accursed", StellarityBlocks.ALTAR_OF_THE_ACCURSED);

  public static final Item CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.properties());
  public static final Item FISHER_OF_VOIDS = register("fisher_of_voids", FisherOfVoids::new, FisherOfVoids.properties());

  public static final Item SUSHI = register("sushi", Item::new, basicFood(4, 2.4f));
  public static final Item GOLDEN_CHORUS_FRUIT = register("golden_chorus_fruit", GoldenChorusFruit::new, GoldenChorusFruit.properties());
  public static final Item FRIED_CHORUS_FRUIT = register("fried_chorus_fruit", FriedChorusFruit::new, FriedChorusFruit.properties());
  public static final Item FROZEN_CARPACCIO = register("frozen_carpaccio", Item::new, basicFood(7, 8.4f));
  public static final Item ENDERMAN_FLESH = register("enderman_flesh", EndermanFlesh::new, EndermanFlesh.properties());
  public static final Item CRYSTAL_HEARTFISH = register("crystal_heartfish", CrystalHeartfish::new, CrystalHeartfish.properties());
  public static final Item GRILLED_ENDERMAN_FLESH = register("grilled_enderman_flesh", Item::new, basicFood(6, 9.6f));
  public static final Item FLAREFIN_KOI = register("flarefin_koi", Item::new, foodProperties(4, 0.8f, new EffectChance(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16 * 20), 1.0f)));
  public static final Item AMETHYST_BUDFISH = register("amethyst_budfish", Item::new, new Item.Properties());
  public static final Item CRIMSON_TIGERFISH = register("crimson_tigerfish", Item::new, foodProperties(1, 0.2f,
    new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20), 1.0f),
    new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20), 1.0f)));
  public static final Item ENDER_KOI = register("ender_koi", Item::new, basicFood(1, 0.6f));
  public static final Item FLESHY_PIRANHA = register("fleshy_piranha", Item::new, foodProperties(1, 0.2f,
    new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20), 1.0f),
    new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20), 1.0f))
  );
  public static final Item BUBBLEFISH = register("bubblefish", Item::new, foodProperties(0, 0, new EffectChance(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20), 1.0f)));
  public static final Item PRISMITE = register("prismite", Item::new, foodProperties(3, 1.8f, new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20), 1.0f)));
  public static final Item OVERGROWN_COD = register("overgrown_cod", Item::new,
    foodProperties(1, 0.2f, new EffectChance(new MobEffectInstance(
      //? >= 1.21.9 {
      /*MobEffects.SLOWNESS
       *///?} else {
      MobEffects.MOVEMENT_SLOWDOWN
      //?}
      , 3 * 20, 2), 1.0f)));
  public static final Item SHULKER_BODY = register("shulker_body", ShulkerBody::new, ShulkerBody.properties());
  public static final Item PRISMATIC_SUSHI = register("prismatic_sushi", Item::new, foodProperties(4, 2.4f, true, new EffectChance(new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 20))));



  public static Item registerBlock(String name, Block block) {
    return registerBlock(name, block, new Item.Properties());
  }

  public static Item registerBlock(String name, Block block, Item.Properties settings) {
    ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
    //? if >= 1.21.9 {
    /*settings = settings.useBlockDescriptionPrefix().setId(itemKey);
     *///?}
    Item item = new BlockItem(block, settings);

    Registry.register(BuiltInRegistries.ITEM, itemKey, item);

    return item;
  }

  public static Item register(String name, Function<Item.Properties, Item> itemFactory) {
    return register(name, itemFactory, new Item.Properties());
  }

  public static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
    ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Stellarity.of(name));
    //? >= 1.21.10 {
    /*settings.setId(itemKey);
     *///?}

    Item item = itemFactory.apply(settings);
    Registry.register(BuiltInRegistries.ITEM, itemKey, item);

    return item;
  }

  public record EffectChance(MobEffectInstance effect, float chance) {
    public EffectChance(MobEffectInstance effect) {
      this(effect, 1.0f);
    }
  }


  public static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties,
                                               //? >= 1.21.9 {
    /*Consumable.Builder consumable,
     *///?}
                                               int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
    foodProperties = foodProperties
      .nutrition(nutrition)
      //? < 1.21.1 {
      .saturationMod(saturation);

for (EffectChance ec : effectChances) {
  foodProperties.effect(ec.effect, ec.chance);
}
//?} else {
      /*.saturationModifier(saturation);
    *///?}
    if (alwaysEat) {
      foodProperties =
        //? = 1.20.1
        foodProperties.alwaysEat();
        //? >= 1.21.1
        //foodProperties.alwaysEdible();
    }

    //? >= 1.21.9 {
        /*for (EffectChance ec : effectChances) {
            consumable = consumable.onConsume(new ApplyStatusEffectsConsumeEffect(ec.effect, ec.chance));
        }
        return properties.food(foodProperties.build(), consumable.build());
         *///?} else {

    return properties.food(foodProperties.build());
    //?}

  }

  //? >= 1.21.9 {
    /*public static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties,
                                                    int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
        return foodProperties(properties, foodProperties, Consumables.defaultFood(), nutrition, saturation, alwaysEat, effectChances);
    }
    *///?}

  public static Item.Properties foodProperties(int nutrition, float saturation, boolean alwaysEat, EffectChance... effectChances) {
    return foodProperties(new Item.Properties(), new FoodProperties.Builder(), nutrition, saturation, alwaysEat, effectChances);
  }

  public static Item.Properties foodProperties(int nutrition, float saturation, EffectChance... effectChances) {
    return foodProperties(nutrition, saturation, false, effectChances);
  }

  public static Item.Properties basicFood(int nutrition, float saturation) {
    return foodProperties(nutrition, saturation, false);
  }


  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Items");
  }
}