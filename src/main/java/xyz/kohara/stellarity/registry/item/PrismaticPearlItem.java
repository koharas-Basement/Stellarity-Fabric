package xyz.kohara.stellarity.registry.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.ItemStack;
//? < 1.21.9 {

import net.minecraft.world.InteractionResultHolder;
import xyz.kohara.stellarity.registry.entity.ThrownPrismaticPearl;

	//? } else {
/*import net.minecraft.world.InteractionResult;
 *///? }

public class PrismaticPearlItem extends Item {
	public PrismaticPearlItem(Properties properties) {
		super(properties);
	}

	public static Properties properties() {
		return new Item.Properties().stacksTo(1);
	}


	@Override
	public /*? < 1.21.9 {*/InteractionResultHolder<ItemStack>/*? } else {*//*InteractionResult*//*? } */ use(Level level, Player player, InteractionHand interactionHand) {
		ItemStack itemStack = player.getItemInHand(interactionHand);

		if (level instanceof ServerLevel serverLevel) {
			player.getCooldowns().addCooldown(itemStack/*? < 1.21.9 {*/.getItem()/*? } */, 5 * 20);

			var pearl = new ThrownPrismaticPearl(level, player);
			pearl.setItem(itemStack);
			pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F * 1.25F, 1.0F);
			level.addFreshEntity(pearl);
		}

		//? < 1.21.9 {

		return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
		//? } else {
		/*return InteractionResult.SUCCESS;
		 *///? }
	}
}
