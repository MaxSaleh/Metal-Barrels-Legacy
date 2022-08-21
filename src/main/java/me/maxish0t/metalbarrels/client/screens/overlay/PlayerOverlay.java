package me.maxish0t.metalbarrels.client.screens.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PlayerOverlay implements IGuiOverlay {

    private static final Minecraft minecraft = Minecraft.getInstance();

    @Override
    public void render(ForgeGui gui, PoseStack poseStack, float partialTick, int screenWidth, int screenHeight) {
        HitResult hitResult = minecraft.hitResult;
        if (minecraft.level != null) {
            ClientLevel clientLevel = minecraft.level;
            if (minecraft.player != null) {
                if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockhitresult = (BlockHitResult) hitResult;
                    BlockPos blockPos = blockhitresult.getBlockPos();
                    if (clientLevel.getBlockEntity(blockPos) instanceof MetalBarrelBlockEntity metalBarrelBlockEntity) {
                        if (metalBarrelBlockEntity.getOwner() != null) {
                            String ownerName = metalBarrelBlockEntity.getOwner().getString();
                            minecraft.font.draw(poseStack, ChatFormatting.GREEN + "" + ChatFormatting.BOLD +
                                    "Barrel Owner: " + ownerName, 5, 5, 0);
                        }
                    }
                }
            }
        }
    }
}
