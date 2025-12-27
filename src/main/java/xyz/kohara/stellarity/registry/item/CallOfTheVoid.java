package xyz.kohara.stellarity.registry.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class CallOfTheVoid extends BowItem {
    public static Item.Properties properties() {
        return new Item.Properties().fireResistant().rarity(Rarity.EPIC).stacksTo(1);
    }

    public CallOfTheVoid() {
        super(properties());

    }

    public CallOfTheVoid(Properties properties) {
        super(properties);
    }


    //TODO
}