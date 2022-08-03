package com.tfar.metalbarrels.common.init;

import com.tfar.metalbarrels.MetalBarrels;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MetalBarrels.MODID);

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
     * WOOD TO X
     */

    public static final Item.Properties properties = new Item.Properties().tab(ModTabs.tab);

    /**
    public static final RegistryObject<Item> WOOD_TO_COPPER = REGISTER.register(
            "wood_to_copper", () -> new BarrelUpgradeItem(properties,  new UpgradeInfo(new ArrayList<>(Collections.singleton(ModTags.Blocks.WOODEN_BARRELS)),
                    new ArrayList<>(Collections.singleton(ObjectHolders.COPPER_BARREL)))
                    .add(Tags.Blocks.CHESTS_WOODEN, IronChestObjectHolders.COPPER_CHEST,"ironchest"))
    );**/

}
