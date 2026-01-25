//? < 1.21.5 {
package xyz.kohara.stellarity.mixin.entity_variants;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.kohara.stellarity.registry.entity.variants.StellarityFrogVariants;

//? 1.21.1 {

/*import net.minecraft.core.registries.BuiltInRegistries;
 *///? } else {

import net.minecraft.nbt.CompoundTag;
//? }

@Mixin(Frog.class)
public abstract class FrogMixin extends Entity {


    public FrogMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }


    //? 1.20.1 {
    @Shadow
    public abstract void setVariant(FrogVariant par1);

    @Inject(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/frog/FrogAi;initMemories(Lnet/minecraft/world/entity/animal/frog/Frog;Lnet/minecraft/util/RandomSource;)V"))
    private void addStellarityFrogs(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        Holder<Biome> holder = serverLevelAccessor.getBiome(this.blockPosition());

        if (holder.is(BiomeTags.IS_END)) {
            setVariant(StellarityFrogVariants.END.get());
        }
    }
    //? } else {
    /*@Shadow
    public abstract void setVariant(Holder<FrogVariant> holder);

    @Inject(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/frog/FrogAi;initMemories(Lnet/minecraft/world/entity/animal/frog/Frog;Lnet/minecraft/util/RandomSource;)V"))
    private void addStellarityFrogs(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Holder<Biome> holder = serverLevelAccessor.getBiome(this.blockPosition());

        if (holder.is(BiomeTags.IS_END)) {
            setVariant(BuiltInRegistries.FROG_VARIANT.wrapAsHolder(StellarityFrogVariants.END.get()));
        }
    }
    *///? }

}

//? }