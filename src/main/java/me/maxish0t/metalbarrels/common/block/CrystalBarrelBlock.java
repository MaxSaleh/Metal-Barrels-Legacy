package me.maxish0t.metalbarrels.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class CrystalBarrelBlock extends MetalBarrelBlock {

    public CrystalBarrelBlock(Properties properties, String barrelName,
                              BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier, int slotWidth, int slotHeight) {
        super(properties.noOcclusion(), barrelName, blockEntitySupplier, slotWidth, slotHeight);
    }

    @Override
    public int getLightBlock(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return 1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getShadeBrightness(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return true;
    }

    @Override
    public @NotNull VoxelShape getBlockSupportShape(@NotNull BlockState blockState, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos) {
        return Shapes.empty();
    }
}
