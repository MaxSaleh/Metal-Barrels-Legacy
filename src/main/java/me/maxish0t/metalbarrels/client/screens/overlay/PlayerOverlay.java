package me.maxish0t.metalbarrels.client.screens.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class PlayerOverlay implements IIngameOverlay {

    private final Minecraft minecraft = Minecraft.getInstance();
    private final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/toasts.png");

    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        HitResult hitResult = minecraft.hitResult;
        if (minecraft.level != null) {
            ClientLevel clientLevel = minecraft.level;
            if (minecraft.player != null) {
                if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockhitresult = (BlockHitResult) hitResult;
                    BlockPos blockPos = blockhitresult.getBlockPos();
                    if (clientLevel.getBlockEntity(blockPos) instanceof MetalBarrelBlockEntity metalBarrelBlockEntity) {
                        if (metalBarrelBlockEntity.getOwner() != null) {
                            RenderSystem.setShader(GameRenderer::getPositionTexShader);
                            RenderSystem.setShaderTexture(0, TEXTURE);
                            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                            GuiComponent.blit(poseStack, 1, 1, 0, 0, 160, 32, 256, 256);

                            String ownerName = metalBarrelBlockEntity.getOwner().getString();
                            minecraft.font.draw(poseStack, ChatFormatting.GREEN + "" + ChatFormatting.BOLD +
                                    new TranslatableComponent("metalbarrels.player_overlay").getString(), 7, 7, 0);
                            minecraft.font.draw(poseStack, ChatFormatting.BLUE + "" + ChatFormatting.BOLD +
                                    ownerName, 7, 17, 0);
                        }
                    }
                }
            }
        }
    }
}
