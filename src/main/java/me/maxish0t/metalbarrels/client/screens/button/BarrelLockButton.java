package me.maxish0t.metalbarrels.client.screens.button;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.util.ModReference;
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
public class BarrelLockButton extends Button {
    private final ResourceLocation LOCK_TEXTURE = new ResourceLocation(ModReference.MODID, "textures/gui/locked.png");
    private final int screenWidth;
    private final int screenHeight;

    public BarrelLockButton(int screenWidth, int screenHeight, int p_93721_, int p_93722_, int p_93723_, int p_93724_, Component p_93725_, OnPress p_93726_) {
        super(p_93721_, p_93722_, p_93723_, p_93724_, p_93725_, p_93726_);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public void renderButton(@NotNull PoseStack poseStack, int p_93747_, int p_93748_, float p_93749_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, LOCK_TEXTURE);

        poseStack.pushPose();
        poseStack.scale(0.4F, 0.4F, 0.4F);
        this.blit(poseStack, this.x + this.width / 2, this.y + (this.height - 8) / 2, 0, 70, 240, 180);
        poseStack.popPose();

        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        Font font = Minecraft.getInstance().font;
        drawCenteredString(poseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, getFGColor() | Mth.ceil(this.alpha * 255.0F) << 24);

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(poseStack, p_93747_, p_93748_);
        }
    }
}
