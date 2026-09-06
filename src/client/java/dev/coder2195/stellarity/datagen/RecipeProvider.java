package dev.coder2195.stellarity.datagen;

import com.mojang.serialization.Lifecycle;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.recipe.*;
import dev.coder2195.stellarity.tags.StellarityItemTags;
import dev.coder2195.stellarity.util.tuple.Tuple2;
import dev.coder2195.stellarity.util.tuple.Tuple3;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static dev.coder2195.stellarity.registry.StellarityItems.*;
import static net.minecraft.world.item.Items.*;


public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	public static void altarOfTheAccursed(RecipeOutput output, String id, AltarOfTheAccursedRecipe recipe) {
		output.accept(Stellarity.key(Registries.RECIPE, "altar_of_the_accursed/" + id), recipe, null);
	}

	public static void consecration(RecipeOutput output, String id, Ingredient item, ItemStackTemplate result) {
		output.accept(Stellarity.key(Registries.RECIPE, "consecration/" + id), new ConsecrationRecipe(item, result), null);
	}

	public static class Ingredients extends LinkedHashMap<Ingredient, Integer> {
		public Ingredients put(ItemLike item, int count) {
			put(Ingredient.of(item), count);
			return this;
		}

		public Ingredients put(ItemLike item) {
			return put(item, 1);
		}

		public Ingredients putMany(int count, ItemLike... items) {
			put(Ingredient.of(items), count);
			return this;
		}
	}

	@Override
	protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
		return new net.minecraft.data.recipes.RecipeProvider(recipes, advancements) {
			@Override
			public void buildRecipes() {
				shapeless(RecipeCategory.BUILDING_BLOCKS, ENDERITE_BLOCK)
					.requires(ENDERITE_SHARD, 9)
					.unlockedBy(getHasName(ENDERITE_SHARD), has(ENDERITE_SHARD))
					.save(output, "crafting/enderite_block");

				shapeless(RecipeCategory.BUILDING_BLOCKS, ENDERITE_SHARD, 9)
					.requires(ENDERITE_BLOCK)
					.unlockedBy(getHasName(ENDERITE_BLOCK), has(ENDERITE_BLOCK))
					.save(output, "crafting/enderite_shard");

				this.shaped(RecipeCategory.BUILDING_BLOCKS, COARSE_ENDER_DIRT, 4)
					.pattern("DG")
					.pattern("GD")
					.define('D', ENDER_DIRT)
					.define('G', GRAVEL)
					.unlockedBy(getHasName(GRAVEL), this.has(GRAVEL))
					.unlockedBy(getHasName(ENDER_DIRT), this.has(ENDER_DIRT))
					.save(this.output, "crafting/coarse_ender_dirt");

				this.shaped(RecipeCategory.MISC, CHORUS_PLANT, 4)
					.pattern("CC")
					.pattern("CC")
					.define('C', CHORUS_FRUIT)
					.unlockedBy(getHasName(CHORUS_FRUIT), this.has(CHORUS_FRUIT))
					.save(this.output, "crafting/chorus_plant_from_chorus_fruit");

				this.shapeless(RecipeCategory.BUILDING_BLOCKS, GLASS, 3)
					.requires(END_STONE).requires(BLAZE_POWDER)
					.unlockedBy(getHasName(END_STONE), this.has(END_STONE))
					.unlockedBy(getHasName(BLAZE_POWDER), this.has(BLAZE_POWDER))
					.save(this.output, "crafting/glass_from_end_stone");


				SimpleCookingRecipeBuilder.generic(Ingredient.of(POPPED_CHORUS_FRUIT), RecipeCategory.MISC, CookingBookCategory.MISC, GUNPOWDER, 0.05f, 150, BlastingRecipe::new)
					.group("gunpowder")
					.unlockedBy(getHasName(GUNPOWDER), this.has(GUNPOWDER))
					.save(this.output, "blasting/gunpowder_from_popped_chorus_fruit");

				SimpleCookingRecipeBuilder.generic(Ingredient.of(POPPED_CHORUS_FRUIT), RecipeCategory.MISC, CookingBookCategory.MISC, GUNPOWDER, 0, 600, CampfireCookingRecipe::new)
					.group("gunpowder")
					.unlockedBy(getHasName(GUNPOWDER), this.has(GUNPOWDER))
					.save(this.output, "campfire/gunpowder_from_popped_chorus_fruit");

				this.shaped(RecipeCategory.MISC, LEATHER, 2)
					.pattern("##")
					.pattern("##")
					.define('#', PHANTOM_MEMBRANE)
					.unlockedBy(getHasName(PHANTOM_MEMBRANE), this.has(PHANTOM_MEMBRANE))
					.save(this.output, "crafting/leather_from_phantom_membrane");

				this.shapeless(RecipeCategory.MISC, PAPER, 6)
					.requires(CHORUS_PLANT, 3)
					.requires(WATER_BUCKET, 1)
					.unlockedBy(getHasName(CHORUS_PLANT), this.has(CHORUS_PLANT))
					.unlockedBy(getHasName(WATER_BUCKET), this.has(WATER_BUCKET))
					.save(this.output, "crafting/papyrus");

				this.shapeless(RecipeCategory.MISC, DYE.purple(), 2)
					.requires(CHORUS_FLOWER)
					.unlockedBy(getHasName(CHORUS_FLOWER), this.has(CHORUS_FLOWER))
					.save(this.output, "crafting/purple_dye_from_chorus_flower");

				this.shapeless(RecipeCategory.BUILDING_BLOCKS, SLIME_BALL, 3)
					.requires(WATER_BUCKET).requires(ENDER_EYE)
					.unlockedBy(getHasName(WATER_BUCKET), this.has(WATER_BUCKET))
					.unlockedBy(getHasName(ENDER_EYE), this.has(ENDER_EYE))
					.save(this.output, "crafting/slime_ball_from_ender_eye");

				this.shapeless(RecipeCategory.COMBAT, SPECTRAL_ARROW, 4)
					.requires(ARROW).requires(GLOWSTONE)
					.unlockedBy(getHasName(ARROW), this.has(ARROW))
					.unlockedBy(getHasName(GLOWSTONE), this.has(GLOWSTONE))
					.save(this.output, "crafting/spectral_arrow");

				this.shapeless(RecipeCategory.BUILDING_BLOCKS, SUGAR, 1)
					.requires(CHORUS_FRUIT)
					.unlockedBy(getHasName(CHORUS_FRUIT), this.has(CHORUS_FRUIT))
					.save(this.output, "crafting/sugar_from_chorus_fruit");

				this.shapeless(RecipeCategory.FOOD, new ItemStackTemplate(SUSPICIOUS_STEW,
						DataComponentPatch.builder().set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(MobEffects.LEVITATION, 160)))).build())
					)
					.requires(BOWL).requires(CHORUS_FRUIT).requires(RED_MUSHROOM).requires(BROWN_MUSHROOM)
					.unlockedBy(getHasName(BOWL), this.has(BOWL))
					.unlockedBy(getHasName(CHORUS_FRUIT), this.has(CHORUS_FRUIT))
					.unlockedBy(getHasName(RED_MUSHROOM), this.has(RED_MUSHROOM))
					.unlockedBy(getHasName(BROWN_MUSHROOM), this.has(BROWN_MUSHROOM))
					.save(this.output, "crafting/suspicious_stew_from_chorus_fruit");

				this.shapeless(RecipeCategory.FOOD, new ItemStackTemplate(SUSPICIOUS_STEW,
						DataComponentPatch.builder().set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(MobEffects.ABSORPTION, 180)))).build())
					)
					.requires(BOWL).requires(PINK_PETALS).requires(RED_MUSHROOM).requires(BROWN_MUSHROOM)
					.unlockedBy(getHasName(BOWL), this.has(BOWL))
					.unlockedBy(getHasName(PINK_PETALS), this.has(PINK_PETALS))
					.unlockedBy(getHasName(RED_MUSHROOM), this.has(RED_MUSHROOM))
					.unlockedBy(getHasName(BROWN_MUSHROOM), this.has(BROWN_MUSHROOM))
					.save(this.output, "crafting/suspicious_stew_from_pink_petals");

				this.shapeless(RecipeCategory.FOOD, new ItemStackTemplate(SUSPICIOUS_STEW,
						DataComponentPatch.builder().set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(MobEffects.STRENGTH, 220)))).build())
					)
					.requires(BOWL).requires(PITCHER_PLANT).requires(RED_MUSHROOM).requires(BROWN_MUSHROOM)
					.unlockedBy(getHasName(BOWL), this.has(BOWL))
					.unlockedBy(getHasName(PITCHER_PLANT), this.has(PITCHER_PLANT))
					.unlockedBy(getHasName(RED_MUSHROOM), this.has(RED_MUSHROOM))
					.unlockedBy(getHasName(BROWN_MUSHROOM), this.has(BROWN_MUSHROOM))
					.save(this.output, "crafting/suspicious_stew_from_pitcher_plant");

				registries.allRegistriesLifecycle().add(Lifecycle.stable());
				RecipeProvider.this.buildRecipes(registries, output);
			}
		};
	}


	public void buildRecipes(HolderLookup.Provider provider, RecipeOutput output) {

		altarOfTheAccursed(output, "lapis_to_amethyst", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(DIAMOND).put(LAPIS_LAZULI),
			new ItemStackTemplate(AMETHYST_SHARD)
		));

		altarOfTheAccursed(output, "chorus_plating", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(IRON_INGOT).put(POPPED_CHORUS_FRUIT, 2),
			new ItemStackTemplate(CHORUS_PLATING)
		));

		altarOfTheAccursed(output, "enderite_upgrade_smithing_template", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(ENDERITE_SHARD, 5).put(PURPUR_BLOCK, 9),
			new ItemStackTemplate(ENDERITE_UPGRADE_SMITHING_TEMPLATE, 2)
		));

		altarOfTheAccursed(output, "endonomicon", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(ENCHANTED_BOOK),
			new ItemStackTemplate(ENDONOMICON)
		));

		altarOfTheAccursed(output, "satchel_of_voids", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(BUNDLE).put(NETHER_STAR, 2).put(NETHERITE_INGOT, 4).put(ENDERITE_SHARD, 64).put(STARLIGHT_SOOT, 64),
			new ItemStackTemplate(SATCHEL_OF_VOIDS)
		));

		altarOfTheAccursed(output, "satchel_of_voids_alternative", new AltarOfTheAccursedSimpleRecipe(
			new Ingredients().put(LEATHER).put(STRING).put(NETHER_STAR, 2).put(NETHERITE_INGOT, 4).put(ENDERITE_SHARD, 64).put(STARLIGHT_SOOT, 64),
			new ItemStackTemplate(SATCHEL_OF_VOIDS)
		));

		altarOfTheAccursed(output, "dye_elytra", new AltarOfTheAccursedDyeRecipe(Ingredient.of(ELYTRA)));

		altarOfTheAccursed(output, "spectral_fury", new AltarOfTheAccursedUpgradeRecipe(
			Ingredient.of(SHARANGA),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(PHANTOM_MEMBRANE, 8).put(DIAMOND, 3),
			new ItemStackTemplate(SPECTRAL_FURY)
		));

		altarOfTheAccursed(output, "tamaris", new AltarOfTheAccursedUpgradeRecipe(
			Ingredient.of(NETHERITE_SWORD),
			new Ingredients().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE).put(ENDERITE_SHARD, 8).put(WITHER_SKELETON_SKULL).put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
			new ItemStackTemplate(TAMARIS)
		));


		var pieces = List.of(
			new Tuple2<>(NETHERITE_HELMET, "helmet"),
			new Tuple2<>(NETHERITE_CHESTPLATE, "chestplate"),
			new Tuple2<>(NETHERITE_LEGGINGS, "leggings"),
			new Tuple2<>(NETHERITE_BOOTS, "boots")
		);

		for (int i = 0; i < pieces.size(); i++) {
			var piece = pieces.get(i);
			for (var armorType : List.<Tuple3<String, Item[], Supplier<Ingredients>>>of(
				new Tuple3<>("shulker", new Item[]{SHULKER_HELMET, SHULKER_CHESTPLATE, SHULKER_LEGGINGS, SHULKER_BOOTS}, () -> new Ingredients().put(SHULKER_SHELL, 4)),
				new Tuple3<>("champion", new Item[]{CHAMPION_HELMET, CHAMPION_CHESTPLATE, CHAMPION_LEGGINGS, CHAMPION_BOOTS}, () -> new Ingredients().put(CHORUS_PLATING, 4)),
				new Tuple3<>("hallowed", new Item[]{HALLOWED_HELMET, HALLOWED_CHESTPLATE, HALLOWED_LEGGINGS, HALLOWED_BOOTS}, () -> new Ingredients().put(HALLOWED_INGOT, 4)),
				new Tuple3<>("floral", new Item[]{FLORAL_HELMET, FLORAL_CHESTPLATE, FLORAL_LEGGINGS, FLORAL_BOOTS}, () -> new Ingredients().put(CHERRY_LEAVES, 8))
			))
				altarOfTheAccursed(output, armorType._1() + "_" + piece._2(), new AltarOfTheAccursedUpgradeRecipe(
					Ingredient.of(piece._1()),
					armorType._3().get().put(ENDERITE_UPGRADE_SMITHING_TEMPLATE),
					new ItemStackTemplate(armorType._2()[i])
				));
		}

		consecration(output, "cherry_leaves", Ingredient.of(provider.getOrThrow(StellarityItemTags.LEAVES_EXCEPT_CHERRY)), new ItemStackTemplate(CHERRY_LEAVES));
		consecration(output, "stripped_cherry_log", Ingredient.of(provider.getOrThrow(StellarityItemTags.STRIPPED_LOGS_EXCEPT_CHERRY)), new ItemStackTemplate(STRIPPED_CHERRY_LOG));
		consecration(output, "stripped_cherry_wood", Ingredient.of(provider.getOrThrow(StellarityItemTags.STRIPPED_WOOD_EXCEPT_CHERRY)), new ItemStackTemplate(STRIPPED_CHERRY_WOOD));
		consecration(output, "cherry_log", Ingredient.of(provider.getOrThrow(StellarityItemTags.LOGS_EXCEPT_CHERRY)), new ItemStackTemplate(CHERRY_LOG));
		consecration(output, "cherry_wood", Ingredient.of(provider.getOrThrow(StellarityItemTags.WOOD_EXCEPT_CHERRY)), new ItemStackTemplate(CHERRY_WOOD));
		consecration(output, "poppy", Ingredient.of(WITHER_ROSE), new ItemStackTemplate(POPPY));
		consecration(output, "pink_petals", Ingredient.of(DEAD_BUSH), new ItemStackTemplate(PINK_PETALS));
		consecration(output, "glow_ink_sac", Ingredient.of(INK_SAC), new ItemStackTemplate(GLOW_INK_SAC));
		consecration(output, "ink_sac", Ingredient.of(GLOW_INK_SAC), new ItemStackTemplate(INK_SAC));
		consecration(output, "nether_brick", Ingredient.of(BRICK), new ItemStackTemplate(NETHER_BRICK));
		consecration(output, "brick", Ingredient.of(NETHER_BRICK), new ItemStackTemplate(BRICK));
		consecration(output, "honey_block", Ingredient.of(SLIME_BLOCK), new ItemStackTemplate(HONEY_BLOCK));
		consecration(output, "slime_block", Ingredient.of(HONEY_BLOCK), new ItemStackTemplate(SLIME_BLOCK));
		consecration(output, "soul_torch", Ingredient.of(TORCH), new ItemStackTemplate(SOUL_TORCH));
		consecration(output, "torch", Ingredient.of(SOUL_TORCH), new ItemStackTemplate(TORCH));
		consecration(output, "soul_lantern", Ingredient.of(LANTERN), new ItemStackTemplate(SOUL_LANTERN));
		consecration(output, "lantern", Ingredient.of(SOUL_LANTERN), new ItemStackTemplate(LANTERN));
		consecration(output, "soul_campfire", Ingredient.of(CAMPFIRE), new ItemStackTemplate(SOUL_CAMPFIRE));
		consecration(output, "campfire", Ingredient.of(SOUL_CAMPFIRE), new ItemStackTemplate(CAMPFIRE));
		consecration(output, "shroomlight", Ingredient.of(GLOWSTONE), new ItemStackTemplate(SHROOMLIGHT));
		consecration(output, "glowstone", Ingredient.of(SHROOMLIGHT), new ItemStackTemplate(GLOWSTONE));
		consecration(output, "hallowed_ingot", Ingredient.of(IRON_INGOT), new ItemStackTemplate(HALLOWED_INGOT));
		consecration(output, "iron_ingot", Ingredient.of(HALLOWED_INGOT), new ItemStackTemplate(IRON_INGOT));
		consecration(output, "chainmail_helmet", Ingredient.of(IRON_HELMET), new ItemStackTemplate(CHAINMAIL_HELMET));
		consecration(output, "chainmail_chestplate", Ingredient.of(IRON_CHESTPLATE), new ItemStackTemplate(CHAINMAIL_CHESTPLATE));
		consecration(output, "chainmail_leggings", Ingredient.of(IRON_LEGGINGS), new ItemStackTemplate(CHAINMAIL_LEGGINGS));
		consecration(output, "chainmail_boots", Ingredient.of(IRON_BOOTS), new ItemStackTemplate(CHAINMAIL_BOOTS));
		consecration(output, "music_disc_relic", Ingredient.of(MUSIC_DISC_5), new ItemStackTemplate(MUSIC_DISC_RELIC));
		consecration(output, "charcoal", Ingredient.of(COAL), new ItemStackTemplate(CHARCOAL));
		consecration(output, "coal", Ingredient.of(CHARCOAL), new ItemStackTemplate(COAL));
		consecration(output, "sculk_sensor", Ingredient.of(CALIBRATED_SCULK_SENSOR), new ItemStackTemplate(SCULK_SENSOR));
		consecration(output, "flowering_azalea", Ingredient.of(AZALEA), new ItemStackTemplate(FLOWERING_AZALEA));
		consecration(output, "azalea", Ingredient.of(FLOWERING_AZALEA), new ItemStackTemplate(AZALEA));
		consecration(output, "red_sand", Ingredient.of(SAND), new ItemStackTemplate(RED_SAND));
		consecration(output, "sand", Ingredient.of(RED_SAND), new ItemStackTemplate(SAND));
		consecration(output, "red_sandstone", Ingredient.of(SANDSTONE), new ItemStackTemplate(RED_SANDSTONE));
		consecration(output, "sandstone", Ingredient.of(RED_SANDSTONE), new ItemStackTemplate(SANDSTONE));
		consecration(output, "chiseled_red_sandstone", Ingredient.of(CHISELED_SANDSTONE), new ItemStackTemplate(CHISELED_RED_SANDSTONE));
		consecration(output, "chiseled_sandstone", Ingredient.of(CHISELED_RED_SANDSTONE), new ItemStackTemplate(CHISELED_SANDSTONE));
		consecration(output, "cut_red_sandstone", Ingredient.of(CUT_SANDSTONE), new ItemStackTemplate(CUT_RED_SANDSTONE));
		consecration(output, "cut_sandstone", Ingredient.of(CUT_RED_SANDSTONE), new ItemStackTemplate(CUT_SANDSTONE));
		consecration(output, "red_sandstone_slab", Ingredient.of(SANDSTONE_SLAB), new ItemStackTemplate(RED_SANDSTONE_SLAB));
		consecration(output, "sandstone_slab", Ingredient.of(RED_SANDSTONE_SLAB), new ItemStackTemplate(SANDSTONE_SLAB));
		// TODO: STANDSTONE is a typo on mojank part, fix
		consecration(output, "cut_red_sandstone_slab", Ingredient.of(CUT_STANDSTONE_SLAB), new ItemStackTemplate(CUT_RED_SANDSTONE_SLAB));
		consecration(output, "cut_sandstone_slab", Ingredient.of(CUT_RED_SANDSTONE_SLAB), new ItemStackTemplate(CUT_STANDSTONE_SLAB));
		// end TODO
		consecration(output, "smooth_red_sandstone", Ingredient.of(SMOOTH_SANDSTONE), new ItemStackTemplate(SMOOTH_RED_SANDSTONE));
		consecration(output, "smooth_sandstone", Ingredient.of(SMOOTH_RED_SANDSTONE), new ItemStackTemplate(SMOOTH_SANDSTONE));
		consecration(output, "red_sandstone_stairs", Ingredient.of(SANDSTONE_STAIRS), new ItemStackTemplate(RED_SANDSTONE_STAIRS));
		consecration(output, "sandstone_stairs", Ingredient.of(RED_SANDSTONE_STAIRS), new ItemStackTemplate(SANDSTONE_STAIRS));
		consecration(output, "red_sandstone_wall", Ingredient.of(SANDSTONE_WALL), new ItemStackTemplate(RED_SANDSTONE_WALL));
		consecration(output, "sandstone_wall", Ingredient.of(RED_SANDSTONE_WALL), new ItemStackTemplate(SANDSTONE_WALL));
		consecration(output, "smooth_red_sandstone_stairs", Ingredient.of(SMOOTH_SANDSTONE_STAIRS), new ItemStackTemplate(SMOOTH_RED_SANDSTONE_STAIRS));
		consecration(output, "smooth_sandstone_stairs", Ingredient.of(SMOOTH_RED_SANDSTONE_STAIRS), new ItemStackTemplate(SMOOTH_SANDSTONE_STAIRS));
		consecration(output, "smooth_red_sandstone_slab", Ingredient.of(SMOOTH_SANDSTONE_SLAB), new ItemStackTemplate(SMOOTH_RED_SANDSTONE_SLAB));
		consecration(output, "smooth_sandstone_slab", Ingredient.of(SMOOTH_RED_SANDSTONE_SLAB), new ItemStackTemplate(SMOOTH_SANDSTONE_SLAB));
		consecration(output, "crying_obsidian", Ingredient.of(OBSIDIAN), new ItemStackTemplate(CRYING_OBSIDIAN));
		consecration(output, "obsidian", Ingredient.of(CRYING_OBSIDIAN), new ItemStackTemplate(OBSIDIAN));
		consecration(output, "music_disc_cat", Ingredient.of(MUSIC_DISC_13), new ItemStackTemplate(MUSIC_DISC_CAT));
		consecration(output, "music_disc_13", Ingredient.of(MUSIC_DISC_CAT), new ItemStackTemplate(MUSIC_DISC_13));
		consecration(output, "compass", Ingredient.of(CLOCK), new ItemStackTemplate(COMPASS));
		consecration(output, "clock", Ingredient.of(COMPASS), new ItemStackTemplate(CLOCK));
		consecration(output, "blaze_powder", Ingredient.of(MAGMA_CREAM), new ItemStackTemplate(BLAZE_POWDER));
		consecration(output, "magma_cream", Ingredient.of(BLAZE_POWDER), new ItemStackTemplate(MAGMA_CREAM));
		consecration(output, "spore_blossom", Ingredient.of(CHORUS_FLOWER), new ItemStackTemplate(SPORE_BLOSSOM));
		consecration(output, "melon", Ingredient.of(PUMPKIN), new ItemStackTemplate(MELON));
		consecration(output, "pumpkin", Ingredient.of(MELON), new ItemStackTemplate(PUMPKIN));
		consecration(output, "wind_charge", Ingredient.of(FIRE_CHARGE), new ItemStackTemplate(WIND_CHARGE));
		consecration(output, "fire_charge", Ingredient.of(WIND_CHARGE), new ItemStackTemplate(FIRE_CHARGE));
		consecration(output, "music_disc_creator", Ingredient.of(MUSIC_DISC_CREATOR_MUSIC_BOX), new ItemStackTemplate(MUSIC_DISC_CREATOR));
		consecration(output, "music_disc_creator_music_box", Ingredient.of(MUSIC_DISC_CREATOR), new ItemStackTemplate(MUSIC_DISC_CREATOR_MUSIC_BOX));
		consecration(output, "feather", Ingredient.of(ROTTEN_FLESH), new ItemStackTemplate(FEATHER));
		consecration(output, "rotten_flesh", Ingredient.of(FEATHER), new ItemStackTemplate(ROTTEN_FLESH));
		consecration(output, "deepslate", Ingredient.of(STONE), new ItemStackTemplate(DEEPSLATE));
		consecration(output, "stone", Ingredient.of(BLACKSTONE), new ItemStackTemplate(STONE));
		consecration(output, "armadillo_scute", Ingredient.of(TURTLE_SCUTE), new ItemStackTemplate(ARMADILLO_SCUTE));
		consecration(output, "turtle_scute", Ingredient.of(ARMADILLO_SCUTE), new ItemStackTemplate(TURTLE_SCUTE));
		consecration(output, "piston", Ingredient.of(STICKY_PISTON), new ItemStackTemplate(PISTON));
		consecration(output, "dropper", Ingredient.of(DISPENSER), new ItemStackTemplate(DROPPER));
		consecration(output, "trial_key", Ingredient.of(OMINOUS_TRIAL_KEY), new ItemStackTemplate(TRIAL_KEY));
		consecration(output, "purpur_key", Ingredient.of(GILDED_PURPUR_KEY), new ItemStackTemplate(PURPUR_KEY));
		consecration(output, "sculk_shrieker", Ingredient.of(SCULK_CATALYST), new ItemStackTemplate(SCULK_SHRIEKER));
		consecration(output, "sculk_catalyst", Ingredient.of(SCULK_SHRIEKER), new ItemStackTemplate(SCULK_CATALYST));
		consecration(output, "verdant_froglight", Ingredient.of(OCHRE_FROGLIGHT), new ItemStackTemplate(VERDANT_FROGLIGHT));
		consecration(output, "pearlescent_froglight", Ingredient.of(VERDANT_FROGLIGHT), new ItemStackTemplate(PEARLESCENT_FROGLIGHT));
		consecration(output, "ochre_froglight", Ingredient.of(PEARLESCENT_FROGLIGHT), new ItemStackTemplate(OCHRE_FROGLIGHT));
	}

	@Override
	public String getName() {
		return Stellarity.MOD_ID;
	}

}
