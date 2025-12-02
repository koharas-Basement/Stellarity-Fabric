package xyz.kohara.stellarity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import xyz.kohara.stellarity.entity.PhantomItemFrame;

//? > 1.21.9 {
/*import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
*///? }

public class StellarityEntities {

  public static final EntityType<PhantomItemFrame> PHANTOM_ITEM_FRAME = register("phantom_item_frame", EntityType.Builder.of(PhantomItemFrame::new, MobCategory.MISC));

  public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
    var location = Stellarity.of(id);
    return Registry.register(BuiltInRegistries.ENTITY_TYPE, location, builder.build(
      //? <= 1.21.1 {
      location.toString()
      //? } else {
      /*ResourceKey.create(Registries.ENTITY_TYPE, location)
      *///? }
    ));
  }

  public static void init() {
    Stellarity.LOGGER.info("Registering Stellarity Entities");
  }
}
