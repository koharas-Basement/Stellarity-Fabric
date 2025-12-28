package xyz.kohara.stellarity.client.registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import xyz.kohara.stellarity.Stellarity;
//? 1.20.1 {
import net.minecraft.world.item.alchemy.PotionUtils;
//? } else{
/*import net.minecraft.core.component.DataComponents;
 *///? }

@Environment(EnvType.CLIENT)
public class StellarityTooltips {
    public static final Component EMPTY_LINE = Component.literal("");
    public static final Component STELLARITY = Component.translatable("Stellarity").withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC);


    public static void init() {
        Stellarity.LOGGER.info("Registering Stellarity Tooltips");

        ItemTooltipCallback.EVENT.register((
            //? 1.20.1 {
            itemStack, unused, list
            //? } else {
            /*itemStack, unused, unused2, list
             *///? }
        ) -> {
            Item item = itemStack.getItem();
            boolean isStellarityPotion = item instanceof PotionItem &&
                //? 1.20.1 {
                BuiltInRegistries.POTION.getKey(PotionUtils.getPotion(itemStack)).getNamespace().equals(Stellarity.MOD_ID);
            //?} else {
            /*itemStack.get(DataComponents.POTION_CONTENTS).potion().map(holder -> BuiltInRegistries.POTION.getKey(holder.value()).getNamespace().equals(Stellarity.MOD_ID)).orElse(false);
             *///? }
            if (!(BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(Stellarity.MOD_ID) || isStellarityPotion)) {
                return;
            }


            list.add(EMPTY_LINE);
            list.add(STELLARITY);
        });
    }
}
