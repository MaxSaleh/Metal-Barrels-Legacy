package me.maxish0t.metalbarrels.client.screens.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import me.maxish0t.metalbarrels.MetalBarrels;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = MetalBarrels.MODID, value = Dist.CLIENT)
public class PlayerOverlay {

    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void renderOverlay(RenderGuiOverlayEvent.Pre event) {
        PoseStack poseStack = event.getPoseStack();
        HitResult hitResult = minecraft.hitResult;
        if (minecraft.level != null) {
            ClientLevel clientLevel = minecraft.level;
            if (minecraft.player != null) {
                LocalPlayer localPlayer = minecraft.player;
                if (hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = new BlockPos(hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
                    if (clientLevel.getBlockEntity(blockPos) instanceof MetalBarrelBlockEntity metalBarrelBlockEntity) {

                        System.out.println(metalBarrelBlockEntity.getOwner().getString());

                        //if (metalBarrelBlockEntity.getOwner().getString().equals(localPlayer.getUUID().toString())) {
                            //minecraft.font.draw(poseStack, "Test Text", 5, 5, 0);
                        //}
                    }
                }
            }
        }
    }

}
