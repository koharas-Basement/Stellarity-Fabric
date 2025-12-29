package xyz.kohara.stellarity.registry.item;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

//? < 1.21.9 {
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
//? } else {
/*import net.minecraft.world.InteractionResult;
 *///? }

public class Endonomicon extends Item {
    public Endonomicon(Properties properties) {
        super(properties);
    }

    public static Properties properties() {
        return new Properties().stacksTo(1);
    }


    @Override
    public /*? < 1.21.9 { */InteractionResultHolder<ItemStack>/*? } else { */ /*InteractionResult*//*? }*/ use(Level level, Player player, InteractionHand interactionHand) {
        boolean patchouliLoaded = FabricLoader.getInstance().isModLoaded("patchouli");

        if (!patchouliLoaded && player instanceof LocalPlayer localPlayer) {
            localPlayer.displayClientMessage(
                Component.translatable("message.stellarity.missing_patchouli"), true
            );
        }


        return super.use(level, player, interactionHand);


    }
}
