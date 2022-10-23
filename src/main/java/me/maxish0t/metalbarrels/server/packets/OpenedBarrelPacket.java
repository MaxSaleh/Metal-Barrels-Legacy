package me.maxish0t.metalbarrels.server.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenedBarrelPacket {

    public static BlockPos barrelBlockPos;

    public OpenedBarrelPacket(final BlockPos barrelBlockPos) {
        OpenedBarrelPacket.barrelBlockPos = barrelBlockPos;
    }

    public static void encode(final OpenedBarrelPacket openedBarrelPacket, final FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(OpenedBarrelPacket.barrelBlockPos);
    }

    public static OpenedBarrelPacket decode(final FriendlyByteBuf friendlyByteBuf) {
        return new OpenedBarrelPacket(friendlyByteBuf.readBlockPos());
    }

    public static void handle(final OpenedBarrelPacket openedBarrelPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {

        });

        context.setPacketHandled(true);
    }
    
}
