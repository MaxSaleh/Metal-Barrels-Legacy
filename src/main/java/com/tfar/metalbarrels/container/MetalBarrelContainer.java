package com.tfar.metalbarrels.container;

import com.tfar.metalbarrels.block.MetalBarrelsTypes;
import com.tfar.metalbarrels.init.ModMenuTypes;
import com.tfar.metalbarrels.screens.MetalBarrelScreen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class MetalBarrelContainer extends ChestMenu {

	private MetalBarrelContainer(MenuType<?> p_39224_, int p_39225_, Inventory p_39226_, int p_39227_) {
		this(p_39224_, p_39225_, p_39226_, new SimpleContainer(9 * p_39227_), p_39227_);
	}

	public static @NotNull ChestMenu copper(int containerId, Inventory inventory) {
		return new ChestMenu(ModMenuTypes.COPPER_CONTAINER.get(), containerId, inventory,
				new SimpleContainer(27), 3);
	}

	public MetalBarrelContainer(MenuType<?> p_39229_, int p_39230_, Inventory p_39231_, Container p_39232_, int p_39233_) {
		super(p_39229_, p_39230_, p_39231_, p_39232_, p_39233_);
	}
}

