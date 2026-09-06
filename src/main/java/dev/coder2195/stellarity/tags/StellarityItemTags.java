package dev.coder2195.stellarity.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import dev.coder2195.stellarity.Stellarity;

public interface StellarityItemTags {
	TagKey<Item> FISHES = id("fishes");
	TagKey<Item> BOWS = id("bows");
	TagKey<Item> ELYTRA_ENCHANTABLE = id("enchantable.elytra");
	TagKey<Item> RANGED_ENCHANTABLE = id("enchantable.ranged");
	TagKey<Item> DONATOR = id("donator");
	TagKey<Item> DEVELOPER = id("developer");

	TagKey<Item> SHULKER_ARMOR = id("shulker_armor");
	TagKey<Item> FLORAL_ARMOR = id("floral_armor");
	TagKey<Item> HALLOWED_ARMOR = id("hallowed_armor");
	TagKey<Item> CHAMPION_ARMOR = id("champion_armor");

	TagKey<Item> REPAIRS_CHAMPION_ARMOR = id("repairs_champion_armor");
	TagKey<Item> REPAIRS_HALLOWED_ARMOR = id("repairs_hallowed_armor");
	TagKey<Item> REPAIRS_REINFORCED_ARMOR = id("repairs_reinforced_armor");
	TagKey<Item> REPAIRS_SHULKER_ARMOR = id("repairs_shulker_armor");
	TagKey<Item> REPAIRS_FLORAL_ARMOR = id("repairs_floral_armor");
	TagKey<Item> REPAIRS_PHANTOM_WINGS = id("repairs_phantom_wings");
	TagKey<Item> REPAIRS_DRAGON_WINGS = id("repairs_dragon_wings");
	TagKey<Item> REPAIRS_EMPRESS_WINGS = id("repairs_empress_wings");

	TagKey<Item> SHULKER_TOOL_MATERIALS = id("shulker_tool_materials");

	TagKey<Item> WOOD_EXCEPT_CHERRY = id("wood_except_cherry");
	TagKey<Item> LOGS_EXCEPT_CHERRY = id("logs_except_cherry");
	TagKey<Item> STRIPPED_WOOD_EXCEPT_CHERRY = id("stripped_wood_except_cherry");
	TagKey<Item> STRIPPED_LOGS_EXCEPT_CHERRY = id("stripped_logs_except_cherry");
	TagKey<Item> LEAVES_EXCEPT_CHERRY = id("leaves_except_cherry");

	static TagKey<Item> id(String id) {
		return TagKey.create(Registries.ITEM, Stellarity.id(id));
	}

}
