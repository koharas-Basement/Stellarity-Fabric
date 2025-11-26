package xyz.kohara.stellarity.mixin.altar_of_the_accursed;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;

import java.util.HashMap;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
  @Shadow
  public abstract ItemStack getItem();

  @Shadow
  public abstract void setItem(ItemStack itemStack);

  @Shadow
  public abstract void setPickUpDelay(int i);

  @Unique
  private ItemMode itemMode = ItemMode.PICKUP;

  public ItemEntityMixin(EntityType<?> entityType, Level level) {
    super(entityType, level);
  }

  @Override
  public ItemMode stellarity$getItemMode() {
    return itemMode;
  }

  @Override
  public void stellarity$setItemMode(ItemMode mode) {
    this.itemMode = mode;
    boolean crafting = mode == ItemMode.CRAFTING;
    setGlowingTag(crafting);

    setPickUpDelay(crafting ? Short.MAX_VALUE : 0);
  }

  @Override
  public void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
    if (this.itemMode != ItemMode.CRAFTING) return;
    ItemStack stack = this.getItem();
    Integer count = results.get(stack);

    if (count == null || count == 0) {
      this.discard();
      return;
    }

    setItem(stack.copyWithCount(count));
  }
}
