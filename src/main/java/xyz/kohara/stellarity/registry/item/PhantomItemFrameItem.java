package xyz.kohara.stellarity.registry.item;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemFrameItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.registry.entity.PhantomItemFrame;


public class PhantomItemFrameItem extends ItemFrameItem {
	public PhantomItemFrameItem(EntityType<? extends HangingEntity> entityType, Item.Properties properties) {
		super(entityType, properties);
	}

	public PhantomItemFrameItem(Item.Properties properties) {
		this(StellarityEntities.PHANTOM_ITEM_FRAME, properties);
	}

	public InteractionResult useOn(UseOnContext useOnContext) {
		BlockPos blockPos = useOnContext.getClickedPos();
		Direction direction = useOnContext.getClickedFace();
		BlockPos blockPos2 = blockPos.relative(direction);
		Player player = useOnContext.getPlayer();
		ItemStack itemStack = useOnContext.getItemInHand();
		if (player != null && !this.mayPlace(player, direction, itemStack, blockPos2)) {
			return InteractionResult.FAIL;
		} else if (useOnContext.getLevel() instanceof ServerLevel level) {
			HangingEntity hangingEntity = new PhantomItemFrame(level, blockPos2, direction);


			EntityType.createDefaultStackConfig(level, itemStack, player).accept(hangingEntity);
			if (hangingEntity.survives()) {
				if (!level.isClientSide()) {
					hangingEntity.playPlacementSound();
					level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
					level.addFreshEntity(hangingEntity);
				}

				itemStack.shrink(1);
				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.CONSUME;
			}
		}

		return InteractionResult.SUCCESS;
	}
}
