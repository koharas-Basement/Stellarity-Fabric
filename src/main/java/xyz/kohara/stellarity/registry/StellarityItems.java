package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import xyz.kohara.stellarity.registry.item.*;

import xyz.kohara.stellarity.Stellarity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Function;

public class StellarityItems {
    private static final Registrar<Item> ITEMS = StellarityRegistries.MANAGER.get().get(Registries.ITEM);
    
    public static final RegistrySupplier<BlockItem> ENDER_DIRT = registerBlock("ender_dirt", StellarityBlocks.ENDER_DIRT);
    public static final RegistrySupplier<BlockItem> ENDER_GRASS_BLOCK = registerBlock("ender_grass_block", StellarityBlocks.ENDER_GRASS_BLOCK);
    public static final RegistrySupplier<BlockItem> ASHEN_FROGLIGHT = registerBlock("ashen_froglight", StellarityBlocks.ASHEN_FROGLIGHT);
    public static final RegistrySupplier<BlockItem> ROOTED_ENDER_DIRT = registerBlock("rooted_ender_dirt", StellarityBlocks.ROOTED_ENDER_DIRT);
    public static final RegistrySupplier<BlockItem> ENDER_DIRT_PATH = registerBlock("ender_dirt_path", StellarityBlocks.ENDER_DIRT_PATH);
    public static final RegistrySupplier<BlockItem> ALTAR_OF_THE_ACCURSED = registerBlock("altar_of_the_accursed", StellarityBlocks.ALTAR_OF_THE_ACCURSED);

    public static final RegistrySupplier<CallOfTheVoid> CALL_OF_THE_VOID = register("call_of_the_void", CallOfTheVoid::new, CallOfTheVoid.properties());
    public static final RegistrySupplier<FisherOfVoids> FISHER_OF_VOIDS = register("fisher_of_voids", FisherOfVoids::new, FisherOfVoids.properties());

