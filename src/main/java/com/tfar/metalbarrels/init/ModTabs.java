package com.tfar.metalbarrels.init;

import com.tfar.metalbarrels.MetalBarrels;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

    public static final CreativeModeTab tab = new CreativeModeTab(MetalBarrels.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.COPPER_BARREL.get());
        }
    };

}
