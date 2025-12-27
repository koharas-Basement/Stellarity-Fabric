package xyz.kohara.stellarity.networking;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import xyz.kohara.stellarity.Stellarity;

import java.util.ArrayList;

//? 1.20.1 {
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.FriendlyByteBuf;

	//? } else {
/*import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
*///? }


import java.util.List;

public record S2CSetStellarityEntityDataPacket(int id, List<SynchedEntityData.DataValue<?>> list)
//? > 1.21 {
	/*implements CustomPacketPayload
	*///? }
{
	public static final ResourceLocation ID = Stellarity.id("set_entity_data");

	//? 1.20.1 {
	public FriendlyByteBuf pack() {
		var buf = PacketByteBufs.create();
		buf.writeVarInt(id);
		for (SynchedEntityData.DataValue<?> dataValue : list) {
			dataValue.write(buf);
		}

		buf.writeByte(255);

		return buf;
	}

	public static S2CSetStellarityEntityDataPacket unpack(FriendlyByteBuf friendlyByteBuf) {
		int id = friendlyByteBuf.readVarInt();
		List<SynchedEntityData.DataValue<?>> list = new ArrayList<>();

		int i;
		while ((i = friendlyByteBuf.readUnsignedByte()) != 255) {
			list.add(SynchedEntityData.DataValue.read(friendlyByteBuf, i));
		}

		return new S2CSetStellarityEntityDataPacket(id, list);
	}
	//? } else {
	/*public static final Type<S2CSetStellarityEntityDataPacket> TYPE = new Type<>(ID);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}

	public static final StreamCodec<RegistryFriendlyByteBuf, S2CSetStellarityEntityDataPacket> STREAM_CODEC = new StreamCodec<>() {
		@Override
		public void encode(RegistryFriendlyByteBuf buf, S2CSetStellarityEntityDataPacket packet) {
			buf.writeVarInt(packet.id);
			for (SynchedEntityData.DataValue<?> dataValue : packet.list) {
				dataValue.write(buf);
			}

			buf.writeByte(255);
		}

		@Override
		public S2CSetStellarityEntityDataPacket decode(RegistryFriendlyByteBuf buf) {
			int id = buf.readVarInt();
			List<SynchedEntityData.DataValue<?>> list = new ArrayList<>();

			int i;
			while ((i = buf.readUnsignedByte()) != 255) {
				list.add(SynchedEntityData.DataValue.read(buf, i));
			}

			return new S2CSetStellarityEntityDataPacket(id, list);
		}
	};

	*///? }
}
