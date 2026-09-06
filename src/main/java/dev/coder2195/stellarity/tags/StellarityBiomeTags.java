package dev.coder2195.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityBiomeTags {
	TagKey<Biome> SNOWY = id("snowy");
	TagKey<Biome> ALL_STELLARITY = id("all");
	TagKey<Biome> ALL_OUTER = id("all_outer");

	TagKey<Biome> SPAWNS_ASH_VOIDED_SKELETON = id("spawns_ash_voided_skeleton");
	TagKey<Biome> SPAWNS_COLD_VOIDED_SKELETON = id("spawns_cold_voided_skeleton");
	TagKey<Biome> SPAWNS_FLESH_VOIDED_SKELETON = id("spawns_flesh_voided_skeleton");

	TagKey<Biome> HAS_STRUCTURE_CAMPSITE = id("has_structure/campsite");
	TagKey<Biome> HAS_STRUCTURE_CHAPEL = id("has_structure/chapel");
	TagKey<Biome> HAS_STRUCTURE_DESERT_RUIN = id("has_structure/desert_ruin");
	TagKey<Biome> HAS_STRUCTURE_FISHING_HUT = id("has_structure/fishing_hut");
	TagKey<Biome> HAS_STRUCTURE_FOSSIL = id("has_structure/fossil");
	TagKey<Biome> HAS_STRUCTURE_OBSIDIAN_SPIKE = id("has_structure/obsidian_spike");
	TagKey<Biome> HAS_STRUCTURE_VILLAGE = id("has_structure/village");

	TagKey<Biome> NO_VOID_FISHING = id("no_void_fishing");
	TagKey<Biome> ALLOWS_CONSECRATION = id("allows_consecration");

	static TagKey<Biome> id(String id) {
		return TagKey.create(Registries.BIOME, Stellarity.id(id));

	}
}
