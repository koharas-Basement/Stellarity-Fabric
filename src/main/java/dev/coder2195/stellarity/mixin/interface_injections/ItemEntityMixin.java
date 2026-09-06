package dev.coder2195.stellarity.mixin.interface_injections;

import dev.coder2195.stellarity.interface_injection.ExtItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity implements ExtItemEntity {
	@Shadow
	public abstract ItemStack getItem();

	@Shadow
	public abstract void setItem(ItemStack itemStack);

	@Shadow
	public abstract void setPickUpDelay(int ticks);

	public ItemEntityMixin(EntityType<?> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	public void stellarity$setItemMode(ItemMode mode, @Nullable Integer color) {
		ExtItemEntity.super.stellarity$setItemMode(mode, color);

		boolean crafting = mode.isCrafting();
		setGlowingTag(color != null || crafting);
		this.stellarity$setGlowColor(color == null ? crafting ? 11141290 : -1 : color);

		var delay = mode.getPickupDelay();
		if (delay != null) setPickUpDelay(delay);
	}

	@Override
	public void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
		if (stellarity$getItemMode() != ItemMode.ALTAR_CRAFTING) return;
		ItemStack stack = this.getItem();
		Integer count = results.get(stack);

		if (count == null || count == 0) {
			this.discard();
			return;
		}

		setItem(stack.copyWithCount(count));
	}


}
