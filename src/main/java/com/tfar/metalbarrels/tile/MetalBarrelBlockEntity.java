package com.tfar.metalbarrels.tile;

import com.tfar.metalbarrels.util.MetalBarrelBlockEntityType;
import com.tfar.metalbarrels.block.MetalBarrelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetalBarrelBlockEntity extends BlockEntity implements MenuProvider, Nameable {

  protected final int width;
  protected final int height;
  protected final PropertyDispatch.TriFunction<Integer, Inventory, ContainerLevelAccess, AbstractContainerMenu> containerFactory;
  protected Component customName;

  public MetalBarrelBlockEntity(BlockEntityType<?> tileEntityType, BlockPos blockPos, BlockState blockState) {
    super(tileEntityType, blockPos, blockState);
    this.width = ((MetalBarrelBlockEntityType)tileEntityType).width;
    this.height = ((MetalBarrelBlockEntityType)tileEntityType).height;
    this.containerFactory = ((MetalBarrelBlockEntityType)tileEntityType).containerFactory;
    handler = new ItemStackHandler(width * height) {
      @Override
      protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        setChanged();
      }
    };
    optional = LazyOptional.of(() -> handler);
  }

  public final LazyOptional<IItemHandler> optional;
  public final ItemStackHandler handler;

  public int players = 0;

  @Override
  protected void saveAdditional(CompoundTag tag) {
    CompoundTag compound = this.handler.serializeNBT();
    tag.put("inv", compound);
    if (this.customName != null) {
      tag.putString("CustomName", Component.Serializer.toJson(this.customName));
    }
    super.saveAdditional(tag);
  }

  public void changeState(BlockState blockState, boolean p_213963_2_) {
    if (blockState.getBlock() instanceof MetalBarrelBlock) {
      assert this.level != null;
      this.level.setBlock(this.getBlockPos(), blockState.setValue(BarrelBlock.OPEN, p_213963_2_), 3);
    }
    //else MetalBarrels.logger.warn("Attempted to set invalid property of {}",p_213963_1_.toString());
  }

  public void soundStuff(BlockState blockState, SoundEvent p_213965_2_) {
    if (!(blockState.getBlock() instanceof MetalBarrelBlock)){
      //MetalBarrels.logger.warn("Attempted to set invalid property of {}",p_213965_1_.toString());
      return;
    }

    Vec3i lvt_3_1_ = p_213965_1_.get(BarrelBlock.FACING).getDirectionVec();
    double lvt_4_1_ = this.getBlockPos().getX() + 0.5D + lvt_3_1_.getX() / 2.0D;
    double lvt_6_1_ = this.getBlockPos().getY() + 0.5D + lvt_3_1_.getY() / 2.0D;
    double lvt_8_1_ = this.getBlockPos().getZ() + 0.5D + lvt_3_1_.getZ() / 2.0D;
    this.level.playSound(null, lvt_4_1_, lvt_6_1_, lvt_8_1_, p_213965_2_,
            SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
  }

  @Override
  public void load(CompoundTag tag) {
    CompoundTag invTag = tag.getCompound("inv");
    handler.deserializeNBT(invTag);
    if (tag.contains("CustomName", 8)) {
      this.customName = Component.Serializer.fromJson(tag.getString("CustomName"));
    }
    super.load(tag);
  }

  @Override
  public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
    return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? optional.cast() : super.getCapability(cap, side);
  }

  @Override
  public void setRemoved() {
    super.setRemoved();
    optional.invalidate();
  }

  public void setCustomName(Component name) {
    this.customName = name;
  }

  public Component getName() {
    return this.customName != null ? this.customName : this.getDefaultName();
  }

  public Component getDisplayName() {
    return this.getName();
  }

  @Nullable
  public Component getCustomName() {
    return this.customName;
  }

  protected Component getDefaultName() {
    return Component.translatable(getBlockState().getBlock().getDescriptionId());
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
    assert this.level != null;
    return containerFactory.apply(id, inv, ContainerLevelAccess.create(this.level, this.getBlockPos()));
  }
}