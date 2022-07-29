package com.tfar.metalbarrels.util;

import com.mojang.datafixers.types.Type;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Set;

public class MetalBarrelBlockEntityType<T extends BlockEntity> extends BlockEntityType<T> {

	public final int width;
	public final int height;
	public final PropertyDispatch.TriFunction<Integer, Inventory, ContainerLevelAccess, AbstractContainerMenu> containerFactory;

	public MetalBarrelBlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factoryIn, Set<Block> validBlocksIn,
									  Type type, int width, int height, PropertyDispatch.TriFunction<Integer,
			Inventory, ContainerLevelAccess, AbstractContainerMenu> containerFactory) {
		super(factoryIn, validBlocksIn, type);
		this.width = width;
		this.height = height;
		this.containerFactory = containerFactory;
	}
}
