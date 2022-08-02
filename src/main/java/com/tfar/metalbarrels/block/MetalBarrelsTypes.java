package com.tfar.metalbarrels.block;

import com.tfar.metalbarrels.MetalBarrels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public enum MetalBarrelsTypes implements StringRepresentable {
    COPPER(1, 1, 184, 184, new
            ResourceLocation(MetalBarrels.MODID, "textures/gui/container/copper.png"), 256, 256);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    MetalBarrelsTypes(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }

    MetalBarrelsTypes(@Nullable String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this.name = name == null ? this.toEnglishName(this.name()) : name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public String getId() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String getEnglishName() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.getEnglishName();
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public String toEnglishName(String internalName) {
        return Arrays.stream(internalName.toLowerCase(Locale.ROOT).split("_"))
                .map(StringUtils::capitalize)
                .collect(Collectors.joining(" "));
    }
}
