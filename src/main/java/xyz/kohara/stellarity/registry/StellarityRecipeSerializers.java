package xyz.kohara.stellarity.registry;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.recipe.AltarSimpleRecipe;

public class StellarityRecipeSerializers {
    private static final Registrar<RecipeSerializer<?>> RECIPE_SERIALIZERS = StellarityRegistries.MANAGER.get().get(Registries.RECIPE_SERIALIZER);
    
    public static final RegistrySupplier<RecipeSerializer<AltarSimpleRecipe>> ALTAR_SIMPLE = registerSerializer("altar_of_the_accursed_simple", new AltarSimpleRecipe.Serializer());

    private static <T extends Recipe<?>> RegistrySupplier<RecipeSerializer<T>> registerSerializer(final String id, RecipeSerializer<T> serializer) {
        return RECIPE_SERIALIZERS.register(Stellarity.id(id), () -> serializer);
    }

    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Recipe Serializers");
    }
}
