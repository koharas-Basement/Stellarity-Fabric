package xyz.kohara.stellarity.registry.effect;

//? if >= 1.21.1 {
/*import net.minecraft.server.level.ServerLevel;
 *///?}
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;

public class CreativeShockEffect extends MobEffect {
    //TODO maybe use a config for it or something
    private static final boolean extremeCreativeShock = false;
    
    private GameType initialGameType = null;
    
    public CreativeShockEffect(int color) {
        super(MobEffectCategory.HARMFUL, color);
    }
    
    @Override
    //? if 1.21.1 {
    /*public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
     *///?} else {
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        //?}
        if (!(livingEntity instanceof ServerPlayer player)) return /*? if >= 1.21.1 {*//*false *//*?}*/;
        GameType type = player.gameMode.getGameModeForPlayer();
        if (type.isBlockPlacingRestricted()) return /*? if >= 1.21.1 {*//*false *//*?}*/;
        if (initialGameType == null) initialGameType = type;
        switch (type) {
            case CREATIVE -> {
                if (extremeCreativeShock) {
                    player.gameMode.changeGameModeForPlayer(GameType.ADVENTURE);
                    return /*? if >= 1.21.1 {*//*true *//*?}*/;
                }
            }
            case SURVIVAL -> {
                player.gameMode.changeGameModeForPlayer(GameType.ADVENTURE);
                return /*? if >= 1.21.1 {*//*true *//*?}*/;
            }
        }
        /*? if >= 1.21.1 {*//*return false;*//*?}*/
    }
    
    @Override
    //? < 1.21.1
    public boolean isDurationEffectTick(int duration, int amplifier) {
        //? >= 1.21.1
        /*public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {*/
        return true;
    }
}