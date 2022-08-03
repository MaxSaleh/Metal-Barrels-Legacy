package com.tfar.metalbarrels;

import com.tfar.metalbarrels.common.init.ModMenuTypes;
import com.tfar.metalbarrels.common.init.ModRegistries;
import com.tfar.metalbarrels.common.item.BarrelUpgradeItem;
import com.tfar.metalbarrels.server.network.PacketHandler;
import com.tfar.metalbarrels.client.screens.MetalBarrelScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MetalBarrels.MODID)
public class MetalBarrels {

  public static final String MODID = "metalbarrels";

  public static final Logger logger = LogManager.getLogger();

  public MetalBarrels() {
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

    final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ModRegistries.registries(iEventBus);
  }

  private void commonSetup(final FMLCommonSetupEvent event){
    BarrelUpgradeItem.IRON_CHESTS_LOADED = ModList.get().isLoaded("ironchest");
    PacketHandler.register();
  }

  private void doClientStuff(final FMLClientSetupEvent event) {
    MenuScreens.register(ModMenuTypes.COPPER_CONTAINER.get(), MetalBarrelScreen::copper);
    MenuScreens.register(ModMenuTypes.IRON_CONTAINER.get(), MetalBarrelScreen::iron);
    MenuScreens.register(ModMenuTypes.SILVER_CONTAINER.get(), MetalBarrelScreen::silver);
    MenuScreens.register(ModMenuTypes.GOLD_CONTAINER.get(), MetalBarrelScreen::gold);
    MenuScreens.register(ModMenuTypes.DIAMOND_CONTAINER.get(), MetalBarrelScreen::diamond);
    MenuScreens.register(ModMenuTypes.OBSIDIAN_CONTAINER.get(), MetalBarrelScreen::obsidian);
    MenuScreens.register(ModMenuTypes.NETHERITE_CONTAINER.get(), MetalBarrelScreen::netherite);
    MenuScreens.register(ModMenuTypes.CRYSTAL_CONTAINER.get(), MetalBarrelScreen::crystal);
  }

}
