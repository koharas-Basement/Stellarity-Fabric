package xyz.kohara.stellarity.registry.item;

import net.minecraft.world.item.FishingRodItem;

public class FisherOfVoids extends FishingRodItem {
    public FisherOfVoids(Properties properties) {
        super(properties);
    }

    public static Properties properties() {
        return new Properties().stacksTo(1).durability(100);
    }
}
