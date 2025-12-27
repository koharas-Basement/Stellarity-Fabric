package xyz.kohara.stellarity.client.gui.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class StellarityConfig extends Screen {

	public StellarityConfig(Screen screen) {
		super(Component.translatable("stellarity.config.title"));
	}

	@Override
	protected void init() {
	}
}
