package xyz.kohara.stellarity.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import xyz.kohara.stellarity.registry.StellarityBlocks;

import java.util.concurrent.CompletableFuture;
//? >= 1.21.9 {
/*import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
*///? }

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    //? >= 1.21.9 {
    /*public TagAppender<Block, Block> getOrCreateTagBuilder(TagKey<Block> tagKey) {
        return this.valueLookupBuilder(tagKey);
    }
    *///?}


    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(StellarityBlocks.ENDER_DIRT_PATH, StellarityBlocks.ENDER_DIRT, StellarityBlocks.ENDER_GRASS_BLOCK, StellarityBlocks.ROOTED_ENDER_DIRT);
    }
}
