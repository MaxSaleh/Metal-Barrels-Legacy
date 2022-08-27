package me.maxish0t.metalbarrels.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CRYSTAL_BARREL_ITEMS_RENDER;

    static {
        BUILDER.push("~ Client Config for Metal Barrels Mod ~");
        CRYSTAL_BARREL_ITEMS_RENDER = BUILDER
                .comment(" You can choose whether you want the crystal barrel to render the items in the barrel or not.")
                .define("Crystal Barrel Items Render", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
