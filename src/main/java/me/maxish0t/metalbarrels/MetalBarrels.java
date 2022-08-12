package me.maxish0t.metalbarrels;

import me.maxish0t.metalbarrels.client.render.CrystalBarrelRender;
import me.maxish0t.metalbarrels.common.init.ModBlockEntities;
import me.maxish0t.metalbarrels.common.init.ModMenuTypes;
import me.maxish0t.metalbarrels.common.init.ModRegistries;
import me.maxish0t.metalbarrels.common.item.upgrade.BarrelUpgradeItem;
import me.maxish0t.metalbarrels.client.screens.MetalBarrelScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
  }

  @OnlyIn(Dist.CLIENT)
  private void doClientStuff(final FMLClientSetupEvent event) {
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
