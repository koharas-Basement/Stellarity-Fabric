package xyz.kohara.stellarity.registry.entity.variants;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.animal.FrogVariant;
import xyz.kohara.stellarity.Stellarity;

public class StellarityFrogVariants {
    private static final Registrar<FrogVariant> FROG_VARIANTS = RegistrarManager.get(Stellarity.MOD_ID).get(Registries.FROG_VARIANT);
    
    public static final RegistrySupplier<FrogVariant> END = register("end", "textures/entity/frog/ender_frog.png");
    public static final ResourceKey<FrogVariant> END_KEY = Stellarity.key(Registries.FROG_VARIANT, "end");

    private static RegistrySupplier<FrogVariant> register(String id, String texture) {
        return FROG_VARIANTS.register(Stellarity.id(id), () -> new FrogVariant(Stellarity.id(texture)));
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Frog Variants");


    }
}
