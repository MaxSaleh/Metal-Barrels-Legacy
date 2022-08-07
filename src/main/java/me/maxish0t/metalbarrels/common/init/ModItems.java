package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.MetalBarrels;
import me.maxish0t.metalbarrels.common.item.BarrelUpgradeItem;
import me.maxish0t.metalbarrels.common.item.UpgradeInfo;
import me.maxish0t.metalbarrels.util.ModTags;
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

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_IRON = REGISTER.register(
            "wood_to_iron", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.IRON_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.IRON_BARREL.get(), MetalBarrels.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_SILVER = REGISTER.register(
            "wood_to_silver", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.SILVER_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.SILVER_BARREL.get(), MetalBarrels.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_GOLD = REGISTER.register(
            "wood_to_gold", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.GOLD_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.GOLD_BARREL.get(), MetalBarrels.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_DIAMOND = REGISTER.register(
            "wood_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.DIAMOND_BARREL.get(), MetalBarrels.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_OBSIDIAN = REGISTER.register(
            "wood_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.OBSIDIAN_BARREL.get(), MetalBarrels.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_NETHERITE = REGISTER.register(
            "wood_to_netherite", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.NETHERITE_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.NETHERITE_BARREL.get(), MetalBarrels.MODID))
    );

    /**
     * Copper to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_IRON = REGISTER.register(
            "copper_to_iron", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.IRON_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.IRON_BARREL.get(), MetalBarrels.MODID))
    );
}
