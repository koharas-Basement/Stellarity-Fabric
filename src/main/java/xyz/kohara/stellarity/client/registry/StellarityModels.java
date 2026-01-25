package xyz.kohara.stellarity.client.registry;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.registry.StellarityItems;

//? if forge {
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
//? } else if fabric {
/*import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
*///? }


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;

//$ clientOnly
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public class StellarityModels {
    private static void registerBowModel(Item bow) {
        ItemProperties.register(bow, Stellarity.mcId("pull"), (itemStack, clientWorld, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            //? = 1.20.1
            return entity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            //? = 1.21.1
            //return entity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) / 20.0F;
        });

        ItemProperties.register(bow, Stellarity.mcId("pulling"), (itemStack, clientWorld, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            }
            return entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    private static void registerFishingRodModel(Item fishingRod) {
        ItemProperties.register(fishingRod, Stellarity.mcId("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean bl = livingEntity.getMainHandItem() == itemStack;
                boolean bl2 = livingEntity.getOffhandItem() == itemStack;
                if (livingEntity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    bl2 = false;
                }

                return (bl || bl2) && livingEntity instanceof Player player && player.fishing != null ? 1.0F : 0.0F;
            }
        });
    }

    public static void initModelPredicates() {
        registerBowModel(StellarityItems.CALL_OF_THE_VOID.get());
        registerFishingRodModel(StellarityItems.FISHER_OF_VOIDS.get());
        Stellarity.LOGGER.info("Stellarity Model Predicates Initialized!");
    }
    
    public static void initBlockColors(/*? if forge >> ' {'*/RegisterColorHandlersEvent.Block event) {
        BlockColor blockColor = (state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                return BiomeColors.getAverageGrassColor(world, pos);
            }
            // fallback tint
            return 0x91BD59;
        };
        //? if fabric {
        /*ColorProviderRegistry.BLOCK.finishSetup(blockColor, StellarityBlocks.ENDER_GRASS_BLOCK.get());

        BlockRenderLayerMap.INSTANCE.putBlock(StellarityBlocks.ENDER_GRASS_BLOCK.get(), RenderType.cutout());
        *///? } else if forge {
        event.register(blockColor, StellarityBlocks.ENDER_GRASS_BLOCK.get());
        
        //TODO port the fabric thing
        //? }

        Stellarity.LOGGER.info("Initialized Block Model Colors");
    }

    public static void initItemColors(/*? if forge >> ' {'*/RegisterColorHandlersEvent.Item event) {
        ItemColor itemColor = (itemStack, i) -> 0x91BD59;
        //? if fabric {
        /*ColorProviderRegistry.ITEM.finishSetup(itemColor, StellarityItems.ENDER_GRASS_BLOCK.get());
        *///? } else if forge {
        event.register(itemColor, StellarityItems.ENDER_GRASS_BLOCK.get());
        //? }

        Stellarity.LOGGER.info("Initialized Item Model Colors");

    }

    //? if fabric {
    /*public static void init() {
        initModelPredicates();
        initBlockColors();
        initItemColors();
    }
    *///? }
}
