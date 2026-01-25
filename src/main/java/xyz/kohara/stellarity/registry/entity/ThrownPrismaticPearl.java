package xyz.kohara.stellarity.registry.entity;

import net.minecraft.core.particles.DustParticleOptions;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile./*? > 1.21.9 { *//*throwableitemprojectile.*//*?}*/ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import xyz.kohara.stellarity.registry.StellarityEntities;
import xyz.kohara.stellarity.registry.StellarityItems;
//? > 1.21.9 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///? } else {
import net.minecraft.nbt.CompoundTag;
    //? }

import java.util.Set;

public class ThrownPrismaticPearl extends ThrowableItemProjectile {
    public ThrownPrismaticPearl(EntityType<? extends ThrownPrismaticPearl> entityType, Level level) {
        super(entityType, level);
    }

    private Vec3 oldPos = null;

    public ThrownPrismaticPearl(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(StellarityEntities.PRISMATIC_PEARL.get(), livingEntity, level);
        setItem(itemStack);
    }

    @Override
    public void shootFromRotation(Entity entity, float f, float g, float h, float i, float j) {
        super.shootFromRotation(entity, f, g, h, i, j);
        if (!level().isClientSide()) {
            setBisexualTrail(entity instanceof Player player && player.getGameProfile().getName().equalsIgnoreCase("bush_moss"));
        }
    }

    public static final int DATA_SIZE = Entity.stellarity$DATA_SIZE + 1;
    // first one is + 0    but intellj simplies it away
    public static EntityDataAccessor<Boolean> DATA_BISEXUAL_TRAIL = new EntityDataAccessor<>(Entity.stellarity$DATA_SIZE, EntityDataSerializers.BOOLEAN);


    @Override
    public void stellarity$defineSynchedData() {
        super.stellarity$defineSynchedData();

        stellarity$addSynchedData(DATA_BISEXUAL_TRAIL, false);
    }

    public boolean hasBisexualTrail() {
        return stellarity$entityData().get(DATA_BISEXUAL_TRAIL);
    }

    public void setBisexualTrail(boolean mode) {
        stellarity$entityData().set(DATA_BISEXUAL_TRAIL, mode);
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        if (compoundTag.contains("stellarity:bisexual_trail")) {
            setBisexualTrail(compoundTag.getBoolean("stellarity:bisexual_trail"));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("stellarity:bisexual_trail", hasBisexualTrail());
    }

    public static final int[] RAINBOW_COLORS = new int[]{
        0xfa80fc,
        0xfa9ce0,
        0xfab5c7,
        0xfac5b7,
        0xfad9a3,
        0xcb6448,
        0x7a07f6,
        0x77e1a3,
        0x77b8cc,
        0x935118,
        0xbbff41,
        0xcae150,
        0xfa8150,
        0xfa811d
    };

    public static final int[] BISEXUAL_COLORS = new int[]{
        0xD60270,
        0xD60270,
        0xD60270,
        0xD60270,
        0x6348a3,
        0x6348a3,
        0x6348a3,
        0x6348a3,
        0x004be0,
        0x004be0,
        0x004be0,
        0x004be0
    };

    private int colorIndex = 0;


    @Override
    public void tick() {
        super.tick();
        var level = level();

        var list = hasBisexualTrail() ? BISEXUAL_COLORS : RAINBOW_COLORS;


        var position = position();
        var x = position.x;
        var y = position.y;
        var z = position.z;

        if (oldPos == null) {
            oldPos = new Vec3(x, y, z);
        }

        var dx = x - oldPos.x;
        var dy = y - oldPos.y;
        var dz = z - oldPos.z;

        long steps = (int) (Math.max(Math.max(Math.abs(dx), Math.abs(dy)), Math.abs(dz)) / 0.1);

        var xStep = dx / steps;
        var yStep = dy / steps;
        var zStep = dz / steps;

        for (int i = 0; i <= steps + 1; i++) {
            var color = list[colorIndex];

            stellarity$setGlowColor(color);
            level.addParticle(new DustParticleOptions(Vec3.fromRGB24(color).toVector3f(), 1.5f), x + i * xStep, y + i * yStep, z + i * zStep, 0, 0, 0);
        }
        if (++colorIndex >= list.length) {
            colorIndex = 0;
        }

        oldPos = new Vec3(x, y, z);

    }

    public ThrownPrismaticPearl(Level level, LivingEntity livingEntity) {
        super(StellarityEntities.PRISMATIC_PEARL.get(), livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return StellarityItems.PRISMATIC_PEARL.get();
    }

    private static final int[] LAND_COLORS = {
        0xfa80fc, 0xfa9ce0, 0xfab5c7, 0xfac5b7, 0xfad9a3, 0xcb6448, 0x7a07f6, 0x77e1a3, 0x77b8cc, 0x935118, 0xbbff41, 0xcae150, 0xfa8150, 0xfa811d
    };

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        var level = level();
        var position = position();

        if (level instanceof ServerLevel serverLevel && !isRemoved()) {
            var owner = getOwner();
            if (owner != null) {
                owner.teleportTo(serverLevel, position.x, position.y, position.z, Set.of(), owner.getYHeadRot(), owner.getXRot());
            }

            level.playSound(null, owner.blockPosition(), SoundEvents.ENDER_EYE_DEATH, SoundSource.NEUTRAL);

            this.discard();
        }

        for (int color : LAND_COLORS) {
            level.addParticle(new DustParticleOptions(Vec3.fromRGB24(color).toVector3f(), 1.5f), position.x + random.nextDouble() * 2 - 1, position.y, position.z + random.nextDouble() * 2 - 1, 0, 0, 0);
        }
    }

    @Override
    public void syncPacketPositionCodec(double d, double e, double f) {
        super.syncPacketPositionCodec(d, e, f);
    }

    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        var level = level();
        if (level.isClientSide()) return;
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }


}
