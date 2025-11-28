package xyz.kohara.stellarity.mixin.void_fishing;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.core.particles.ParticleOptions;
import xyz.kohara.stellarity.StellarityItems;
import xyz.kohara.stellarity.StellarityCriteriaTriggers;
import xyz.kohara.stellarity.interface_injection.ExtFishingHook;

//? >= 1.21.10 {
/*import net.minecraft.core.particles.PowerParticleOption;
 *///?} else {


//?}

@Mixin(FishingHook.class)
public abstract class FishingHookMixin extends Projectile implements ExtFishingHook {
  @Unique
  private static final ParticleOptions DRAGON_BREATH =
    //? >= 1.21.9 {
    /*PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1f);
     *///?} else {
    ParticleTypes.DRAGON_BREATH;
  //?}

  @Unique
  private boolean buffVoidFishing = false;

  @Shadow
  private FishingHook.FishHookState currentState;

  @Unique
  private boolean warned = false;

  @Unique
  private boolean isVoidFishing = false;

  @Unique
  private boolean splashed = false;

  @Shadow
  @Nullable
  public abstract Player getPlayerOwner();

  @Shadow
  @Final
  private int lureSpeed;

  @Shadow
  private int timeUntilLured;

  public FishingHookMixin(EntityType<? extends Projectile> entityType, Level level) {
    super(entityType, level);
  }

  @Unique
  private boolean isEnd() {
    //? <= 1.20.1 {
    return this.level().dimensionTypeId() == BuiltinDimensionTypes.END;
     //?} else {
    /*return this.level().dimensionTypeRegistration().is(BuiltinDimensionTypes.END);
    *///?}
  }

  @Unique
  private boolean isEndMidAir() {
    BlockPos pos = this.blockPosition();

    return isEnd() && this.level().getBlockState(pos).is(Blocks.AIR) && this.level().getBlockState(pos.below()).is(Blocks.AIR);
  }

  @Unique
  private boolean evalVoidFishing() {
    return isEndMidAir() && currentState == FishingHook.FishHookState.BOBBING;
  }

  @Unique
  private boolean canBob() {
    double y = getY();

    if (y < 0 && !warned) {
      warned = true;
      if (getPlayerOwner() instanceof ServerPlayer player) {
        player.connection.send(new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.void_fishing_too_deep").withStyle(ChatFormatting.DARK_PURPLE)));
      }
    }

    if (y > 0 && warned) {
      warned = false;
    }

    return !warned && isEndMidAir() && (this.currentState == FishingHook.FishHookState.BOBBING || this.distanceTo(this.getPlayerOwner()) > 20.0);
  }

  @ModifyVariable(method = "tick()V", at = @At(value = "STORE"), ordinal = 0)
  private boolean checkCanBob(boolean original) {
    return canBob() || original;
  }

  @Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V"))
  private void voidFishingHover(FishingHook instance, MoverType moverType, Vec3 vec3) {
    this.isVoidFishing = evalVoidFishing();
    if (isVoidFishing) {
      this.setDeltaMovement(0.0, 0.0, 0.0);
    } else {
      instance.move(moverType, vec3);
    }
  }

  @Inject(method = "tick", at = @At("TAIL"))
  private void visuals(CallbackInfo ci) {
    if (level() instanceof ClientLevel level) {
      if (!isVoidFishing) return;
      double x = getX();
      double y = getY();
      double z = getZ();

      if (!splashed) {
        splashed = true;

        for (float i = 0; i < 2 * Mth.PI; i += Mth.PI / 30) {
          float vx = Mth.cos(i) * 0.04f;
          float vz = Mth.sin(i) * 0.04f;

          level.addParticle(DRAGON_BREATH, x, y, z, vx, 0, vz);

        }
      }

      ParticleOptions particleType = random.nextBoolean() ? DRAGON_BREATH : ParticleTypes.END_ROD;


      double dx = random.nextDouble() * 4 - 2;
      double dy = random.nextDouble() * 2 - 1;
      double dz = random.nextDouble() * 4 - 2;

      level.addParticle(
        particleType,
        x + dx,
        y + dy,
        z + dz,
        dx * 0.01,
        dy * 0.01,
        dz * 0.01
      );
    }
  }



  @Redirect(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
  private boolean addVoidVishingToWaterCheck(BlockState instance, Block block) {
    return isVoidFishing || instance.is(block);
  }

  @Redirect(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;sendParticles(Lnet/minecraft/core/particles/ParticleOptions;DDDIDDDD)I"))
  private int splashParticles(ServerLevel instance, ParticleOptions particleOptions, double d, double e, double f, int i, double g, double h, double j, double k) {
    if (isVoidFishing) {
      if (particleOptions == ParticleTypes.SPLASH || particleOptions == ParticleTypes.FISHING)
        particleOptions = ParticleTypes.WITCH;
      if (particleOptions == ParticleTypes.BUBBLE) particleOptions = DRAGON_BREATH;
    }

    return instance.sendParticles(particleOptions, d, e, f, evalVoidFishing() ? i * 2 : i, g, h, j, k);
  }


  @Redirect(method = "catchingFish", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/FishingHook;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"))
  public void louderSplash(FishingHook instance, SoundEvent soundEvent, float v, float p) {
    instance.playSound(soundEvent, evalVoidFishing() ? 1.5f : v, p);
  }

  @Redirect(method="shouldStopFishing", at = @At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
  public boolean dontStopFisherOfVoids(ItemStack instance, Item item) {
    return instance.is(StellarityItems.FISHER_OF_VOIDS) || instance.is(item);
  }

  @Redirect(method = "catchingFish", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/projectile/FishingHook;lureSpeed:I", opcode = Opcodes.GETFIELD))
  private int increaseLure(FishingHook instance) {
    isVoidFishing = evalVoidFishing();
    if (!this.buffVoidFishing || !isVoidFishing) {
      return this.lureSpeed;
    }

    //? < 1.21 {
    return this.lureSpeed + 2;
    //? } else {
    /*return this.lureSpeed + 200;
    *///? }
  }

  @Unique
  public void stellarity$buffVoidFishing(boolean buffVoidFishing) {
    this.buffVoidFishing = buffVoidFishing;
  }

  @Redirect(method = "retrieve", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"))
  private ObjectArrayList<ItemStack> voidFishingRetrieve(LootTable instance, LootParams lootParams, @Local Player player, @Local(argsOnly = true) ItemStack itemStack) {
    ObjectArrayList<ItemStack> list = instance.getRandomItems(lootParams);
    if (isVoidFishing) {
      StellarityCriteriaTriggers.VOID_FISHED.trigger((ServerPlayer) player, itemStack, (FishingHook) (Object) this, list);
    }
    return list;
  }
}