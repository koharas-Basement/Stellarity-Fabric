//? > 1.21.10 {
/*package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.item.Tamaris;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(method = "inventoryTick", at = @At("TAIL"))
    private void addStellarityClientTicks(Level level, Entity entity, EquipmentSlot equipmentSlot, CallbackInfo ci) {
        var item = this.getItem();
        if (level instanceof ClientLevel clientLevel && item instanceof Tamaris tamaris) {
            tamaris.inventoryTick((ItemStack) (Object) this, clientLevel, entity, equipmentSlot);
        }
    }
}

*///? }