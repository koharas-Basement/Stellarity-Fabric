package xyz.kohara.stellarity.advancement_criterion;

//? < 1.21 {
import com.google.gson.JsonObject;
import java.util.Collection;

import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.Stellarity;

public class VoidFishedTrigger extends SimpleCriterionTrigger<VoidFishedTrigger.TriggerInstance> {
  static final ResourceLocation ID = Stellarity.of("void_fished");

  @NotNull
  public ResourceLocation getId() {
    return ID;
  }

  @NotNull
  public TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
    ItemPredicate itemPredicate = ItemPredicate.fromJson(jsonObject.get("rod"));
    ContextAwarePredicate contextAwarePredicate2 = EntityPredicate.fromJson(jsonObject, "entity", deserializationContext);
    ItemPredicate itemPredicate2 = ItemPredicate.fromJson(jsonObject.get("item"));
    return new TriggerInstance(contextAwarePredicate, itemPredicate, contextAwarePredicate2, itemPredicate2);
  }

  public void trigger(ServerPlayer serverPlayer, ItemStack itemStack, FishingHook fishingHook, Collection<ItemStack> collection) {
    LootContext lootContext = EntityPredicate.createContext(serverPlayer, (Entity)(fishingHook.getHookedIn() != null ? fishingHook.getHookedIn() : fishingHook));
    this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(itemStack, lootContext, collection));
  }

  public static class TriggerInstance extends AbstractCriterionTriggerInstance {
    private final ItemPredicate rod;
    private final ContextAwarePredicate entity;
    private final ItemPredicate item;

    public TriggerInstance(ContextAwarePredicate contextAwarePredicate, ItemPredicate itemPredicate, ContextAwarePredicate contextAwarePredicate2, ItemPredicate itemPredicate2) {
      super(ID, contextAwarePredicate);
      this.rod = itemPredicate;
      this.entity = contextAwarePredicate2;
      this.item = itemPredicate2;
    }

    public static TriggerInstance fishedItem(ItemPredicate itemPredicate, EntityPredicate entityPredicate, ItemPredicate itemPredicate2) {
      return new TriggerInstance(ContextAwarePredicate.ANY, itemPredicate, EntityPredicate.wrap(entityPredicate), itemPredicate2);
    }

    public boolean matches(ItemStack itemStack, LootContext lootContext, Collection<ItemStack> collection) {
      if (!this.rod.matches(itemStack)) {
        return false;
      } else if (!this.entity.matches(lootContext)) {
        return false;
      } else {
        if (this.item != ItemPredicate.ANY) {
          boolean bl = false;
          Entity entity = (Entity)lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
          if (entity instanceof ItemEntity itemEntity) {
            if (this.item.matches(itemEntity.getItem())) {
              bl = true;
            }
          }

          for(ItemStack itemStack2 : collection) {
            if (this.item.matches(itemStack2)) {
              bl = true;
              break;
            }
          }

          return bl;
        }

        return true;
      }
    }

    @NotNull
    public JsonObject serializeToJson(SerializationContext serializationContext) {
      JsonObject jsonObject = super.serializeToJson(serializationContext);
      jsonObject.add("rod", this.rod.serializeToJson());
      jsonObject.add("entity", this.entity.toJson(serializationContext));
      jsonObject.add("item", this.item.serializeToJson());
      return jsonObject;
    }
  }
}
//? } else {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Collection;
import java.util.Optional;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;
import xyz.kohara.stellarity.StellarityCriteriaTriggers;

public class VoidFishedTrigger extends SimpleCriterionTrigger<VoidFishedTrigger.TriggerInstance> {
  public @NotNull Codec<TriggerInstance> codec() {
    return TriggerInstance.CODEC;
  }

  public void trigger(ServerPlayer serverPlayer, ItemStack itemStack, FishingHook fishingHook, Collection<ItemStack> collection) {
    LootContext lootContext = EntityPredicate.createContext(serverPlayer, (Entity)(fishingHook.getHookedIn() != null ? fishingHook.getHookedIn() : fishingHook));
    this.trigger(serverPlayer, (triggerInstance) -> triggerInstance.matches(itemStack, lootContext, collection));
  }

  public static record TriggerInstance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> rod, Optional<ContextAwarePredicate> entity, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
    public static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create((instance) -> instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player), ItemPredicate.CODEC.optionalFieldOf("rod").forGetter(TriggerInstance::rod), EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("entity").forGetter(TriggerInstance::entity), ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggerInstance::item)).apply(instance, TriggerInstance::new));

    public static Criterion<TriggerInstance> fishedItem(Optional<ItemPredicate> optional, Optional<EntityPredicate> optional2, Optional<ItemPredicate> optional3) {
      return StellarityCriteriaTriggers.VOID_FISHED.createCriterion(new TriggerInstance(Optional.empty(), optional, EntityPredicate.wrap(optional2), optional3));
    }

    public boolean matches(ItemStack itemStack, LootContext lootContext, Collection<ItemStack> collection) {
      if (this.rod.isPresent() && !((ItemPredicate)this.rod.get()).test(itemStack)) {
        return false;
      } else if (this.entity.isPresent() && !((ContextAwarePredicate)this.entity.get()).matches(lootContext)) {
        return false;
      } else {
        if (this.item.isPresent()) {
          boolean bl = false;
          //? < 1.21.9
          Entity entity = (Entity)lootContext.getParamOrNull(LootContextParams.THIS_ENTITY);
          //? >= 1.21.9
          //Entity entity = (Entity)lootContext.getOptionalParameter(LootContextParams.THIS_ENTITY);

          if (entity instanceof ItemEntity itemEntity) {
            if (this.item.get().test(itemEntity.getItem())) {
              bl = true;
            }
          }

          for(ItemStack itemStack2 : collection) {
            if (this.item.get().test(itemStack2)) {
              bl = true;
              break;
            }
          }

          return bl;
        }

        return true;
      }
    }

    public void validate(CriterionValidator criterionValidator) {
      SimpleCriterionTrigger.SimpleInstance.super.validate(criterionValidator);
      criterionValidator.validateEntity(this.entity, "entity");
    }
  }
}


*///? }