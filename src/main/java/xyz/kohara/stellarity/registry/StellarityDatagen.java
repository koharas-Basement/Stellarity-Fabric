package xyz.kohara.stellarity.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import xyz.kohara.stellarity.datagen.*;
import xyz.kohara.stellarity.datagen.loot_table.BlockLootTableProvider;
import xyz.kohara.stellarity.datagen.loot_table.FishingLootTableProvider;

@Environment(EnvType.CLIENT)
public class StellarityDatagen implements DataGeneratorEntrypoint {

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
	}
}
