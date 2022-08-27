package me.maxish0t.metalbarrels.server.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class CrystalBarrelItemsPacket {

    private Set<ItemStack> itemStacks = new HashSet<>();
    public static ItemStack itemStack;

    public CrystalBarrelItemsPacket(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }


    public static void encode(final CrystalBarrelItemsPacket msg, final FriendlyByteBuf packetBuffer) {
        packetBuffer.writeItem(msg.itemStack);
    }

    public static CrystalBarrelItemsPacket decode(final FriendlyByteBuf packetBuffer) {
        return new CrystalBarrelItemsPacket(packetBuffer.readItem());
    }

    /**
     * Called on the Client.
     */
    public static void handle(final CrystalBarrelItemsPacket msg, final Supplier<NetworkEvent.Context> contextSupplier) {

    }
}
