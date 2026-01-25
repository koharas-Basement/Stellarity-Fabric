package xyz.kohara.stellarity.registry.item;

import dev.architectury.platform.Platform;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
import vazkii.patchouli.api.PatchouliAPI;
import xyz.kohara.stellarity.Stellarity;

public class Endonomicon extends Item {
    public Endonomicon(Properties properties) {
        super(properties);
    }

    public static Properties properties() {
        return new Properties().stacksTo(1);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        var result = super.use(level, player, interactionHand);
        
        if (!(player.level().isClientSide())) return result;

        boolean patchouliLoaded = Platform.isModLoaded("patchouli");
        if (!patchouliLoaded) {
            player.displayClientMessage(
                Component.translatable("message.stellarity.missing_patchouli"), true
            );

            return result;
        }

        try {
            PatchouliAPI.get().openBookGUI(Stellarity.id("endonomicon"));
        } catch (Exception e) {
            player.displayClientMessage(
                Component.literal(e.toString()), false
            );
        }


        return result;


    }
}
