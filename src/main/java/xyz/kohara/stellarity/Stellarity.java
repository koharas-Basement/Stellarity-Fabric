package xyz.kohara.stellarity;

import net.fabricmc.api.ModInitializer;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Stellarity implements ModInitializer {
  // This logger is used to write text to the console and the log file.
  // It is considered best practice to use your mod id as the logger's name.
  // That way, it's clear which mod wrote info, warnings, and errors.
  public static final String MOD_ID = "stellarity";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
  public static final String VERSION = /*$ mod_version*/ "0.2.0";
  public static final String MINECRAFT = /*$ minecraft*/ "1.20.1";

  public static ResourceLocation id(String path) {
    //? if = 1.20.1 {
    return new ResourceLocation(MOD_ID, path);
    //?} else {
    /*return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);

     *///?}
  }

  public static ResourceLocation mcId(String path) {
    //? if = 1.20.1 {
    return new ResourceLocation(path);
    //?} else {
    /*return ResourceLocation.withDefaultNamespace(path);
     *///?}
  }

  public static <T extends Registry<U>, U> ResourceKey<U> key(ResourceKey<T> registry, String path) {
    return ResourceKey.create(registry, id(path));
  }

  @Override
  public void onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    StellarityItems.init();
    StellarityNetworking.init();
    StellarityPotions.init();
    StellarityBlocks.init();
    StellarityCreativeModeTabs.init();
    StellarityEntities.init();
    StellarityBlockEntityTypes.init();
    StellarityCriteriaTriggers.init();
    StellarityRecipeTypes.init();
    StellarityRecipeSerializers.init();
    StellarityPaintings.init();
    StellarityTooltips.init();
    StellarityMobEffects.init();
    StellaritySounds.init();
  }
}