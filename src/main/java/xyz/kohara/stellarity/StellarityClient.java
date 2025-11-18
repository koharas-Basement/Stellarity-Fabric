package xyz.kohara.stellarity;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.BiomeColors;

//? <= 1.21.1 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
//?} else {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}

@Environment(EnvType.CLIENT)
public class StellarityClient implements ClientModInitializer {
    public static void initBlockColors() {

        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                return BiomeColors.getAverageGrassColor(world, pos);
            }
            // fallback tint
            return 0x91BD59;
        }, StellarityBlocks.ENDER_GRASS_BLOCK);

        //? <= 1.21.1 {
        BlockRenderLayerMap.INSTANCE.putBlock(StellarityBlocks.ENDER_GRASS_BLOCK, RenderType.cutout());
        //?} else {
        /*BlockRenderLayerMap.putBlock(StellarityBlocks.ENDER_GRASS_BLOCK, ChunkSectionLayer.CUTOUT);
        *///?}

        Stellarity.LOGGER.info("Initialized Block Colors");
    }

    public static void initItemColors() {
        //? <= 1.21.1 {
        ColorProviderRegistry.ITEM.register(((itemStack, i) -> {
            return 0x91BD59;
        }), StellarityItems.ENDER_GRASS_BLOCK);
        //?}
        
    }

    @Override
    public void onInitializeClient() {
        Stellarity.LOGGER.info("Stellarity Client Initializing");

        initBlockColors();
        initItemColors();

    }
}
