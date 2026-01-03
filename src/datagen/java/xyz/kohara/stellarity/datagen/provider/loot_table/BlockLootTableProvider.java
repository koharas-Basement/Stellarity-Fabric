package xyz.kohara.stellarity.datagen.provider.loot_table;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import xyz.kohara.stellarity.registry.StellarityBlocks;

//? > 1.21 {
/*import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
*///? }

import static xyz.kohara.stellarity.utils.LootTableUtils.*;

public class BlockLootTableProvider extends FabricBlockLootTableProvider {

    //? 1.20.1 {
    public BlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    //? } else {

    /*public BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }
    *///? }


    private static final Block[] DROP_SELF = {
        StellarityBlocks.ASHEN_FROGLIGHT,
        StellarityBlocks.ENDER_DIRT,
        StellarityBlocks.ROOTED_ENDER_DIRT,

    };


    @Override
    public void generate() {
        for (Block block : DROP_SELF) {
            dropSelf(block);
        }

        dropOther(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT);

        add(StellarityBlocks.ENDER_GRASS_BLOCK, new LootTable.Builder().withPool(new LootPool.Builder().add(
            AlternativesEntry.alternatives(
                LootItem.lootTableItem(StellarityBlocks.ENDER_GRASS_BLOCK).when(/*? 1.20.1 {*/HAS_SILK_TOUCH/*? } else { *//*hasSilkTouch()*//*? }*/),
                applyExplosionCondition(StellarityBlocks.ENDER_DIRT, item(StellarityBlocks.ENDER_DIRT))
            )
        )));


    }
}
