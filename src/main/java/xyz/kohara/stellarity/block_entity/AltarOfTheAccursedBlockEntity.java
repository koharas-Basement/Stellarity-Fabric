package xyz.kohara.stellarity.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;

import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.kohara.stellarity.StellarityBlockEntityTypes;
import xyz.kohara.stellarity.StellarityRecipeTypes;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;

import java.util.HashMap;
import java.util.List;
//? > 1.21.9 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
 *///?}

public class AltarOfTheAccursedBlockEntity extends BlockEntity {
  public AltarOfTheAccursedBlockEntity(BlockPos pos, BlockState state) {
    this(StellarityBlockEntityTypes.ALTAR_OF_THE_ACCURSED, pos, state);
  }

  private boolean unlocked = false;
  private long ticksPassed = 0;

  public AltarOfTheAccursedBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
    super(blockEntityType, blockPos, blockState);
  }

  //? 1.20.1 {
  @Override
  protected void saveAdditional(CompoundTag compoundTag) {
    super.saveAdditional(compoundTag);
    compoundTag.putBoolean("unlocked", unlocked);
  }

  @Override
  public void load(CompoundTag compoundTag) {
    super.load(compoundTag);
    if (compoundTag.contains("unlocked")) {
      this.unlocked = compoundTag.getBoolean("unlocked");
    }
  }

  @Override
  public @NotNull CompoundTag getUpdateTag() {
    var tag = super.getUpdateTag();
    tag.putBoolean("unlocked", this.unlocked);
    return tag;
  }
  //? } else 1.21.1 {
/*
  @Override
  protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
    super.saveAdditional(compoundTag, provider);
    compoundTag.putBoolean("unlocked", unlocked);
  }

  @Override
  protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
    super.loadAdditional(compoundTag, provider);
    if (compoundTag.contains("unlocked")) {
      this.unlocked = compoundTag.getBoolean("unlocked");
    }
  }

  *///? } else {
/*
  @Override
  public void saveAdditional(ValueOutput valueOutput) {
    super.saveAdditional(valueOutput);
    valueOutput.putBoolean("unlocked", unlocked);
  }

  @Override
  protected void loadAdditional(ValueInput valueInput) {
    super.loadAdditional(valueInput);
    this.unlocked = valueInput.getBooleanOr("unlocked", false);
  }

  *///? }

  //? > 1.21 {
/*
  @Override
  public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider provider) {
    var tag = super.getUpdateTag(provider);
    tag.putBoolean("unlocked", this.unlocked);
    return tag;
  }
  *///?}

  @Override
  public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T blockEntity) {
    if (blockEntity instanceof AltarOfTheAccursedBlockEntity entity) {
      entity.ticksPassed++;
      double x = blockPos.getX() + 0.5d;
      double y = blockPos.getY() + 0.5d;
      double z = blockPos.getZ() + 0.5d;

      if (level.isClientSide()) {
        if (!entity.unlocked) return;

        float angle = entity.ticksPassed / 20f;
        double dx = Mth.cos(angle);
        double dz = Mth.sin(angle);

        //? >= 1.21.9 {
        /*
        var purpleParticle = new DustColorTransitionOptions(12255487, 1769509, 1.4f);
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

      } else if (level instanceof ServerLevel server) {
        EndDragonFight dragonFight = server.getDragonFight();
        boolean unlocked = dragonFight != null && dragonFight.hasPreviouslyKilledDragon();

        if (entity.unlocked != unlocked) {
          entity.unlocked = unlocked;
          level.sendBlockUpdated(blockPos, blockState, blockState, 2);
          entity.setChanged();
        }

        if (!entity.unlocked) return;
        if (entity.ticksPassed % 10 == 0) entity.handleItems(server, x, y, z);

      }
    }
  }

  private void handleItems( ServerLevel server, double x, double y, double z) {
    List<ItemEntity> itemEntities = server.getEntitiesOfClass(ItemEntity.class, new AABB(
      x - 0.5, y + 0.75d - 0.5, z - 0.5,
      x + 0.5, y + 0.75d + 0.5, z + 0.5
    ), entity -> ((ExtItemEntity) entity).stellarity$getItemMode() != ExtItemEntity.ItemMode.RESULT);

    List<ItemStack> itemStacks = itemEntities.stream().map(ItemEntity::getItem).toList();

    if (itemEntities.size() < 2) return;
    Player player = server.getNearestPlayer(x, y, z, 5, false);
    ExtItemEntity.ItemMode itemMode = player != null && player.isCrouching() ? ExtItemEntity.ItemMode.PICKUP : ExtItemEntity.ItemMode.CRAFTING;


    HashMap<ItemStack, Integer> results = null;

    if (itemMode == ExtItemEntity.ItemMode.CRAFTING)
      for (var recipe : server.getRecipeManager().getAllRecipesFor(StellarityRecipeTypes.ALTAR_RECIPE)) {
        results = recipe.recipeRemainder(itemStacks);
        if (results != null) break;
      }



    for (var entity : itemEntities) {
      ExtItemEntity itemEntity = (ExtItemEntity) entity;
      itemEntity.stellarity$setItemMode(itemMode);

      if (results == null) return;

      itemEntity.stellarity$updateResults(results);

    }


  }


}
