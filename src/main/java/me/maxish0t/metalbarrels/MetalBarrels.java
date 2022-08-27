package me.maxish0t.metalbarrels;

import me.maxish0t.metalbarrels.client.config.ClientConfig;
import me.maxish0t.metalbarrels.client.handlers.ClientHandlers;
import me.maxish0t.metalbarrels.common.init.ModRegistries;
import me.maxish0t.metalbarrels.server.BarrelNetwork;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModReference.MODID)
public class MetalBarrels {

  public MetalBarrels() {
    final IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    iEventBus.addListener(ClientHandlers::clientRegistries);
    ModRegistries.registries(iEventBus);
    BarrelNetwork.register();
    ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC, "MetalBarrels/metalbarrels-client.toml");
    ModReference.LOGGER.info("Metal Barrels mod has been successfully loaded!");
  }

}
