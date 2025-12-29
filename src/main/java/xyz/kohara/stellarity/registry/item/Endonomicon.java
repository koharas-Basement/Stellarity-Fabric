package xyz.kohara.stellarity.registry.item;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

//? < 1.21.9 {
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;
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

        if (!(player instanceof LocalPlayer localPlayer)) return result;


        boolean patchouliLoaded = FabricLoader.getInstance().isModLoaded("patchouli");
        if (!patchouliLoaded) {
            localPlayer.displayClientMessage(
                Component.translatable("message.stellarity.missing_patchouli"), true
            );

            return result;
        }

        try {
            Object apiInstance = Class.forName("vazkii.patchouli.api.PatchouliAPI").getDeclaredMethod("get").invoke(Object.class);

            apiInstance.getClass().getMethod("openBookGUI", ResourceLocation.class).invoke(apiInstance, Stellarity.id("endonomicon"));


        } catch (ReflectiveOperationException e) {
            localPlayer.displayClientMessage(
                Component.literal(e.toString()), false
            );
        }


        return result;


    }
}
