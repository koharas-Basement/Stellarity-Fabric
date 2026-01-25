package xyz.kohara.stellarity.registry;

import xyz.kohara.stellarity.Stellarity;

//? 1.20.1 {
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.PaintingVariant;
//? }

public class StellarityPaintings {
    //? 1.20.1 {
    private static final Registrar<PaintingVariant> PAINTING_VARIANTS = StellarityRegistries.MANAGER.get().get(Registries.PAINTING_VARIANT);
    
    public static final RegistrySupplier<PaintingVariant> A_HOP_AND_A_SKIP_AWAY = register("a_hop_and_a_skip_away", 4, 4);
    public static final RegistrySupplier<PaintingVariant> DRAGONBLADE = register("dragonblade", 2, 2);
    public static final RegistrySupplier<PaintingVariant> END = register("end", 5, 5);
    public static final RegistrySupplier<PaintingVariant> END_BLOSSOM = register("end_blossom", 4, 4);
    public static final RegistrySupplier<PaintingVariant> HOURGLASS = register("hourglass", 2, 1);
    public static final RegistrySupplier<PaintingVariant> MAJESTICAL_BREW = register("majestical_brew", 3, 3);
    public static final RegistrySupplier<PaintingVariant> SCHEME = register("scheme", 3, 3);
    public static final RegistrySupplier<PaintingVariant> SHEPHERDS_FEAST = register("shepherds_feast", 2, 2);
    public static final RegistrySupplier<PaintingVariant> SNARE = register("snare", 1, 1);
    public static final RegistrySupplier<PaintingVariant> SNATCH = register("snatch", 3, 2);
    public static final RegistrySupplier<PaintingVariant> THE_OBSIDIAN_RELIQUARY = register("the_obsidian_reliquary", 2, 2);
    
    public static RegistrySupplier<PaintingVariant> register(String id, int width, int height) {
        return PAINTING_VARIANTS.register(Stellarity.id(id), () -> new PaintingVariant(width * 16, height * 16));
    }
    //? }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Paintings");
    }
}
