package xyz.kohara.stellarity.registry.recipe;


import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;
import xyz.kohara.stellarity.registry.StellarityRecipeTypes;
//? 1.20.1 {
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
//? }

import java.util.HashMap;
import java.util.List;

//? > 1.21 {
/*import net.minecraft.core.HolderLookup;
    *///? } else {
import net.minecraft.data.recipes.FinishedRecipe;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
//? }

//? > 1.21.9 {
/*import net.minecraft.core.particles.ColorParticleOption;
 *///?}

public interface AltarRecipe extends Recipe<AltarRecipe.Input> {
    class Input extends SimpleContainer
        //? < 1.21 {
        {
         //? } else {
        /*implements RecipeInput {
        @Override
            *///? }
        public int size() {
            return this.items.size();
        }
    }

    record Output(HashMap<ItemStack, Integer> remainders, ItemStack result) {
    }

    @Nullable Output craft(List<ItemStack> itemStacks);

    HashMap<Ingredient, Integer> ingredients();

    ItemStack result();

    ResourceLocation id();

    //? 1.20.1 {
    default void toJson(JsonObject jsonObject) {
        var entrySet = this.ingredients().entrySet();
        JsonArray ingredientsArray = new JsonArray();
        for (var entry : entrySet) {
            JsonObject ingredient = new JsonObject();
            ingredient.add("ingredient", entry.getKey().toJson());
            var count = entry.getValue();
            if (count > 1) ingredient.addProperty("count", count);

            ingredientsArray.add(ingredient);
        }

        jsonObject.add("ingredients", ingredientsArray);
        JsonObject resultObj = new JsonObject();
        var result = this.result();
        resultObj.addProperty("item", BuiltInRegistries.ITEM.getKey(result.getItem()).toString());
        int count = result.getCount();
        if (count > 1) {
            resultObj.addProperty("count", count);
        }

        jsonObject.add("result", resultObj);
    }

    record Finished(AltarRecipe recipe) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject jsonObject) {
            recipe.toJson(jsonObject);
        }

        @Override
        public ResourceLocation getId() {
            return recipe.id();
        }

        @Override
        public RecipeSerializer<?> getType() {
            return recipe.getSerializer();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return null;
        }
    }

    default Finished finished() {
        return new Finished(this);
    }

    @Override
    default ItemStack getResultItem(RegistryAccess registryAccess) {
        return result();
    }

    @Override
    default ItemStack assemble(Input container, RegistryAccess registryAccess) {
        return null;
    }

    @Override
    default ResourceLocation getId() {
        return id();
    }


    //? } else {
    /*//? = 1.21.1
    //@Override
    default ItemStack getResultItem(HolderLookup.Provider provider) {
        return result().copy();
    }

    *///? }

    //? > 1.21.9 {
    /*@Override
    default PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    default RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    *///? } else {
    @Override
    default boolean canCraftInDimensions(int i, int j) {
        return true;
    }
    //? }


    @Override
    default RecipeType<? extends Recipe<Input>> getType() {
        return StellarityRecipeTypes.ALTAR_RECIPE;
    }


    @Override
    default boolean matches(Input container, Level level) {
        return craft(container.items) == null;
    }

    static void handleItems(ServerLevel serverLevel, double x, double y, double z, boolean locked) {

        List<ItemEntity> itemEntities = serverLevel.getEntitiesOfClass(ItemEntity.class, new AABB(
            x - 0.5, y + 0.75d - 0.5, z - 0.5,
            x + 0.5, y + 0.75d + 0.5, z + 0.5
        ), entity -> entity.stellarity$getItemMode() != ExtItemEntity.ItemMode.RESULT);

        Player player = serverLevel.getNearestPlayer(x, y, z, 10, false);

        if (locked) {
            if (!itemEntities.isEmpty() && player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(
                    new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.altar_of_the_accursed_locked").withStyle(ChatFormatting.DARK_PURPLE))
                );
            }


            return;
        }


        List<ItemStack> itemStacks = itemEntities.stream().map(ItemEntity::getItem).toList();
        ExtItemEntity.ItemMode itemMode = player != null && player.isCrouching() ? ExtItemEntity.ItemMode.PICKUP : ExtItemEntity.ItemMode.CRAFTING;

        for (var entity : itemEntities) {
            entity.stellarity$setItemMode(itemMode);
        }

        if (itemEntities.isEmpty()) return;


        AltarRecipe.Output output = null;
        AltarRecipe hitRecipe = null;


        if (itemMode == ExtItemEntity.ItemMode.CRAFTING) {
            //? = 1.21.1 {
            /*var allRecipes = serverLevel.getRecipeManager().getAllRecipesFor(StellarityRecipeTypes.ALTAR_RECIPE);
            *///? } > 1.21.9 {
            /*var allRecipes = serverLevel.getServer().getRecipeManager().getAllOfType(StellarityRecipeTypes.ALTAR_RECIPE);
             *///? }
            //? = 1.20.1 {
            for (var recipe : serverLevel.getRecipeManager().getAllRecipesFor(StellarityRecipeTypes.ALTAR_RECIPE)) {
             //? } else {
            /*for (var recipeHolder : allRecipes) {
                var recipe = recipeHolder.value();
                *///? }

                output = recipe.craft(itemStacks);
                if (output != null) {
                    hitRecipe = recipe;
                    break;
                }
            }
        }

        if (output == null) return;

        for (
            var entity : itemEntities) {
            entity.stellarity$updateResults(output.remainders());
        }

        ItemEntity resultItem = new ItemEntity(serverLevel, x, y + 0.75, z, output.result());
        resultItem.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT);
        serverLevel.addFreshEntity(resultItem);


        serverLevel.sendParticles(/*? < 1.21.9 {*/ ParticleTypes.FLASH/*? } else {*/ /*ColorParticleOption.create(ParticleTypes.FLASH, -1) *//*? }*/, x, y + 1, z, 1, 0, 0, 0, 0);

        serverLevel.sendParticles(ParticleTypes.END_ROD, x, y + 1, z, 17, 0, 0, 0, 0.13);
    }
}
