package xyz.kohara.stellarity.client.registry.gui.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

//$ clientOnly
@net.minecraftforge.api.distmarker.OnlyIn(net.minecraftforge.api.distmarker.Dist.CLIENT)
public class StellarityConfig extends Screen {

    public StellarityConfig(Screen screen) {
        super(Component.translatable("stellarity.config.title"));
    }

    @Override
    protected void init() {
    }
}
