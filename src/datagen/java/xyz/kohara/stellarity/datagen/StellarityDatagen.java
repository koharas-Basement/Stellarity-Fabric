package xyz.kohara.stellarity.datagen;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import xyz.kohara.stellarity.datagen.provider.*;
import xyz.kohara.stellarity.datagen.provider.loot_table.BlockLootTableProvider;
import xyz.kohara.stellarity.datagen.provider.loot_table.FishingLootTableProvider;

public class StellarityDatagen implements DataGeneratorEntrypoint, ModInitializer {


    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();


        pack.addProvider(ModelProvider::new);
        pack.addProvider(AdvancementProvider::new);
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(RecipeProvider::new);
        pack.addProvider(BlockLootTableProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(FishingLootTableProvider::new);


        //? <= 1.21.1 {
        pack.addProvider((fabricDataOutput, completableFuture) -> new EndonomiconBookProvider(fabricDataOutput, fabricDataGenerator, completableFuture.join(), "endonomicon"));
        //? }
    }

    @Override
    public void onInitialize() {
        
    }
}
