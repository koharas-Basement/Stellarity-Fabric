package xyz.kohara.stellarity.registry.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import org.jetbrains.annotations.Nullable;
import xyz.kohara.stellarity.registry.StellarityBlockEntityTypes;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.registry.recipe.AltarRecipe;
//? < 1.21.9 {

import org.joml.Vector3f;
//? }

public class AltarOfTheAccursedBlockEntity extends BlockEntity {
    public AltarOfTheAccursedBlockEntity(BlockPos pos, BlockState state) {
        this(StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED, pos, state);
    }

    private long ticksPassed = 0;

    public AltarOfTheAccursedBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
        boolean locked = blockState.getValue(AltarOfTheAccursed.LOCKED);
        var placeType = blockState.getValue(AltarOfTheAccursed.PLACE_TYPE);
        if (blockEntity instanceof AltarOfTheAccursedBlockEntity entity) {
            entity.ticksPassed++;
            var centerPos = blockPos.getCenter();
            double x = centerPos.x;
            double y = centerPos.y;
            double z = centerPos.z;

            if (level.isClientSide()) {
                if (locked) return;

                float angle = entity.ticksPassed / 20f;
                double dx = Mth.cos(angle);
                double dz = Mth.sin(angle);

                //? >= 1.21.9 {

                /*var purpleParticle = new DustColorTransitionOptions(12255487, 1769509, 1.4f);
                 *///?} else {
                var purpleParticle = new DustColorTransitionOptions(new Vector3f(0.733f, 0.0f, 1.0f), new Vector3f(0.106f, 0.0f, 0.145f), 1.4f);
                //? }

                level.addParticle(
                    purpleParticle,
                    x + dx, y, z + dz,
                    0, 0, 0
                );


                level.addParticle(
                    purpleParticle,
                    x - dx, y, z - dz,
                    0, 0, 0
                );


                dx = Mth.cos(-angle) * 1.5;
                dz = Mth.sin(-angle) * 1.5;


                level.addParticle(
                    purpleParticle,
                    x + dx, y, z + dz,
                    0, 0, 0
                );

                level.addParticle(
                    purpleParticle,
                    x - dx, y, z - dz,
                    0, 0, 0
                );
                if (entity.ticksPassed % 3 == 0) {
                    dx = level.random.nextGaussian() * 0.5;
                    dz = level.random.nextGaussian() * 0.5;
                    level.addParticle(
                        ParticleTypes.ENCHANT,
                        x + dx, y + 1.5, z + dz,
                        dx * 2, -1.5, dz * 2
                    );
                }

                level.addParticle(
                    ParticleTypes.WITCH,
                    x, y + 0.5, z,
                    0, 0,
                    0
                );

            } else if (level instanceof ServerLevel serverLevel) {

                if (!placeType.bypassesDragon()) {
                    var end = serverLevel.getServer().getLevel(Level.END);
                    EndDragonFight dragonFight = end == null ? null : end.getDragonFight();


                    boolean newLocked = dragonFight != null && !(dragonFight.hasPreviouslyKilledDragon() && dragonFight.stellarity$dragonKilled());
                    if (locked != newLocked) {
                        locked = newLocked;
                        serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(AltarOfTheAccursed.LOCKED, newLocked));
                    }
                }

                if (entity.ticksPassed % 10 == 0) {
                    AltarRecipe.handleItems(serverLevel, x, y, z, locked);
                }
                ;

            }
        }
    }


}
