package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.common.item.extra.BarrelMoveItem;
import me.maxish0t.metalbarrels.common.item.upgrade.BarrelUpgradeItem;
import me.maxish0t.metalbarrels.common.item.upgrade.UpgradeInfo;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModReference.MODID);
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
     * Wood to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_COPPER = REGISTER.register(
            "wood_to_copper", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.COPPER_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.COPPER_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_IRON = REGISTER.register(
            "wood_to_iron", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.IRON_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.IRON_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_SILVER = REGISTER.register(
            "wood_to_silver", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.SILVER_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.SILVER_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_GOLD = REGISTER.register(
            "wood_to_gold", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.GOLD_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.GOLD_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_DIAMOND = REGISTER.register(
            "wood_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.DIAMOND_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_OBSIDIAN = REGISTER.register(
            "wood_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_NETHERITE = REGISTER.register(
            "wood_to_netherite", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.NETHERITE_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.NETHERITE_BARREL.get(), ModReference.MODID))
    );

    /**
     * Copper to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_IRON = REGISTER.register(
            "copper_to_iron", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.IRON_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.IRON_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_SILVER = REGISTER.register(
            "copper_to_silver", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.SILVER_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.SILVER_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_GOLD = REGISTER.register(
            "copper_to_gold", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.GOLD_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.GOLD_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_DIAMOND = REGISTER.register(
            "copper_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.DIAMOND_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_OBSIDIAN = REGISTER.register(
            "copper_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    /**
     * Iron to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> IRON_TO_SILVER = REGISTER.register(
            "iron_to_silver", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.IRON_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.SILVER_BARREL.get()))
            ).add(ModTags.Blocks.IRON_CHESTS, ModBlocks.SILVER_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> IRON_TO_GOLD = REGISTER.register(
            "iron_to_gold", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.IRON_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.GOLD_BARREL.get()))
            ).add(ModTags.Blocks.IRON_CHESTS, ModBlocks.GOLD_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> IRON_TO_DIAMOND = REGISTER.register(
            "iron_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.IRON_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(ModTags.Blocks.IRON_CHESTS, ModBlocks.DIAMOND_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> IRON_TO_OBSIDIAN = REGISTER.register(
            "iron_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.IRON_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(ModTags.Blocks.IRON_CHESTS, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    /**
     * Silver to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> SILVER_TO_GOLD = REGISTER.register(
            "silver_to_gold", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.SILVER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.GOLD_BARREL.get()))
            ).add(ModTags.Blocks.SILVER_CHESTS, ModBlocks.GOLD_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> SILVER_TO_DIAMOND = REGISTER.register(
            "silver_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.SILVER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(ModTags.Blocks.SILVER_CHESTS, ModBlocks.DIAMOND_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> SILVER_TO_OBSIDIAN = REGISTER.register(
            "silver_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.SILVER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(ModTags.Blocks.SILVER_CHESTS, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    /**
     * Gold to X.
     */

    public static final RegistryObject<BarrelUpgradeItem> GOLD_TO_DIAMOND = REGISTER.register(
            "gold_to_diamond", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.GOLD_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.DIAMOND_BARREL.get()))
            ).add(ModTags.Blocks.GOLD_CHESTS, ModBlocks.DIAMOND_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> GOLD_TO_OBSIDIAN = REGISTER.register(
            "gold_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.GOLD_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(ModTags.Blocks.GOLD_CHESTS, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> DIAMOND_TO_OBSIDIAN = REGISTER.register(
            "diamond_to_obsidian", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.DIAMOND_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.OBSIDIAN_BARREL.get()))
            ).add(ModTags.Blocks.DIAMOND_CHESTS, ModBlocks.OBSIDIAN_BARREL.get(), ModReference.MODID))
    );

    /**
     * Crystal.
     */

    public static final RegistryObject<BarrelUpgradeItem> WOOD_TO_CRYSTAL = REGISTER.register(
            "wood_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(Tags.Blocks.CHESTS_WOODEN, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> COPPER_TO_CRYSTAL = REGISTER.register(
            "copper_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.COPPER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(ModTags.Blocks.COPPER_CHESTS, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> IRON_TO_CRYSTAL = REGISTER.register(
            "iron_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.IRON_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(ModTags.Blocks.IRON_CHESTS, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> SILVER_TO_CRYSTAL = REGISTER.register(
            "silver_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.SILVER_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(ModTags.Blocks.SILVER_CHESTS, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> GOLD_TO_CRYSTAL = REGISTER.register(
            "gold_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.GOLD_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(ModTags.Blocks.GOLD_CHESTS, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    public static final RegistryObject<BarrelUpgradeItem> DIAMOND_TO_CRYSTAL = REGISTER.register(
            "diamond_to_crystal", () -> new BarrelUpgradeItem(properties, new UpgradeInfo(
                    new ArrayList<>(Collections.singleton(ModTags.Blocks.DIAMOND_BARRELS)),
                    new ArrayList<>(Collections.singleton(ModBlocks.CRYSTAL_BARREL.get()))
            ).add(ModTags.Blocks.DIAMOND_CHESTS, ModBlocks.CRYSTAL_BARREL.get(), ModReference.MODID))
    );

    /**
     * Extra Items.
     */

    public static final RegistryObject<BarrelMoveItem> BARREL_MOVE = REGISTER.register(
            "barrel_move", BarrelMoveItem::new
    );
}
