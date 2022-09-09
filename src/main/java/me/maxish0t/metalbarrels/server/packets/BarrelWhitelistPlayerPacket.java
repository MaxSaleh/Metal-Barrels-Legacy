package me.maxish0t.metalbarrels.server.packets;

import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BarrelWhitelistPlayerPacket {

    private final BlockPos barrelBlockPos;
    private final String username;

    public BarrelWhitelistPlayerPacket(final BlockPos barrelBlockPos, final String username) {
        this.barrelBlockPos = barrelBlockPos;
        this.username = username;
    }

    public static void encode(final BarrelWhitelistPlayerPacket barrelWhitelistPlayerPacket, final FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBlockPos(barrelWhitelistPlayerPacket.barrelBlockPos);
        friendlyByteBuf.writeUtf(barrelWhitelistPlayerPacket.username);
    }

    public static BarrelWhitelistPlayerPacket decode(final FriendlyByteBuf friendlyByteBuf) {
        return new BarrelWhitelistPlayerPacket(friendlyByteBuf.readBlockPos(), friendlyByteBuf.readUtf());
    }

    public static void handle(final BarrelWhitelistPlayerPacket barrelWhitelistPlayerPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final NetworkEvent.Context context = contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = contextSupplier.get().getSender();
            if (serverPlayer != null) {
                ServerLevel serverLevel = serverPlayer.getLevel();
                MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) serverLevel.getBlockEntity(barrelWhitelistPlayerPacket.barrelBlockPos);
                if (metalBarrelBlockEntity != null) {
                    metalBarrelBlockEntity.addWhitelistedPlayer(barrelWhitelistPlayerPacket.username);
                    serverLevel.sendBlockUpdated(barrelWhitelistPlayerPacket.barrelBlockPos, metalBarrelBlockEntity.getBlockState(),
                            metalBarrelBlockEntity.getBlockState(), Block.UPDATE_ALL);
                }
            }
        });

        context.setPacketHandled(true);
    }
    
}
