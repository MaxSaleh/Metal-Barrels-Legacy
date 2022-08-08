package me.maxish0t.metalbarrels.client.handlers;

import me.maxish0t.metalbarrels.MetalBarrels;
import me.maxish0t.metalbarrels.common.init.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MetalBarrels.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientHandlers {
    @SubscribeEvent
    public static void doClientStuff(final FMLClientSetupEvent event) {
        //ClientRegistry.bindTileEntityRenderer(MetalBarrels.ObjectHolders.CRYSTAL_TILE, CrystalBarrelTileSpecialRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BARREL.get(), RenderType.cutout());
    }
}
