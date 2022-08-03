package com.tfar.metalbarrels.common.init;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.common.container.MetalBarrelContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, MetalBarrels.MODID);

    public static final RegistryObject<MenuType<MetalBarrelContainer>> COPPER_CONTAINER = REGISTER.register(
            "copper_container", () -> new MenuType<>(MetalBarrelContainer::copper)
    );

}
