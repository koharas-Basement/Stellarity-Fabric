package xyz.kohara.stellarity;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stellarity implements ModInitializer {
  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod id as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final String MOD_ID = "stellarity";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final String VERSION = /*$ mod_version*/ "0.1.0";
  public static final String MINECRAFT = /*$ minecraft*/ "1.20.1";

  public static ResourceLocation of(String path) {
    //? if = 1.20.1 {
    return new ResourceLocation(MOD_ID, path);
     //?} else {
    /*return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    *///?}
  }

  public static ResourceLocation mcOf(String path) {
    //? if = 1.20.1 {
    return new ResourceLocation(path);
     //?} else {
    /*return ResourceLocation.withDefaultNamespace(path);
    *///?}
  }

  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    StellarityItems.init();
    StellarityPotions.init();
    StellarityBlocks.init();
    StellarityCreativeModeTabs.init();
    StellarityEntities.init();
    StellarityBlockEntityTypes.init();
    StellarityCriteriaTriggers.init();
    StellarityRecipeTypes.init();
    StellarityRecipeSerializers.init();
  }

  /**
   * Adapts to the {@link ResourceLocation} changes introduced in 1.21.
   */
  public static ResourceLocation id(String namespace, String path) {
    //? if <1.21 {
    return new ResourceLocation(namespace, path);
     //?} else
    //return ResourceLocation.fromNamespaceAndPath(namespace, path);
  }
}