package xyz.kohara.stellarity.mixin.extend_classes;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.damagesource.DamageSources;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.interface_injection.ExtDamageSources;
import xyz.kohara.stellarity.registry.StellarityDamageSources;

@Mixin(DamageSources.class)
public abstract class DamageSourcesMixin implements ExtDamageSources {
    @Unique @Final @Mutable
    private StellarityDamageSources stellarity$stellaritySources;
    
    @Inject(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/core/RegistryAccess;registryOrThrow(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/Registry;",
            shift = At.Shift.AFTER
        )
    )
    private void constructorInject(RegistryAccess registry, CallbackInfo ci) {
        this.stellarity$stellaritySources = new StellarityDamageSources(registry);
    }
    
    @Override
    public StellarityDamageSources stellarity$stellaritySources() {
        return stellarity$stellaritySources;
    }
}
