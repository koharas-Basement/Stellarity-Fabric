package xyz.kohara.stellarity.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import xyz.kohara.stellarity.registry.item.*;


import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.Stellarity;
//? >= 1.21.9 {
/*import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

*///?} else {
import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.registry.item.*;
//? }


import java.util.List;
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
    public static final Item FLAREFIN_KOI = register("flarefin_koi", Item::new, foodProperties(4, 0.8f, new EffectChance(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16 * 20))));
    public static final Item AMETHYST_BUDFISH = register("amethyst_budfish", Item::new, new Item.Properties());
    public static final Item CRIMSON_TIGERFISH = register("crimson_tigerfish", Item::new, foodProperties(1, 0.2f,
        new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
        new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20))));
    public static final Item ENDER_KOI = register("ender_koi", Item::new, basicFood(1, 0.6f));
    public static final Item FLESHY_PIRANHA = register("fleshy_piranha", Item::new, foodProperties(1, 0.2f,
        new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
        new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20)))
    );
    public static final Item BUBBLEFISH = register("bubblefish", Item::new, foodProperties(0, 0, new EffectChance(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20))));
    public static final Item PRISMITE = register("prismite", Item::new, foodProperties(3, 1.8f, new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20))));
    public static final Item OVERGROWN_COD = register("overgrown_cod", Item::new,
        foodProperties(1, 0.2f, new EffectChance(new MobEffectInstance(
            //? >= 1.21.9 {
            /*MobEffects.SLOWNESS
             *///?} else {
            MobEffects.MOVEMENT_SLOWDOWN
            //?}
            , 3 * 20, 2))));
    public static final Item SHULKER_BODY = register("shulker_body", ShulkerBody::new, ShulkerBody.properties());
    public static final Item PRISMATIC_SUSHI = register("prismatic_sushi", Item::new, foodProperties(4, 2.4f, true, new EffectChance(new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 20))));
    public static final Item SHEPHERDS_PIE = register("shepherds_pie", Item::new,
        foodProperties(20, 20f, true,
            new EffectChance(new MobEffectInstance(
                //? >= 1.21.9 {
                /*MobEffects.HEALTH_BOOST
                 *///? } else {
                MobEffects.HEAL
                //?}
                , 20, 2)),
            new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 64 * 20, 1))
        ));
    public static final Item CHORUS_PIE = register("chorus_pie", Item::new, foodProperties(8, 4.8f));
    public static final Item PHANTOM_ITEM_FRAME = register("phantom_item_frame", PhantomItemFrameItem::new, new ItemFrameItem.Properties().stacksTo(16));

    public static final Item PHO = register("pho",
        //? >= 1.21 {
        /*Item::new,
         *///? } else {
        BowlFoodItem::new,
        //? }
        foodProperties(new Item.Properties().stacksTo(1).craftRemainder(Items.BOWL), new FoodProperties.Builder()
            //? = 1.21.1 {
            /*.usingConvertsTo(Items.BOWL)
             *///? } >= 1.21.9 {
            /*, Consumables.defaultFood()
             *///? }
            , 13, 20f, true,
            new EffectChance(new MobEffectInstance(MobEffects.ABSORPTION, 150 * 20)),
            new EffectChance(new MobEffectInstance(
                //? >= 1.21.9 {
                /*MobEffects.STRENGTH
                 *///? } else {
                MobEffects.DAMAGE_BOOST
                //?}
                , 150 * 20)),
            new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 32 * 20))
        )
        //? >= 1.21.9 {
        /*.usingConvertsTo(Items.BOWL)
         *///? }
    );

    public static final Item TAMARIS = register("tamaris", Tamaris::new, Tamaris.properties());

    public static final Item CHORUS_PLATING = register("chorus_plating", Item::new, new Item.Properties());
    public static final Item ENDERITE_SHARD = register("enderite_shard", Item::new, new Item.Properties());
    public static final Item ENDERITE_UPGRADE_SMITHING_TEMPLATE = register("enderite_upgrade_smithing_template", (properties) -> new SmithingTemplateItem(
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE),
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.hallowed_ingot")).withStyle(ChatFormatting.BLUE),
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.upgrade").withStyle(ChatFormatting.GRAY),
        Component.empty(),
        //? < 1.21.9
        Component.empty(),
        List.of(),
        List.of()
        //? >= 1.21.9
        //, properties
    ) {
        //? < 1.21.9 {
        @Override
        public void appendHoverText(ItemStack itemStack, /*? 1.20.1 { */    Level level /*? } else { */ /*TooltipContext context *//*? } */, List<Component> list, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, /*? 1.20.1 { */ level /*? } else { */ /*context *//*? }*/, list, tooltipFlag);
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.chorus_plating")).withStyle(ChatFormatting.BLUE)));
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.minecraft.shulker_shell")).withStyle(ChatFormatting.BLUE)));
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.8"), Component.translatable("block.minecraft.cherry_leaves")).withStyle(ChatFormatting.BLUE)));
        }
        //? } else {

        /*@Override
        public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
            consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.chorus_plating")).withStyle(ChatFormatting.BLUE)));
            consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.minecraft.shulker_shell")).withStyle(ChatFormatting.BLUE)));
            consumer.accept(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.8"), Component.translatable("block.minecraft.cherry_leaves")).withStyle(ChatFormatting.BLUE)));
        }

        *///? }
    }, new Item.Properties());

    public static final Item HALLOWED_INGOT = register("hallowed_ingot", Item::new, new Item.Properties());
    public static final Item SAND_RUNE = register("sand_rune", Item::new, new Item.Properties());
    public static final Item STARLIGHT_SOOT = register("starlight_soot", Item::new, new Item.Properties());
    public static final Item GILDED_PURPUR_KEY = register("gilded_purpur_key", Item::new, new Item.Properties());
    public static final Item PURPUR_KEY = register("purpur_key", Item::new, new Item.Properties());
    public static final Item WINGED_KEY = register("winged_key", Item::new, new Item.Properties());

    public static final Item PRISMATIC_PEARL = register("prismatic_pearl", PrismaticPearlItem::new, PrismaticPearlItem.properties());
    public static final Item ENDONOMICON = register("endonomicon", Endonomicon::new, Endonomicon.properties());

    public static Item registerBlock(String name, Block block) {
        return registerBlock(name, block, new Item.Properties());
    }

    public static Item registerBlock(String name, Block block, Item.Properties settings) {
        ResourceKey<Item> itemKey = Stellarity.key(Registries.ITEM, name);
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
        ResourceKey<Item> itemKey = Stellarity.key(Registries.ITEM, name);
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