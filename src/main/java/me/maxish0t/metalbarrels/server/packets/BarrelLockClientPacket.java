package me.maxish0t.metalbarrels.server.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BarrelLockClientPacket {

    public static BlockPos barrelBlockPos;
    public static boolean isLocked;
    public static boolean isOwner;

    public BarrelLockClientPacket(BlockPos barrelBlockPos, boolean isLocked, boolean isOwner) {
        BarrelLockClientPacket.barrelBlockPos = barrelBlockPos;
        BarrelLockClientPacket.isLocked = isLocked;
        BarrelLockClientPacket.isOwner = isOwner;
    }

    public static void encode(final BarrelLockClientPacket barrelLockClientPacket, final FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(barrelBlockPos);
        friendlyByteBuf.writeBoolean(isLocked);
        friendlyByteBuf.writeBoolean(isOwner);
    }

    public static BarrelLockClientPacket decode(final FriendlyByteBuf friendlyByteBuf) {
        return new BarrelLockClientPacket(friendlyByteBuf.readBlockPos(), friendlyByteBuf.readBoolean(), friendlyByteBuf.readBoolean());
    }

    public static void handle(final BarrelLockClientPacket barrelLockClientPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {

        });

        context.setPacketHandled(true);
    }

}
