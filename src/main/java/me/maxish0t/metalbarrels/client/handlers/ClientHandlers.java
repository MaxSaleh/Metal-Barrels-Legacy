package me.maxish0t.metalbarrels.client.handlers;

import me.maxish0t.metalbarrels.MetalBarrels;
import me.maxish0t.metalbarrels.client.screens.overlay.PlayerOverlay;
import me.maxish0t.metalbarrels.common.init.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MetalBarrels.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientHandlers {

    @SubscribeEvent
    public static void registerRenders(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BARREL.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("mb_overlay", new PlayerOverlay());
    }

}
