package xyz.kohara.stellarity.mixin.creative_shock;


import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.kohara.stellarity.registry.effect.CreativeShockEffect;

import java.util.Collection;

//? < 1.21.9 {
import net.minecraft.core.BlockPos;

 //? } else {
/*import com.llamalad7.mixinextras.sugar.Local;
    *///? }

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    @Shadow
    @Final
    public ServerPlayerGameMode gameMode;


    @Shadow
    public abstract boolean setGameMode(GameType gameType);

    @Unique
    @Nullable
    private GameType initialGameType = null;

    //? < 1.21.9 {
    public ServerPlayerMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
        super(level, blockPos, f, gameProfile);
    }
    //? } else {

    /*public ServerPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }
    *///? }


    @Inject(method = "onEffectAdded", at = @At("HEAD"))
    private void effectAdded(MobEffectInstance effectInstance, Entity entity, CallbackInfo ci) {
        var type = gameMode.getGameModeForPlayer();

        if (!(effectInstance.getEffect()/*? > 1.21.9 {*//*.value()*//*? } */ instanceof CreativeShockEffect effect))
            return;


        if (!effect.extremeCreativeShock() && type == GameType.CREATIVE) return;

        if (initialGameType == null) initialGameType = type;
        setGameMode(GameType.ADVENTURE);
    }

    //? < 1.21.9 {
    @Inject(method = "onEffectRemoved", at = @At("HEAD"))
    protected void effectRemoved(MobEffectInstance effectInstance, CallbackInfo ci)
    //? } else {

    /*@Inject(method = "onEffectsRemoved", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.AFTER))
    protected void effectRemoved(Collection<MobEffectInstance> collection, CallbackInfo ci, @Local MobEffectInstance effectInstance)
    *///? }
    {

        if (!(effectInstance.getEffect()/*? > 1.21.9 {*//*.value()*//*? } */ instanceof CreativeShockEffect effect)) return;

        setGameMode(initialGameType);
        initialGameType = null;


    }

}
