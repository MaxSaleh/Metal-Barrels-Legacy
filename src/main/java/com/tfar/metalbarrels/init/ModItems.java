package com.tfar.metalbarrels.init;

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
