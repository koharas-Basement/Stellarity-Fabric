package xyz.kohara.stellarity.registry.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.registry.StellarityItems;

public class PhantomItemFrame extends ItemFrame {
    public PhantomItemFrame(EntityType<? extends ItemFrame> entityType, Level level) {
        super(entityType, level);
    }

    public PhantomItemFrame(Level level, BlockPos blockPos, Direction direction) {
        this(StellarityEntities.PHANTOM_ITEM_FRAME.get(), level, blockPos, direction);
    }

    public PhantomItemFrame(EntityType<? extends ItemFrame> entityType, Level level, BlockPos blockPos, Direction direction) {
        super(entityType, level, blockPos, direction);
    }

    @Override
    public ItemStack getFrameItemStack() {
        return new ItemStack(StellarityItems.PHANTOM_ITEM_FRAME.get());
    }

    @Override
    public void tick() {
        super.tick();

        ItemStack item = getItem();
        boolean empty = item.isEmpty();
        setInvisible(!empty);

        var level = this.level();
        if (empty && level.isClientSide()) {
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();

            double offset = (this.random.nextDouble() - 0.5) * 0.8;
            double coOffset = (this.random.nextDouble() - 0.5) * 0.8;

            switch (this.getDirection()) {
                case UP:
                case DOWN:
                    x += offset;
                    z += coOffset;
                    break;
                case NORTH:
                case SOUTH:
                    x += offset;
                    y += coOffset;
                    break;
                case EAST:
                case WEST:
                    y += offset;
                    z += coOffset;
                    break;
            }

            switch (this.getDirection()) {
                case UP:
                    y += 0.1;
                    break;
                case DOWN:
                    y -= 0.1;
                    break;
                case NORTH:
                    z -= 0.1;
                    break;
                case SOUTH:
                    z += 0.1;
                    break;
                case EAST:
                    x += 0.1;
                    break;
                case WEST:
                    x -= 0.1;
                    break;
            }

            level.addParticle(ParticleTypes.MYCELIUM, x, y, z, 0, -0.5, 0);
        }
    }
}
