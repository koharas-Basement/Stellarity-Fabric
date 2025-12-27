package xyz.kohara.stellarity.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.entity.PhantomItemFrame;
import xyz.kohara.stellarity.registry.entity.ThrownPrismaticPearl;

//? > 1.21.9 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///? }

public class StellarityEntities {

	public static final EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register("phantom_item_frame", EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

	public static final EntityType<ThrownPrismaticPearl> PRISMATIC_PEARL = register("prismatic_pearl", EntityType.Builder.of(ThrownPrismaticPearl::new, MobCategory.MISC));

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		var location = Stellarity.id(id);
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, location, builder.build(
			//? <= 1.21.1 {
			location.toString()
			//? } else {
			/*Stellarity.key(Registries.ENTITY_TYPE, id)
			 *///? }
		));
	}

	public static void init() {
		Stellarity.LOGGER.info("Registering Stellarity Entities");
	}
}
