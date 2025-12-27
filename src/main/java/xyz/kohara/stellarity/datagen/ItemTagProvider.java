package xyz.kohara.stellarity.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.registry.StellarityItemTags;
import xyz.kohara.stellarity.registry.StellarityItems;

import java.util.concurrent.CompletableFuture;
//? >= 1.21.9 {
/*import net.minecraft.data.tags.TagAppender;
  *///?}

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
  public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
    super(output, completableFuture, blockTagProvider);
  }

  public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
    super(output, completableFuture);
  }

  //? >= 1.21.9 {
  /*public TagAppender<Item, Item> getOrCreateTagBuilder(TagKey<Item> tagKey) {
    return this.valueLookupBuilder(tagKey);
  }
  *///?}


  @Override
  public void addTags(HolderLookup.Provider provider) {
    getOrCreateTagBuilder(StellarityItemTags.FISHES).add(
      StellarityItems.AMETHYST_BUDFISH,
      StellarityItems.BUBBLEFISH,
      StellarityItems.CRIMSON_TIGERFISH,
      StellarityItems.ENDER_KOI,
      StellarityItems.FLAREFIN_KOI,
      StellarityItems.CRYSTAL_HEARTFISH
    );

    getOrCreateTagBuilder(ItemTags.FISHES).addTag(StellarityItemTags.FISHES);
  }
}
