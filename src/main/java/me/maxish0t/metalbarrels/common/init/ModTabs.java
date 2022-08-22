package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabs {

    public static final CreativeModeTab tab = new CreativeModeTab(ModReference.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.DIAMOND_BARREL.get());
        }
    };

}
