package com.tfar.metalbarrels.container;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.init.ModMenuTypes;
import com.tfar.metalbarrels.tile.MetalBarrelBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class MetalBarrelContainer extends AbstractContainerMenu {

	protected Player playerEntity;
	private final ContainerLevelAccess callable;
	public int width;
	public int height;

	public MetalBarrelContainer(MenuType<?> containerType,
								int id, Inventory playerInventory,
								int width, int height, int containerX, int containerY, int playerX, int playerY) {
		this(containerType,id, playerInventory, width,height,containerX,containerY,playerY,playerX, ContainerLevelAccess.NULL);
	}

	public static @NotNull MetalBarrelContainer copper(int containerId, Inventory inventory) {
		return new MetalBarrelContainer(ModMenuTypes.COPPER_CONTAINER.get(), containerId, inventory,
				9, 5, 8, 18, 8, 122);
	}

	public static @NotNull MetalBarrelContainer copper(int containerId, Inventory inventory, ContainerLevelAccess callable) {
		return new MetalBarrelContainer(ModMenuTypes.COPPER_CONTAINER.get(), containerId, inventory,
				9, 5, 8, 18, 8,122, callable);
	}

	public MetalBarrelContainer(MenuType<?> containerType,
								int id, Inventory playerInventory,
								int width, int height, int containerX, int containerY, int playerY, int playerX, ContainerLevelAccess callable) {
		super(containerType, id);
		this.width = width;
		this.height = height;
		this.playerEntity = playerInventory.player;
		this.callable = callable;

		ItemStackHandler stackHandler = callable.evaluate(Level::getBlockEntity).map(MetalBarrelBlockEntity.class::cast)
				.map(metalBarrelBlockEntity -> metalBarrelBlockEntity.handler)
				.orElse(new ItemStackHandler(width * height));

		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				addSlot(new SlotItemHandler(stackHandler,
						j + width * i, containerX + j * 18, containerY + i * 18));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, j * 18 + playerX, i * 18 + playerY));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlot(new Slot(playerInventory, i, i * 18 + playerX, playerY + 58));
		}
	}



	@Override
	public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < this.height * this.width) {
				if (!this.moveItemStackTo(itemstack1, this.height * this.width, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.height * this.width, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}

	@Override
	public boolean stillValid(@NotNull Player player) {
		return true;
	}

	@Override
	public void removed(@NotNull Player playerIn) {
		super.removed(playerIn);

		this.callable.execute((world, pos) -> {
			BlockEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity == null) {
				MetalBarrels.logger.warn("Unexpected null on container close.");
				return;
			}
			MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) tileEntity;

			if (!playerIn.isSpectator()) {
				--metalBarrelBlockEntity.players;
			}
			if (metalBarrelBlockEntity.players <= 0) {
				metalBarrelBlockEntity.soundStuff(tileEntity.getBlockState(), SoundEvents.BARREL_CLOSE);
				metalBarrelBlockEntity.changeState(playerIn.level.getBlockState(tileEntity.getBlockPos()), false);
			}
		});
	}
}

