package xyz.kohara.stellarity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import xyz.kohara.stellarity.datagen.AdvancementProvider;
import xyz.kohara.stellarity.datagen.ItemTagProvider;
import xyz.kohara.stellarity.datagen.ModelProvider;
import xyz.kohara.stellarity.datagen.RecipeProvider;

@Environment(EnvType.CLIENT)
public class StellarityDatagen implements DataGeneratorEntrypoint {

  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    pack.addProvider(ModelProvider::new);
    pack.addProvider(AdvancementProvider::new);
    pack.addProvider(ItemTagProvider::new);
    pack.addProvider(RecipeProvider::new);
  }
}
