package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.recipe.AltarRecipe;

public class StellarityRecipeTypes {
    private static final Registrar<RecipeType<?>> RECIPE_TYPES = StellarityRegistries.MANAGER.get().get(Registries.RECIPE_TYPE);
    
    public static final RegistrySupplier<RecipeType<AltarRecipe>> ALTAR_RECIPE = register("altar_of_the_accursed");

    private static <T extends Recipe<?>> RegistrySupplier<RecipeType<T>> register(final String id) {
        final var path = Stellarity.id(id);
        final var string = path.toString();
        return RECIPE_TYPES.register(path, () -> new RecipeType<>() {
            public String toString() {
                return string;
            }
        });
    }


    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Recipe Types");
    }
}
