package me.maxish0t.metalbarrels.client.screens.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BarrelUnlockButton extends Button {

    private final ResourceLocation UNLOCK_TEXTURE = new ResourceLocation(ModReference.MODID, "textures/gui/unlock.png");

    public BarrelUnlockButton(int p_93721_, int p_93722_, int p_93723_, int p_93724_, Component p_93725_, OnPress p_93726_) {
        super(p_93721_, p_93722_, p_93723_, p_93724_, p_93725_, p_93726_);
    }

    @Override
    public void renderButton(@NotNull PoseStack poseStack, int p_93747_, int p_93748_, float p_93749_) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, UNLOCK_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        if (this.isHoveredOrFocused())
            this.blit(poseStack, this.x - 4, this.y, 0, 86, 29, 20);
        else
            this.blit(poseStack, this.x - 4, this.y, 0, 66, 29, 19);

        this.renderBg(poseStack, minecraft, p_93747_, p_93748_);

        int j = getFGColor();

        if (this.isHoveredOrFocused())
            drawCenteredString(poseStack, font, ChatFormatting.GRAY + this.getMessage().getString(), this.x + (this.width + 4) / 2, this.y + (this.height + 15) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
        else
            drawCenteredString(poseStack, font, this.getMessage(), this.x + (this.width + 4) / 2, this.y + (this.height + 15) / 2, j | Mth.ceil(this.alpha * 255.0F) << 24);
    }

}
