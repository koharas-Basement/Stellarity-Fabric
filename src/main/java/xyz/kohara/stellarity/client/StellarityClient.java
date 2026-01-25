package xyz.kohara.stellarity.client;

//? if forge {
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//? }
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.client.registry.StellarityClientNetworking;
import xyz.kohara.stellarity.client.registry.StellarityEntityRenderers;
import xyz.kohara.stellarity.client.registry.StellarityModels;
import xyz.kohara.stellarity.client.registry.StellarityTooltips;

//$ clientOnly
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
//? if forge {
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Stellarity.MOD_ID)
//? } else if neoforge {
/*@EventBusSubscriber
*///? }
public class StellarityClient /*? if fabric >> ' {'*//*implements ClientModInitializer*/ {
    //? if fabric {
    
    /*@Override
    public void onInitializeClient() {
        Stellarity.LOGGER.info("Stellarity Client Initializing");

        StellarityModels.init();
        StellarityEntityRenderers.init();
        StellarityTooltips.init();
        StellarityClientNetworking.init();
    }
    *///? } else {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void registerModels(ModelEvent.BakingCompleted event) {
        StellarityModels.initModelPredicates();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void itemColorThing(RegisterColorHandlersEvent.Item event) {
        StellarityModels.initItemColors(event);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void blockColorThing(RegisterColorHandlersEvent.Block event) {
        StellarityModels.initBlockColors(event);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void clientSetup(FMLClientSetupEvent event) {
        StellarityClientNetworking.init();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void entityRendering(EntityRenderersEvent.RegisterRenderers event) {
        StellarityEntityRenderers.init(event);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    static void itemTooltipThing(ItemTooltipEvent event) {
        StellarityTooltips.init(event);
    }
    //? }
}
