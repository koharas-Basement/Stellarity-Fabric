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
import vazkii.patchouli.api.PatchouliAPI;
import xyz.kohara.stellarity.Stellarity;
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
        var result = super.use(level, player, interactionHand);


        //? < 1.21.9 {
        if (!(player instanceof LocalPlayer localPlayer)) return result;


        boolean patchouliLoaded = FabricLoader.getInstance().isModLoaded("patchouli");
        if (!patchouliLoaded) {
            localPlayer.displayClientMessage(
                Component.translatable("message.stellarity.missing_patchouli"), true
            );

            return result;
        }

        try {
            PatchouliAPI.get().openBookGUI(Stellarity.id("endonomicon"));
        } catch (Exception e) {
            localPlayer.displayClientMessage(
                Component.literal(e.toString()), false
            );
        }
        //? } else {
        /*// fix later to translation
        player.displayClientMessage(Component.literal("Blame Patchouli for not supplying modern support"), true);
        *///? }


        return result;


    }
}
