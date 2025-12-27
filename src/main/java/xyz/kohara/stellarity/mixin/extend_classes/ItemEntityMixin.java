package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.Stellarity;
import xyz.kohara.stellarity.registry.StellarityBlocks;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;

//? < 1.21.9 {
import net.minecraft.nbt.CompoundTag;
//? } else {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? }

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
        this.stellarity$setGlowColor(crafting ? ChatFormatting.DARK_PURPLE.getColor() : -1);
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

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    public void saveData(
        //? < 1.21.9 {
        CompoundTag tag, CallbackInfo ci
        //? } else {
        /*ValueOutput tag, CallbackInfo ci
        *///? }
    ) {

        tag.putString("stellarity:mode", itemMode.toString());
    }

    //? > 1.21.9
    //@SuppressWarnings("OptionalGetWithoutIsPresent")
    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    public void readData(
        //? < 1.21.9 {
        CompoundTag tag, CallbackInfo ci
        //? } else {
        /*ValueInput tag, CallbackInfo ci
        *///? }
    ) {
        if (tag.contains("stellarity:mode")) {
            try {
                stellarity$setItemMode(ItemMode.valueOf(tag.getString("stellarity:mode")
                    //? > 1.21.9 {
                    /*.get()
                    *///? }
                ));
            } catch (Exception e) {
                Stellarity.LOGGER.info("Detected invalid itemmode, ignoring");
            }
        }
    }


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V", shift = At.Shift.AFTER))
    public void movedOffRecipeBlock(CallbackInfo ci) {
        if (level() instanceof ServerLevel level) {
            if (level.getBlockState(BlockPos.containing(this.position().add(0, -0.5, 0))).is(StellarityBlocks.ALTAR_OF_THE_ACCURSED))
                return;
            if (itemMode == ItemMode.CRAFTING) stellarity$setItemMode(ItemMode.PICKUP);
        }
    }
}
