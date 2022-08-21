package me.maxish0t.metalbarrels.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;

public class ModTags {

  public static class Blocks {
    public static final Tag<Block> WOODEN_BARRELS = tag("barrels/wooden");
    public static final Tag<Block> COPPER_BARRELS = tag("barrels/copper");
    public static final Tag<Block> IRON_BARRELS = tag("barrels/iron");
    public static final Tag<Block> SILVER_BARRELS = tag("barrels/silver");
    public static final Tag<Block> GOLD_BARRELS = tag("barrels/gold");
    public static final Tag<Block> DIAMOND_BARRELS = tag("barrels/diamond");

    public static final Tag<Block> COPPER_CHESTS = tag("chests/copper");
    public static final Tag<Block> IRON_CHESTS = tag("chests/iron");
    public static final Tag<Block> SILVER_CHESTS = tag("chests/silver");
    public static final Tag<Block> GOLD_CHESTS = tag("chests/gold");
    public static final Tag<Block> DIAMOND_CHESTS = tag("chests/diamond");

    private static Tag<Block> tag(String name) {
      return BlockTags.createOptional(new ResourceLocation("forge", name));
    }
  }
}