    // food items start
    public static final RegistrySupplier<Item> SUSHI = register("sushi", Item::new, basicFood(4, 2.4f));
    public static final RegistrySupplier<GoldenChorusFruit> GOLDEN_CHORUS_FRUIT = register("golden_chorus_fruit", GoldenChorusFruit::new, GoldenChorusFruit.properties());
    public static final RegistrySupplier<FriedChorusFruit> FRIED_CHORUS_FRUIT = register("fried_chorus_fruit", FriedChorusFruit::new, FriedChorusFruit.properties());
    public static final RegistrySupplier<Item> FROZEN_CARPACCIO = register("frozen_carpaccio", Item::new, basicFood(7, 8.4f));
    public static final RegistrySupplier<EndermanFlesh> ENDERMAN_FLESH = register("enderman_flesh", EndermanFlesh::new, EndermanFlesh.properties());
    public static final RegistrySupplier<CrystalHeartfish> CRYSTAL_HEARTFISH = register("crystal_heartfish", CrystalHeartfish::new, CrystalHeartfish.properties());
    public static final RegistrySupplier<Item> GRILLED_ENDERMAN_FLESH = register("grilled_enderman_flesh", Item::new, basicFood(6, 9.6f));
    public static final RegistrySupplier<Item> FLAREFIN_KOI = register("flarefin_koi", Item::new, foodProperties(4, 0.8f, new EffectChance(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16 * 20))));
    public static final RegistrySupplier<Item> AMETHYST_BUDFISH = register("amethyst_budfish", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> CRIMSON_TIGERFISH = register("crimson_tigerfish", Item::new, foodProperties(1, 0.2f,
        new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
        new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20))));
    public static final RegistrySupplier<Item> ENDER_KOI = register("ender_koi", Item::new, basicFood(1, 0.6f));
    public static final RegistrySupplier<Item> FLESHY_PIRANHA = register("fleshy_piranha", Item::new, foodProperties(1, 0.2f,
        new EffectChance(new MobEffectInstance(MobEffects.HUNGER, 30 * 20)),
        new EffectChance(new MobEffectInstance(MobEffects.POISON, 20 * 20)))
    );
    public static final RegistrySupplier<Item> BUBBLEFISH = register("bubblefish", Item::new, foodProperties(0, 0, new EffectChance(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20))));
    public static final RegistrySupplier<Item> PRISMITE = register("prismite", Item::new, foodProperties(3, 1.8f, new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20))));
    public static final RegistrySupplier<Item> OVERGROWN_COD = register("overgrown_cod", Item::new,
        foodProperties(1, 0.2f, new EffectChance(new MobEffectInstance(
            MobEffects.MOVEMENT_SLOWDOWN, 3 * 20, 2))));
    public static final RegistrySupplier<ShulkerBody> SHULKER_BODY = register("shulker_body", ShulkerBody::new, ShulkerBody.properties());
    public static final RegistrySupplier<Item> PRISMATIC_SUSHI = register("prismatic_sushi", Item::new, foodProperties(4, 2.4f, true, new EffectChance(new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 20))));
    public static final RegistrySupplier<Item> SHEPHERDS_PIE = register("shepherds_pie", Item::new,
        foodProperties(20, 20f, true,
            new EffectChance(new MobEffectInstance(
                MobEffects.HEAL, 20, 2)),
            new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 64 * 20, 1))
        ));
    public static final RegistrySupplier<Item> CHORUS_PIE = register("chorus_pie", Item::new, foodProperties(8, 4.8f));
    public static final RegistrySupplier<PhantomItemFrameItem> PHANTOM_ITEM_FRAME = register("phantom_item_frame", PhantomItemFrameItem::new, new ItemFrameItem.Properties().stacksTo(16));

    public static final RegistrySupplier<Item> PHO = register("pho",
        //? >= 1.21 {
        /*Item::new,
         *///? } else {
        BowlFoodItem::new,
        //? }
        foodProperties(new Item.Properties().stacksTo(1).craftRemainder(Items.BOWL), new FoodProperties.Builder()
            //? = 1.21.1 {
            /*.usingConvertsTo(Items.BOWL)
             *///? }
            , 13, 20f, true,
            new EffectChance(new MobEffectInstance(MobEffects.ABSORPTION, 150 * 20)),
            new EffectChance(new MobEffectInstance(
                MobEffects.DAMAGE_BOOST
                , 150 * 20)),
            new EffectChance(new MobEffectInstance(MobEffects.REGENERATION, 32 * 20))
        )
    );
    // food items end

    public static final RegistrySupplier<Tamaris> TAMARIS = register("tamaris", Tamaris::new, Tamaris.properties());

    public static final RegistrySupplier<Item> CHORUS_PLATING = register("chorus_plating", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> ENDERITE_SHARD = register("enderite_shard", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> ENDERITE_UPGRADE_SMITHING_TEMPLATE = register("enderite_upgrade_smithing_template", (properties) -> new SmithingTemplateItem(
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.applies_to").withStyle(ChatFormatting.BLUE),
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.hallowed_ingot")).withStyle(ChatFormatting.BLUE),
        Component.translatable("item.stellarity.enderite_upgrade_smithing_template.upgrade").withStyle(ChatFormatting.GRAY),
        Component.empty(),
        Component.empty(),
        List.of(),
        List.of()
    ) {
        @Override
        public void appendHoverText(ItemStack itemStack, /*? 1.20.1 { */    Level level /*? } else { */ /*TooltipContext context *//*? } */, List<Component> list, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, /*? 1.20.1 { */ level /*? } else { */ /*context *//*? }*/, list, tooltipFlag);
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.stellarity.chorus_plating")).withStyle(ChatFormatting.BLUE)));
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.4"), Component.translatable("item.minecraft.shulker_shell")).withStyle(ChatFormatting.BLUE)));
            list.add(CommonComponents.space().append(Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients", Component.translatable("item.stellarity.enderite_upgrade_smithing_template.ingredients.count.8"), Component.translatable("block.minecraft.cherry_leaves")).withStyle(ChatFormatting.BLUE)));
        }
    }, new Item.Properties());

    public static final RegistrySupplier<Item> HALLOWED_INGOT = register("hallowed_ingot", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> SAND_RUNE = register("sand_rune", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> STARLIGHT_SOOT = register("starlight_soot", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> GILDED_PURPUR_KEY = register("gilded_purpur_key", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> PURPUR_KEY = register("purpur_key", Item::new, new Item.Properties());
    public static final RegistrySupplier<Item> WINGED_KEY = register("winged_key", Item::new, new Item.Properties());

    public static final RegistrySupplier<PrismaticPearlItem> PRISMATIC_PEARL = register("prismatic_pearl", PrismaticPearlItem::new, PrismaticPearlItem.properties());
    public static final RegistrySupplier<Endonomicon> ENDONOMICON = register("endonomicon", Endonomicon::new, Endonomicon.properties());

    public static RegistrySupplier<BlockItem> registerBlock(String name, RegistrySupplier<Block> block) {
        return registerBlock(name, block, new Item.Properties());
    }

    public static RegistrySupplier<BlockItem> registerBlock(String name, RegistrySupplier<Block> block, Item.Properties settings) {
        return ITEMS.register(Stellarity.id(name), () -> new BlockItem(block.get(), settings));
    }

    public static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, Item> itemFactory) {
        return register(name, itemFactory, new Item.Properties());
    }

    @SuppressWarnings("unchecked") //bruh
    public static <T extends Item> RegistrySupplier<T> register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {
        return ITEMS.register(Stellarity.id(name), () -> (T) itemFactory.apply(settings));
    }

    public record EffectChance(MobEffectInstance effect, float chance) {
        public EffectChance(MobEffectInstance effect) {
            this(effect, 1.0f);
        }
    }

    public static Item.Properties foodProperties(Item.Properties properties, FoodProperties.Builder foodProperties,
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
        return properties.food(foodProperties.build());
    }

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