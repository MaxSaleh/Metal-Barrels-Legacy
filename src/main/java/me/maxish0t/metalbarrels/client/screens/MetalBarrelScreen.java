package me.maxish0t.metalbarrels.client.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.client.screens.button.BarrelAddPlayerButton;
import me.maxish0t.metalbarrels.client.screens.button.BarrelLockButton;
import me.maxish0t.metalbarrels.client.screens.button.BarrelRemovePlayerButton;
import me.maxish0t.metalbarrels.client.screens.button.BarrelUnlockButton;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.common.container.MetalBarrelContainer;
import me.maxish0t.metalbarrels.server.BarrelNetwork;
import me.maxish0t.metalbarrels.server.packets.*;
import me.maxish0t.metalbarrels.util.ModReference;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class MetalBarrelScreen extends AbstractContainerScreen<MetalBarrelContainer> {

  private final ResourceLocation texture;
  private EditBox usernameInput;
  private boolean renderUsernameInput;
  private boolean isWhitelist;
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
  protected void init() {
    super.init();

    if (minecraft != null)
      this.minecraft.keyboardHandler.setSendRepeatsToGui(true);

    this.usernameInput = new EditBox(this.font, 5, this.height - 115, 80, 12, Component.literal("Input username"));
    this.usernameInput.setCanLoseFocus(false);
    this.usernameInput.setTextColor(-1);
    this.usernameInput.setTextColorUneditable(-1);
    this.usernameInput.setBordered(true);
    this.usernameInput.setMaxLength(15);
    this.setInitialFocus(this.usernameInput);
    this.usernameInput.setEditable(true);
    this.usernameInput.setValue("");
  }

  @Override
  public void render(@NotNull PoseStack poseStack, int x, int y, float partialTicks) {
    this.renderBackground(poseStack);
    super.render(poseStack, x, y, partialTicks);
    this.renderTooltip(poseStack, x, y);

    BlockPos blockPos = BarrelLockClientPacket.barrelBlockPos;
    this.clearWidgets();

    // TODO translate messages

    if (BarrelLockClientPacket.isOwner) {

      poseStack.pushPose();
      poseStack.scale(0.8F, 0.8F, 0.8F);
      this.font.draw(poseStack, ChatFormatting.WHITE + "Whitelisted Players:", this.width + 10, 5, 0);

      if (this.minecraft != null) {
        if (this.minecraft.level != null) {
          BlockEntity blockEntity = this.minecraft.level.getBlockEntity(OpenedBarrelPacket.barrelBlockPos);

          if (blockEntity instanceof MetalBarrelBlockEntity) {
            MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) this.minecraft.level.getBlockEntity(OpenedBarrelPacket.barrelBlockPos);

            if (metalBarrelBlockEntity != null) {
              int height = 20;
              for (int i = 0; i < metalBarrelBlockEntity.getWhitelistedPlayers().size(); i++) {
                this.font.draw(poseStack, ChatFormatting.GRAY + "- " + ChatFormatting.WHITE + metalBarrelBlockEntity.getWhitelistedPlayers().get(i),
                        this.width + 10, height, 0);
                height += 10;
              }
            }
          }
        }
      }

      poseStack.popPose();

      if (renderUsernameInput) {
        poseStack.pushPose();
        poseStack.scale(0.69F, 0.69F, 0.69F);
        this.font.draw(poseStack, ChatFormatting.GRAY + "Press enter to complete", 4, this.height - 70, 0);
        this.font.draw(poseStack, ChatFormatting.GRAY + "Click on the box to type", 5, this.height - 80, 0);
        poseStack.popPose();
        this.addRenderableWidget(this.usernameInput);
        this.usernameInput.render(poseStack, x, y, partialTicks);
      } else {
        this.clearWidgets();
      }

      if (!BarrelLockClientPacket.isLocked) {
        this.addRenderableWidget(new BarrelLockButton(30, this.height - 35, 25, 30, Component.translatable("metalbarrels.screen.lock"),
                (press) -> BarrelNetwork.CHANNEL.sendToServer(new BarrelLockServerPacket(blockPos, true))));
      } else {
        this.addRenderableWidget(new BarrelUnlockButton(30, this.height - 35, 25, 30, Component.translatable("metalbarrels.screen.unlock"),
                (press) -> BarrelNetwork.CHANNEL.sendToServer(new BarrelLockServerPacket(blockPos, false))));
      }

      this.addRenderableWidget(new BarrelAddPlayerButton(5, this.height - 68, 80, 30, Component.literal("Whitelist Player"),
              (press) -> {
                renderUsernameInput = true;
                isWhitelist = true;
      }));

      this.addRenderableWidget(new BarrelRemovePlayerButton(5, this.height - 101, 80, 30, Component.literal("Remove Player"),
              (press) -> {
                renderUsernameInput = true;
                isWhitelist = false;
      }));
    }
  }

  @Override
  public boolean keyPressed(int key, int b, int c) {
    if (!this.usernameInput.getValue().equals("")) {
      if (key == GLFW.GLFW_KEY_ENTER) {
        if (isWhitelist)
          BarrelNetwork.CHANNEL.sendToServer(new BarrelWhitelistPlayerPacket(OpenedBarrelPacket.barrelBlockPos, this.usernameInput.getValue()));
        else
          BarrelNetwork.CHANNEL.sendToServer(new BarrelRemovePlayerPacket(OpenedBarrelPacket.barrelBlockPos, this.usernameInput.getValue()));

        this.usernameInput.setValue("");
        this.renderUsernameInput = false;
        this.isWhitelist = false;
        this.clearWidgets();
      }
    } else if (key != GLFW.GLFW_KEY_E) {
      return super.keyPressed(key, b, c);
    }
    return false;
  }

  @Override
  protected void containerTick() {
    super.containerTick();
    this.usernameInput.tick();
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
