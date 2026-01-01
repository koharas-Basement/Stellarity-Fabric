package xyz.kohara.stellarity.registry;

//? 1.20.1 {

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
 //?} else {

/*import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
    *///? }

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import xyz.kohara.stellarity.Stellarity;

import static xyz.kohara.stellarity.utils.LootTableUtils.*;


public class StellarityLootTables {
    public static void init() {
        LootTableEvents.MODIFY.register((
            /*?1.20.1 {*/resourceManager, lootManager, id, builder, source/*? } else {*/  /*key, builder, source, provider *//*?}*/) -> {
            //? 1.21.1 {
            /*var id = key.location();

             *///? } > 1.21.10 {
            /*var id = key.identifier();
            *///? }

            if (id.equals(Stellarity.mcId("entities/magma_cube"))) {
                Stellarity.LOGGER.info("Modifying Magma Cube Loot Table");
                /*
                 * {
                 *           "type": "minecraft:item",
                 *           "conditions": [
                 *             {
                 *               "condition": "minecraft:damage_source_properties",
                 *               "predicate": {
                 *                 "source_entity": {
                 *                   "type": "minecraft:frog",
                 *                   "type_specific": {
                 *                     "type": "frog",
                 *                     "variant": "minecraft:temperate"
                 *                   }
                 *                 }
                 *               }
                 *             }
                 *           ],
                 *           "functions": [
                 *             {
                 *               "add": false,
                 *               "count": 1.0,
                 *               "function": "minecraft:set_count"
                 *             }
                 *           ],
                 *           "name": "minecraft:ochre_froglight"
                 *         }
                 */

                var tag = new CompoundTag();
                tag.putString("variant", "stellarity:end");

                builder.withPool(pool().when(
                    onDamage(
                        damage().source(
                            entity().entityType(entityType(EntityType.FROG)).nbt(nbt(tag))
                        )
                    )).add(item(StellarityItems.ASHEN_FROGLIGHT)));
            }
        });

    }
}
