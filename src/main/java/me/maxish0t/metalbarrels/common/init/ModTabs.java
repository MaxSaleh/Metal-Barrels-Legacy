package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.MetalBarrels;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

    public static final CreativeModeTab tab = new CreativeModeTab(MetalBarrels.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.DIAMOND_BARREL.get());
        }
    };

}
