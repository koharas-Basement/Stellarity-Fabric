package xyz.kohara.stellarity.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

import net.minecraft.network.chat.Component;
//? <= 1.21.10 {
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.critereon.*;
    //? } else {
/*import net.minecraft.advancements.criterion.*;
    *///? }
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityItems;


import java.util.HashMap;
import java.util.function.Consumer;
//? >= 1.21.1 {
/*import net.minecraft.core.HolderLookup;
import xyz.kohara.stellarity.registry.advancement_criterion.VoidFishedTrigger;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Optional;

import net.minecraft.core.registries.Registries;
*///?} else {

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.CriterionTriggerInstance;
import xyz.kohara.stellarity.registry.advancement_criterion.VoidFishedTrigger;

    //?}

public class AdvancementProvider extends FabricAdvancementProvider {

    //? >= 1.21.1 {
    /*public final AdvancementType TASK = AdvancementType.TASK;
    public final AdvancementType GOAL = AdvancementType.GOAL;
    public final AdvancementType CHALLENGE = AdvancementType.CHALLENGE;

    public AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    *///?} else {

    public final FrameType TASK = FrameType.TASK;
    public final FrameType GOAL = FrameType.GOAL;
    public final FrameType CHALLENGE = FrameType.CHALLENGE;

    public AdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    public static Advancement dummy(ResourceLocation id) {
        return new Advancement(id,
            null,
            null,
            null,
            new HashMap<>(),
            null,
            true
        );
    }
    //?}

    @Override
    public void generateAdvancement(
        //? >= 1.21.1 {
        /*HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer
        *///?} else {
        Consumer<Advancement> consumer
         //?}
    ) {
        //? >= 1.21.1 {
        /*final HolderLookup.RegistryLookup<Item> itemLookup = registryLookup.lookupOrThrow(Registries.ITEM);
        *///?} else {
        var ENTER_END_GATEWAY = dummy(Stellarity.mcId("end/enter_end_gateway"));
         //?}


        var VOID_REELS = Advancement.Builder.advancement()
            .display(StellarityItems.FISHER_OF_VOIDS,
                Component.translatable("advancements.stellarity.void_reels"),
                Component.translatable("advancements.stellarity.void_reels.description"),
                Stellarity.mcId("textures/gui/advancements/backgrounds/adventure.png"),
                TASK,
                true,
                true,
                false
            )
            //? >= 1.21.1 {
            /*.parent(new AdvancementHolder(Stellarity.mcId("end/enter_end_gateway"), null))
            .addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()))
            .requirements(new AdvancementRequirements(List.of(List.of("fishing"))))
            *///? }else {
            .parent(ENTER_END_GATEWAY)
            .addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(
                ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.ANY
            ))
            .requirements(new String[][]{{"fishing"}})
            //?}
            .build(Stellarity.id("void_fishing/void_reels"));

        var TOPPED_OFF = Advancement.Builder.advancement()
            .display(
                StellarityItems.CRYSTAL_HEARTFISH,
                Component.translatable("advancements.stellarity.topped_off"),
                Component.translatable("advancements.stellarity.topped_off.description"),
                Stellarity.mcId("textures/gui/advancements/backgrounds/adventure.png"),
                TASK,
                true,
                true,
                false
            )
            .parent(VOID_REELS)
            .addCriterion("impossible", impossible())
            .build(Stellarity.id("void_fishing/topped_off"));


        consumer.accept(TOPPED_OFF);
        consumer.accept(VOID_REELS);

    }

    //? < 1.21.1 {
    private CriterionTriggerInstance impossible() {
        return new ImpossibleTrigger.TriggerInstance();
    }

    //?} else {
    /*private Criterion<ImpossibleTrigger.TriggerInstance> impossible() {
        return CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance());
    }
    *///?}
}
