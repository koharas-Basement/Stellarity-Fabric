package xyz.kohara.stellarity.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.advancements.*;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;


import net.minecraft.advancements.critereon.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.StellarityItems;


import java.util.HashMap;
import java.util.function.Consumer;
//? >= 1.21.1 {
/*import net.minecraft.core.HolderLookup;
import xyz.kohara.stellarity.advancement_criterion.VoidFishedTrigger;
import net.minecraft.world.item.Item;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.registries.Registries;
*///?} else {

import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.CriterionTriggerInstance;
import xyz.kohara.stellarity.advancement_criterion.VoidFishedTrigger;

  //?}

public class AdvancementProvider extends FabricAdvancementProvider {

  //? >= 1.21.1 {
  /*public AdvancementType TASK = AdvancementType.TASK;
  public AdvancementType GOAL = AdvancementType.GOAL;
  public AdvancementType CHALLENGE = AdvancementType.CHALLENGE;

  public AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
    super(output, registryLookup);
  }

  *///?} else {

  public FrameType TASK = FrameType.TASK;
  public FrameType GOAL = FrameType.GOAL;
  public FrameType CHALLENGE = FrameType.CHALLENGE;

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
    /*final HolderLookup.RegistryLookup<Item> itemLookup =registryLookup.lookupOrThrow(Registries.ITEM);
     *///?} else {
    var ENTER_END_GATEWAY = dummy(Stellarity.mcOf("end/enter_end_gateway"));
    //?}




    var VOID_REELS = Advancement.Builder.advancement()
      .display(StellarityItems.FISHER_OF_VOIDS,
        Component.translatable("advancements.stellarity.void_reels"),
        Component.translatable("advancements.stellarity.void_reels.description"),
        Stellarity.mcOf("textures/gui/advancements/backgrounds/adventure.png"),
        TASK,
        true,
        true,
        false
      )
      //? >= 1.21.1 {
      /*.parent(new AdvancementHolder(Stellarity.mcOf("end/enter_end_gateway"), null))
      .addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(Optional.empty(), Optional.empty(), Optional.empty()))
      .requirements(new AdvancementRequirements(List.of(List.of("fishing"))))
      *///? }else {
      .parent(ENTER_END_GATEWAY)
      .addCriterion("fishing", VoidFishedTrigger.TriggerInstance.fishedItem(
        ItemPredicate.ANY, EntityPredicate.ANY, ItemPredicate.ANY
      ))
      .requirements(new String[][]{{"fishing"}})
      //?}
      .build(Stellarity.of("void_fishing/void_reels"));

    var TOPPED_OFF = Advancement.Builder.advancement()
      .display(
        StellarityItems.CRYSTAL_HEARTFISH,
        Component.translatable("advancements.stellarity.topped_off"),
        Component.translatable("advancements.stellarity.topped_off.description"),
        Stellarity.mcOf("textures/gui/advancements/backgrounds/adventure.png"),
        TASK,
        true,
        true,
        false
      )
      .parent(VOID_REELS)
      .addCriterion("impossible", impossible())
      .build(Stellarity.of("void_fishing/topped_off"));


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
