package me.maxish0t.metalbarrels.client.handlers;

import me.maxish0t.metalbarrels.client.render.CrystalBarrelRender;
import me.maxish0t.metalbarrels.client.screens.MetalBarrelScreen;
import me.maxish0t.metalbarrels.client.screens.overlay.PlayerOverlay;
import me.maxish0t.metalbarrels.common.init.ModBlockEntities;
import me.maxish0t.metalbarrels.common.init.ModBlocks;
import me.maxish0t.metalbarrels.common.init.ModMenuTypes;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ModReference.MODID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientHandlers {

    public static final IIngameOverlay MB_OVERLAY = OverlayRegistry.registerOverlayTop("mb_overlay", new PlayerOverlay());

    @SubscribeEvent
    public static void registerRenders(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CRYSTAL_BARREL.get(), RenderType.cutout());
    }

    public static void clientRegistries(final FMLClientSetupEvent event) {
        MenuScreens.register(ModMenuTypes.COPPER_CONTAINER.get(), MetalBarrelScreen::copper);
        MenuScreens.register(ModMenuTypes.IRON_CONTAINER.get(), MetalBarrelScreen::iron);
        MenuScreens.register(ModMenuTypes.SILVER_CONTAINER.get(), MetalBarrelScreen::silver);
        MenuScreens.register(ModMenuTypes.GOLD_CONTAINER.get(), MetalBarrelScreen::gold);
        MenuScreens.register(ModMenuTypes.DIAMOND_CONTAINER.get(), MetalBarrelScreen::diamond);
        MenuScreens.register(ModMenuTypes.OBSIDIAN_CONTAINER.get(), MetalBarrelScreen::obsidian);
        MenuScreens.register(ModMenuTypes.NETHERITE_CONTAINER.get(), MetalBarrelScreen::netherite);
        MenuScreens.register(ModMenuTypes.CRYSTAL_CONTAINER.get(), MetalBarrelScreen::crystal);
        BlockEntityRenderers.register(ModBlockEntities.CRYSTAL_BARREL.get(), CrystalBarrelRender::new);
    }

}
