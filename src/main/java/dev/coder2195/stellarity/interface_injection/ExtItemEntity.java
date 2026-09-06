package dev.coder2195.stellarity.interface_injection;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.world.item.ItemStack;
import dev.coder2195.stellarity.registry.StellarityDataAttachments;
import dev.coder2195.stellarity.util.CustomCodecs;
import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.function.IntFunction;

@SuppressWarnings("NonExtendableApiUsage")
public interface ExtItemEntity extends AttachmentTarget {
	enum ItemMode {
		DEFAULT(0),
		ALTAR_CRAFTING(1),
		CONSECRATING(2),
		RESULT(3);

		private final int id;

		ItemMode(int id) {
			this.id = id;
		}

		public int id() {
			return id;
		}

		public static final IntFunction<ItemMode> BY_ID =
			ByIdMap.continuous(
				ItemMode::id,
				ItemMode.values(),
				ByIdMap.OutOfBoundsStrategy.ZERO
			);

		public static final StreamCodec<ByteBuf, ItemMode> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ItemMode::id);
		public static final Codec<ItemMode> CODEC = CustomCodecs.enumName(ItemMode.class, DEFAULT);

		public @Nullable Short getPickupDelay() {
			if (this == ALTAR_CRAFTING) return Short.MAX_VALUE;
			if (this == CONSECRATING) return null;
			return 5;
		}

		public boolean isCrafting() {
			return !(this == RESULT || this == DEFAULT);
		}
	}

	default ItemMode stellarity$getItemMode() {
		return this.getAttachedOrElse(StellarityDataAttachments.ITEM_MODE, ItemMode.DEFAULT);
	}

	default void stellarity$setItemMode(ItemMode mode, @Nullable Integer color) {
		this.setAttached(StellarityDataAttachments.ITEM_MODE, mode);
	}

	default void stellarity$setItemMode(ItemMode mode) {
		stellarity$setItemMode(mode, null);
	}

	default void stellarity$updateResults(HashMap<ItemStack, Integer> results) {
		throw new AssertionError("Not transformed!");
	}
}
