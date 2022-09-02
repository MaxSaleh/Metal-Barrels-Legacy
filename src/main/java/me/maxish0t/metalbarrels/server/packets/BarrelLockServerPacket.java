package me.maxish0t.metalbarrels.server.packets;

import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.server.BarrelNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BarrelLockServerPacket {

    private final BlockPos barrelBlockPos;
    private final boolean isLocked;

    public BarrelLockServerPacket(final BlockPos barrelBlockPos, final boolean isLocked) {
        this.barrelBlockPos = barrelBlockPos;
        this.isLocked = isLocked;
    }

    public static void encode(final BarrelLockServerPacket barrelLockServerPacket, final FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(barrelLockServerPacket.barrelBlockPos);
        friendlyByteBuf.writeBoolean(barrelLockServerPacket.isLocked);
    }

    public static BarrelLockServerPacket decode(final FriendlyByteBuf friendlyByteBuf) {
        return new BarrelLockServerPacket(friendlyByteBuf.readBlockPos(), friendlyByteBuf.readBoolean());
    }

    public static void handle(final BarrelLockServerPacket barrelLockServerPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = contextSupplier.get().getSender();
            if (serverPlayer != null) {
                ServerLevel serverLevel = serverPlayer.getLevel();
                MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) serverLevel.getBlockEntity(barrelLockServerPacket.barrelBlockPos);
                if (metalBarrelBlockEntity != null) {
                    metalBarrelBlockEntity.setLocked(barrelLockServerPacket.isLocked);
                    serverLevel.sendBlockUpdated(barrelLockServerPacket.barrelBlockPos, metalBarrelBlockEntity.getBlockState(),
                            metalBarrelBlockEntity.getBlockState(), Block.UPDATE_ALL);
                    BarrelNetwork.CHANNEL.sendTo(new BarrelLockClientPacket(barrelLockServerPacket.barrelBlockPos, barrelLockServerPacket.isLocked, true),
                            serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
                }
            }
        });

        context.setPacketHandled(true);
    }
    
}
