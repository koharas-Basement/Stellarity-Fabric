package xyz.kohara.stellarity.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
//? < 1.21.9 {

import net.minecraft.world.InteractionResultHolder;
 //? } else {

//? }

public class PrismaticPearlItem extends Item {
  public PrismaticPearlItem(Properties properties) {
    super(properties);
  }

  public static Properties properties() {
    return new Item.Properties().stacksTo(1);
  }

//  @Override
//  public /*? < 1.21.9 {*//*InteractionResultHolder<ItemStack>*//*? } else {*/InteractionResult/*? } */ use(Level level, Player player, InteractionHand interactionHand) {
//
//
//    return InteractionResult.SUCCESS;
//  }
}
