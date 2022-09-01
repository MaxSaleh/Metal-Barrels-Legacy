package me.maxish0t.metalbarrels.client.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CRYSTAL_BARREL_ITEMS_RENDER;
    public static final ForgeConfigSpec.ConfigValue<Boolean> BARREL_PARTICLES_EFFECT;

    static {
        BUILDER.push("~ Client Config for Metal Barrels Mod ~");
        CRYSTAL_BARREL_ITEMS_RENDER = BUILDER
                .comment(" You can choose whether you want the crystal barrel to render the items in the barrel or not.")
                .define("Show Crystal Barrel Items Render", true);
        BARREL_PARTICLES_EFFECT = BUILDER
                .comment(" This adds a few particles to be rendered on the barrel to make them look cool.")
                .define("Show Barrel Particles", false);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
