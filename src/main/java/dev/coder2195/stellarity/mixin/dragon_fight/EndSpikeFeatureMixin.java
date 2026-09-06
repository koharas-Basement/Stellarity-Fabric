package dev.coder2195.stellarity.mixin.dragon_fight;


import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.coder2195.stellarity.Stellarity;
import dev.coder2195.stellarity.registry.StellarityBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomSequence;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndSpikeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndSpikeFeature.class)
@Debug(export = true)
public abstract class EndSpikeFeatureMixin implements Feature {
	@Unique
	private RandomSource random = RandomSource.create();

	@Inject(method = "placeSpike", at = @At("HEAD"))
	private void stellaritySpikeInit(ServerLevelAccessor level, RandomSource random, EndSpikeFeature.EndSpike spike, CallbackInfo ci) {
		this.random = new RandomSequence((long) (spike.getCenterX()) << 32 & spike.getCenterZ(), Stellarity.id("obsidian_splatter")).random();
	}

	@Definition(id = "OBSIDIAN", field = "Lnet/minecraft/world/level/block/Blocks;OBSIDIAN:Lnet/minecraft/world/level/block/Block;")
	@Expression("OBSIDIAN.?()")
	@WrapOperation(method = "placeSpike", at = @At("MIXINEXTRAS:EXPRESSION"))
	private BlockState cryingObsidianTops(Block instance, Operation<BlockState> original, @Local(name = "pos") BlockPos pos, @Local(argsOnly = true, name = "spike") EndSpikeFeature.EndSpike spike) {
		if (spike.stellarity$hasCryingObsidianTops()) {
			int distance = spike.getHeight() - pos.getY();

			if (distance <= 15f && random.nextFloat() < -0.05f * distance + 0.8f)
				return Blocks.CRYING_OBSIDIAN.defaultBlockState();
		}

		return original.call(instance);

	}

	@Definition(id = "setBlock", method = "Lnet/minecraft/world/level/levelgen/feature/EndSpikeFeature;setBlock(Lnet/minecraft/world/level/LevelWriter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V")
	@Definition(id = "pos", local = @Local(type = BlockPos.class, name = "pos"))
	@Expression("?.setBlock(?, pos, ?)")
	@WrapOperation(method = "placeSpike", at = @At("MIXINEXTRAS:EXPRESSION"))
	private void hollowForAltar(EndSpikeFeature instance, LevelWriter levelWriter, BlockPos blockPos, BlockState blockState, Operation<Void> original, @Local(argsOnly = true, name = "spike") EndSpikeFeature.EndSpike spike) {
		int y = blockPos.getY();
		if (spike.stellarity$hasAltar() && y < spike.getHeight() - 4 && y > spike.getHeight() - 30) return;
		original.call(instance, levelWriter, blockPos, blockState);
	}


	@WrapMethod(method = "placeSpike")
	private void placeAltar(ServerLevelAccessor level, RandomSource random, EndSpikeFeature.EndSpike spike, Operation<Void> original) {
		try {
			var altarPos = new BlockPos(spike.getCenterX(), spike.getHeight() - 20, spike.getCenterZ());
			var altar = level.getBlockState(altarPos);

			original.call(level, random, spike);
			if (!spike.stellarity$hasAltar() || altar.is(StellarityBlocks.ALTAR_OF_THE_ACCURSED)) return;

			var placePos = altarPos.offset(-8, -9, -9);

			var server = level.getServer();

			if (server != null) {
				level.getServer().getStructureTemplateManager().getOrCreate(Stellarity.id("altar_of_the_accursed")).placeInWorld(level, placePos, placePos, new StructurePlaceSettings(), random, Block.UPDATE_CLIENTS);
			} else {
				Stellarity.LOGGER.info("failed to create the altar");
			}

		} catch (Exception e) {

			Stellarity.LOGGER.error("Failed to place spike correctly", e);
		}
	}

	@Definition(id = "endCrystal", local = @Local(type = EndCrystal.class, name = "endCrystal"))
	@Expression("endCrystal != null")
	@ModifyExpressionValue(method = "placeSpike", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean conditionalCrystalGen(boolean original, @Local(argsOnly = true, name = "spike") EndSpikeFeature.EndSpike spike) {
		return original && spike.stellarity$hasCrystal();
	}


}
