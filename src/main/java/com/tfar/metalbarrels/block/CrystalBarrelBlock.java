package com.tfar.metalbarrels.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public class CrystalBarrelBlock extends MetalBarrelBlock {
	public CrystalBarrelBlock(Properties properties) {
		super(properties);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public float getShadeBrightness(BlockState p_60472_, BlockGetter p_60473_, BlockPos p_60474_) {
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
		return true;
	}
}
