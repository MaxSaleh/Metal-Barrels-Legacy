package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.MetalBarrels;
import me.maxish0t.metalbarrels.common.container.MetalBarrelContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, MetalBarrels.MODID);

    /**
     * Metal Barrels Menu Registry.
     */

    public static final RegistryObject<MenuType<MetalBarrelContainer>> COPPER_CONTAINER = REGISTER.register(
            "copper_container", () -> new MenuType<>(MetalBarrelContainer::copper)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> IRON_CONTAINER = REGISTER.register(
            "iron_container", () -> new MenuType<>(MetalBarrelContainer::iron)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> SILVER_CONTAINER = REGISTER.register(
            "silver_container", () -> new MenuType<>(MetalBarrelContainer::silver)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> GOLD_CONTAINER = REGISTER.register(
            "gold_container", () -> new MenuType<>(MetalBarrelContainer::gold)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> DIAMOND_CONTAINER = REGISTER.register(
            "diamond_container", () -> new MenuType<>(MetalBarrelContainer::diamond)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> OBSIDIAN_CONTAINER = REGISTER.register(
            "obsidian_container", () -> new MenuType<>(MetalBarrelContainer::obsidian)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> NETHERITE_CONTAINER = REGISTER.register(
            "netherite_container", () -> new MenuType<>(MetalBarrelContainer::netherite)
    );

    public static final RegistryObject<MenuType<MetalBarrelContainer>> CRYSTAL_CONTAINER = REGISTER.register(
            "crystal_container", () -> new MenuType<>(MetalBarrelContainer::crystal)
    );

}
