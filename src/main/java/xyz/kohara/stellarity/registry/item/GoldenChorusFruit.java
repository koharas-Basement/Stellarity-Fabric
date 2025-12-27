package xyz.kohara.stellarity.registry.item;


public class GoldenChorusFruit extends TeleportingFood {
	private static final int TELEPORT_DIAMETER = 300;

	public GoldenChorusFruit(Properties properties) {
		super(properties, TELEPORT_DIAMETER);
	}

	public static Properties properties() {
		return TeleportingFood.foodProperties(6, 14.4f, true, TELEPORT_DIAMETER);
	}
}