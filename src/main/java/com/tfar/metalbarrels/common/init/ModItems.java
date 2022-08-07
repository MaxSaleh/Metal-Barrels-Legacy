package com.tfar.metalbarrels.common.init;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.common.item.BarrelUpgradeItem;
import com.tfar.metalbarrels.common.item.UpgradeInfo;
import com.tfar.metalbarrels.util.ModTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MetalBarrels.MODID);
    private static final Item.Properties properties = new Item.Properties().tab(ModTabs.tab);

    /**
     * Mod Block Items.
     */

    public static final RegistryObject<BlockItem> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> new BlockItem(ModBlocks.COPPER_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> IRON_BARREL = REGISTER.register(
            "iron_barrel", () -> new BlockItem(ModBlocks.IRON_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> SILVER_BARREL = REGISTER.register(
            "silver_barrel", () -> new BlockItem(ModBlocks.SILVER_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> GOLD_BARREL = REGISTER.register(
            "gold_barrel", () -> new BlockItem(ModBlocks.GOLD_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> DIAMOND_BARREL = REGISTER.register(
            "diamond_barrel", () -> new BlockItem(ModBlocks.DIAMOND_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> OBSIDIAN_BARREL = REGISTER.register(
            "obsidian_barrel", () -> new BlockItem(ModBlocks.OBSIDIAN_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> NETHERITE_BARREL = REGISTER.register(
            "netherite_barrel", () -> new BlockItem(ModBlocks.NETHERITE_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    public static final RegistryObject<BlockItem> CRYSTAL_BARREL = REGISTER.register(
            "crystal_barrel", () -> new BlockItem(ModBlocks.CRYSTAL_BARREL.get(), new Item.Properties().tab(ModTabs.tab))
    );

    /**
     * Mod Items.
     */

    public static void registerItems() {
        for (Block block : ModBlocks.MOD_BLOCKS) {
            Item.Properties properties = new Item.Properties().tab(ModTabs.tab);

            if (block == ModBlocks.NETHERITE_BARREL.get()) {
                properties.fireResistant(); // TODO -> isBurnable
            }

            Item item = new BlockItem(block, properties);
            REGISTER.register(block.getName().toString(), () -> item);
        }
    }

    /**
     * Wood to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_COPPER = REGISTER.register(
            "wood_to_copper", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.COPPER_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.COPPER_BARREL.get(), MetalBarrels.MODID))
    );
}
