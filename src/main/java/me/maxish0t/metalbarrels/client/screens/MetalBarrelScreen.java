package me.maxish0t.metalbarrels.client.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.common.container.MetalBarrelContainer;
import me.maxish0t.metalbarrels.server.BarrelNetwork;
import me.maxish0t.metalbarrels.server.packets.BarrelLockClientPacket;
import me.maxish0t.metalbarrels.server.packets.BarrelLockServerPacket;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class MetalBarrelScreen extends AbstractContainerScreen<MetalBarrelContainer> {

  private final ResourceLocation locked_texture = new ResourceLocation(ModReference.MODID,"textures/gui/container/locked.png");
  private final ResourceLocation unlocked_texture = new ResourceLocation(ModReference.MODID,"textures/gui/container/unlocked.png");
  private final ResourceLocation texture;
  private final boolean isTall;
  private final boolean isWide;

  public MetalBarrelScreen(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component, ResourceLocation texture, int xSize, int ySize) {
    super(barrelContainer, playerInventory, component);
    this.imageWidth = xSize;
    this.imageHeight = ySize;
    this.texture = texture;
    this.inventoryLabelY = this.imageHeight - 94;
    isTall = barrelContainer.height > 6;
    isWide = barrelContainer.width > 12;
  }

  @Override
  public void render(@NotNull PoseStack poseStack, int x, int y, float partialTicks) {
    this.renderBackground(poseStack);
    super.render(poseStack, x, y, partialTicks);
    this.renderTooltip(poseStack, x, y);

    BlockPos blockPos = BarrelLockClientPacket.barrelBlockPos;
    this.clearWidgets();

    if (BarrelLockClientPacket.isOwner) {
      if (!BarrelLockClientPacket.isLocked) {
        this.addRenderableWidget(new Button(5, this.height - 25, 50, 20, Component.translatable("metalbarrels.screen.lock"),
                (press) -> BarrelNetwork.CHANNEL.sendToServer(new BarrelLockServerPacket(blockPos, true))));
      } else {
        this.addRenderableWidget(new Button(5, this.height - 25, 50, 20, Component.literal("metalbarrels.screen.unlock"),
                (press) -> BarrelNetwork.CHANNEL.sendToServer(new BarrelLockServerPacket(blockPos, false))));
      }
    }
  }

  @Override
  protected void renderBg(@NotNull PoseStack stack, float partialTicks, int mouseX, int mouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderTexture(0, texture);

    int i = (this.width - this.imageWidth) / 2;
    int j = (this.height - this.imageHeight) / 2;

    if (!isTall) {
      this.blit(stack,i, j, 0, 0, this.imageWidth, this.imageHeight);
    } else if (!isWide) {
      blit(stack,i, j, 0, 0, getBlitOffset(), this.imageWidth, this.imageHeight,256,512);
    } else {
      if (texture.getPath().equals("textures/gui/container/netherite.png")) {
        blit(stack,i, j, 0, 0, getBlitOffset(), this.imageWidth, this.imageHeight,512, 512);
      } else {
        blit(stack,i, j, 0, 0, getBlitOffset(), this.imageWidth, this.imageHeight,256, 512);
      }
    }

    RenderSystem.enableBlend();
    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
  }

  private static final ResourceLocation COPPER = new ResourceLocation(ModReference.MODID,"textures/gui/container/copper.png");
  private static final ResourceLocation IRON = new ResourceLocation(ModReference.MODID,"textures/gui/container/iron.png");
  private static final ResourceLocation SILVER = new ResourceLocation(ModReference.MODID,"textures/gui/container/silver.png");
  private static final ResourceLocation GOLD = new ResourceLocation(ModReference.MODID,"textures/gui/container/gold.png");
  private static final ResourceLocation DIAMOND = new ResourceLocation(ModReference.MODID,"textures/gui/container/diamond.png");
  private static final ResourceLocation NETHERITE = new ResourceLocation(ModReference.MODID,"textures/gui/container/netherite.png");


  public static @NotNull MetalBarrelScreen copper(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,COPPER,176,204);
  }

  public static @NotNull MetalBarrelScreen iron(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,IRON,176,222);
  }

  public static @NotNull MetalBarrelScreen silver(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,SILVER,176,258);
  }

  public static @NotNull MetalBarrelScreen gold(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,GOLD,193,258);
  }

  public static @NotNull MetalBarrelScreen diamond(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer, playerInventory, component, DIAMOND,250, 258);
  }

  public static @NotNull MetalBarrelScreen obsidian(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,DIAMOND,250,258);
  }

  public static @NotNull MetalBarrelScreen netherite(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,NETHERITE,300,258);
  }

  public static @NotNull MetalBarrelScreen crystal(MetalBarrelContainer barrelContainer, Inventory playerInventory, Component component) {
    return new MetalBarrelScreen(barrelContainer,playerInventory,component,DIAMOND,250,258);
  }

}
