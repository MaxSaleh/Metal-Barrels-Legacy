package me.maxish0t.metalbarrels.server;

import me.maxish0t.metalbarrels.server.packets.*;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class BarrelNetwork {

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ModReference.MODID, "main"), () -> "1", "1"::equals, "1"::equals
    );

    public static void register() {
        int networkId = 0;

        // Server -> Client
        CHANNEL.registerMessage(networkId++,
                BarrelLockClientPacket.class,
                BarrelLockClientPacket::encode,
                BarrelLockClientPacket::decode,
                BarrelLockClientPacket::handle
        );

        // Client -> Server
        CHANNEL.registerMessage(networkId++,
                BarrelLockServerPacket.class,
                BarrelLockServerPacket::encode,
                BarrelLockServerPacket::decode,
                BarrelLockServerPacket::handle
        );

        // Client -> Server
        CHANNEL.registerMessage(networkId++,
                BarrelWhitelistPlayerPacket.class,
                BarrelWhitelistPlayerPacket::encode,
                BarrelWhitelistPlayerPacket::decode,
                BarrelWhitelistPlayerPacket::handle
        );

        // Client -> Server
        CHANNEL.registerMessage(networkId++,
                BarrelRemovePlayerPacket.class,
                BarrelRemovePlayerPacket::encode,
                BarrelRemovePlayerPacket::decode,
                BarrelRemovePlayerPacket::handle
        );

        // Server -> Client
        CHANNEL.registerMessage(networkId++,
                OpenedBarrelPacket.class,
                OpenedBarrelPacket::encode,
                OpenedBarrelPacket::decode,
                OpenedBarrelPacket::handle
        );
    }

}
