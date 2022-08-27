package me.maxish0t.metalbarrels.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class CrystalBarrelRender implements BlockEntityRenderer<MetalBarrelBlockEntity> {

    private final Vector3f[] vector3fs = new Vector3f[]{
            new Vector3f(0.3F, 0.7F, 0.25F),
            new Vector3f(0.7F, 0.7F, 0.25F),
            new Vector3f(0.7F, 0.7F, 0.75F),
            new Vector3f(0.3F, 0.7F, 0.75F),
            new Vector3f(0.3F, 0.3F, 0.25F),
            new Vector3f(0.7F, 0.3F, 0.25F),
            new Vector3f(0.7F, 0.3F, 0.75F),
            new Vector3f(0.3F, 0.3F, 0.75F)};
    private final List<Vector3f> vector3fList = Arrays.asList(vector3fs);

    public CrystalBarrelRender(BlockEntityRendererProvider.Context context) { }

    @Override
    public void render(@NotNull MetalBarrelBlockEntity blockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int combinedLightIn, int combinedOverlayIn) {
        BlockPos blockPos = blockEntity.getBlockPos();
        if (Minecraft.getInstance().player != null) {
            double distanceAway = blockPos.distToCenterSqr(Minecraft.getInstance().player.position());
            if (distanceAway < 165) {
                for (int i = 0; i < 8; i++) {
                    renderItem(poseStack, bufferSource, blockEntity.getItemStackHandler().getStackInSlot(i),
                            vector3fList.get(i), 0.2F, 0F, combinedLightIn);
                }
            }
        }
    }

    public static void renderItem(PoseStack matrices, MultiBufferSource buffer, ItemStack item, Vector3f vector3f, float scale, float rotation, int light) {
        if (item.isEmpty()) return;
        matrices.pushPose();
        matrices.translate(vector3f.x(), vector3f.y(), vector3f.z());
        matrices.mulPose(Vector3f.YP.rotation(rotation));
        matrices.scale(scale, scale, scale);
        Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemTransforms.TransformType.NONE, light,
                OverlayTexture.NO_OVERLAY, matrices, buffer, 0);
        matrices.popPose();
    }

}
