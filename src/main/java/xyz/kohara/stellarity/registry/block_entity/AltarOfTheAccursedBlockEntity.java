package xyz.kohara.stellarity.registry.block_entity;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import xyz.kohara.stellarity.registry.StellarityBlockEntityTypes;
import xyz.kohara.stellarity.registry.StellarityRecipeTypes;
import xyz.kohara.stellarity.registry.block.AltarOfTheAccursed;
import xyz.kohara.stellarity.interface_injection.ExtItemEntity;
import xyz.kohara.stellarity.registry.recipe.AltarRecipe;

import java.util.List;
//? > 1.21.9 {
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
*///?}

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
        AltarOfTheAccursed.State state = blockState.getValue(AltarOfTheAccursed.STATE);
        if (blockEntity instanceof AltarOfTheAccursedBlockEntity entity) {
            entity.ticksPassed++;
            double x = blockPos.getX() + 0.5d;
            double y = blockPos.getY() + 0.5d;
            double z = blockPos.getZ() + 0.5d;

            if (level.isClientSide()) {
                if (state.isLocked()) return;

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

            if (!state.isCreative()) {
                var end = serverLevel.getServer().getLevel(Level.END);
                EndDragonFight dragonFight = end == null ? null : end.getDragonFight();


                AltarOfTheAccursed.State newState = dragonFight != null && dragonFight.hasPreviouslyKilledDragon() && dragonFight.stellarity$dragonKilled() ?
                AltarOfTheAccursed.State.UNLOCKED : AltarOfTheAccursed.State.LOCKED;
                if (state != newState) {
                    serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(AltarOfTheAccursed.STATE, newState));
                }
            }

            if (entity.ticksPassed % 10 == 0) entity.handleItems(serverLevel, x, y, z, blockState);

            }
        }
    }

    private void handleItems(ServerLevel serverLevel, double x, double y, double z, BlockState blockState) {
        AltarOfTheAccursed.State state = blockState.getValue(AltarOfTheAccursed.STATE);

        List<ItemEntity> itemEntities = serverLevel.getEntitiesOfClass(ItemEntity.class, new AABB(
            x - 0.5, y + 0.75d - 0.5, z - 0.5,
            x + 0.5, y + 0.75d + 0.5, z + 0.5
        ), entity -> entity.stellarity$getItemMode() != ExtItemEntity.ItemMode.RESULT);

        Player player = serverLevel.getNearestPlayer(x, y, z, 10, false);

        if (state.isLocked()) {
            if (!itemEntities.isEmpty() && player instanceof ServerPlayer serverPlayer) {
                serverPlayer.connection.send(
                    new ClientboundSetActionBarTextPacket(Component.translatable("message.stellarity.altar_of_the_accursed_locked").withStyle(ChatFormatting.DARK_PURPLE))
                );
            }


            return;
        }


        List<ItemStack> itemStacks = itemEntities.stream().map(ItemEntity::getItem).toList();
        ExtItemEntity.ItemMode itemMode = player != null && player.isCrouching() ? ExtItemEntity.ItemMode.PICKUP : ExtItemEntity.ItemMode.CRAFTING;

        for (var entity : itemEntities) {
            entity.stellarity$setItemMode(itemMode);
        }

        if (itemEntities.size() < 2) return;


        AltarRecipe.Output output = null;
        AltarRecipe hitRecipe = null;


        if (itemMode == ExtItemEntity.ItemMode.CRAFTING) {
            //? = 1.21.1 {
            /*var allRecipes = serverLevel.getRecipeManager().getAllRecipesFor(StellarityRecipeTypes.ALTAR_RECIPE);
             *///? } > 1.21.9 {
            /*var allRecipes = serverLevel.getServer().getRecipeManager().getAllOfType(StellarityRecipeTypes.ALTAR_RECIPE);
             *///? }
            //? = 1.20.1 {
            for (var recipe : serverLevel.getRecipeManager().getAllRecipesFor(StellarityRecipeTypes.ALTAR_RECIPE)) {
                //? } else {
                /*for (var recipeHolder : allRecipes) {
                var recipe = recipeHolder.value();
                *///? }

                output = recipe.craft(itemStacks);
                if (output != null) {
                    hitRecipe = recipe;
                    break;
                }
            }
        }

        if (output == null) return;

        for (
            var entity : itemEntities) {
            entity.stellarity$updateResults(output.remainders());
        }

        ItemEntity resultItem = new ItemEntity(serverLevel, x, y + 0.75, z, output.result());
        resultItem.stellarity$setItemMode(ExtItemEntity.ItemMode.RESULT);
        serverLevel.addFreshEntity(resultItem);
    }


}
