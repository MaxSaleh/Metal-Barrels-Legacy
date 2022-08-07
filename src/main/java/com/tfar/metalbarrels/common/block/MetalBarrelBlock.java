package com.tfar.metalbarrels.common.block;

import com.tfar.metalbarrels.common.tile.MetalBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

import static net.minecraft.world.Containers.dropItemStack;

public class MetalBarrelBlock extends BarrelBlock {

  protected final String barrelName;

  public MetalBarrelBlock(Properties properties, String barrelName, BlockEntityType<?> blockEntityType) {
    super(properties);
    this.barrelName = barrelName;
  }

  @Override
  public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos,
                       BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);
      if (tileentity instanceof MetalBarrelBlockEntity) {
        dropItems((MetalBarrelBlockEntity)tileentity,worldIn, pos);
        worldIn.updateNeighbourForOutputSignal(pos, this);
      }
      super.onRemove(state, worldIn, pos, newState, isMoving);
    }
  }

  public static void dropItems(MetalBarrelBlockEntity barrel, Level world, BlockPos pos) {
    IntStream.range(0, barrel.handler.getSlots()).mapToObj(barrel.handler::getStackInSlot)
            .filter(stack -> !stack.isEmpty()).forEach(stack ->
                    dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack));
  }

  @Override
  public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos,
                                        @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
    if (!world.isClientSide) {
      MenuProvider tileEntity = getMenuProvider(state, world, pos);
      if (tileEntity != null) {
        MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity)tileEntity;
        world.setBlock(pos, state.setValue(BarrelBlock.OPEN, true), 3);
        if (metalBarrelBlockEntity.players == 0) {
          metalBarrelBlockEntity.soundStuff(state, SoundEvents.BARREL_OPEN);
          metalBarrelBlockEntity.changeState(state, true);
        }
        metalBarrelBlockEntity.players++;
        player.openMenu(tileEntity);
        player.awardStat(Stats.OPEN_BARREL);
      }
    }
    return InteractionResult.SUCCESS;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@NotNull BlockPos state, @NotNull BlockState blockState) {
    return MetalBarrelBlockEntity.copper(state, blockState);
    /**
    return switch (barrelName) {
      case "copper" -> MetalBarrelBlockEntity.copper(state, blockState);
      case "iron" -> MetalBarrelBlockEntity.iron(state, blockState);
      case "silver" -> MetalBarrelBlockEntity.silver(state, blockState);
      case "gold" -> MetalBarrelBlockEntity.gold(state, blockState);
      case "diamond" -> MetalBarrelBlockEntity.diamond(state, blockState);
      case "obsidian" -> MetalBarrelBlockEntity.obsidian(state, blockState);
      case "netherite" -> MetalBarrelBlockEntity.netherite(state, blockState);
      case "crystal" -> MetalBarrelBlockEntity.crystal(state, blockState);
      default -> null;
    };
     **/
  }

  @Override
  public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(@NotNull BlockState blockState, Level world, @NotNull BlockPos pos) {
    BlockEntity barrel = world.getBlockEntity(pos);
    return barrel instanceof MetalBarrelBlockEntity ?
            ItemHandlerHelper.calcRedstoneFromInventory(((MetalBarrelBlockEntity) barrel).handler) : 0;
  }

  @Override
  public void setPlacedBy(@NotNull Level worldIn, @NotNull BlockPos pos, @NotNull BlockState state,
                          @Nullable LivingEntity placer, ItemStack stack) {
    if (stack.hasCustomHoverName()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);
      if (tileentity instanceof MetalBarrelBlockEntity) {
        ((MetalBarrelBlockEntity)tileentity).setCustomName(stack.getDisplayName());
      }
    }
  }
}
