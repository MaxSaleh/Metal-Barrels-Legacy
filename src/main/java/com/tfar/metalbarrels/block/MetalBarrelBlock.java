package com.tfar.metalbarrels.block;

import com.tfar.metalbarrels.tile.MetalBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class MetalBarrelBlock extends BarrelBlock {
  public static final DirectionProperty FACING = BlockStateProperties.FACING;
  public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

  public MetalBarrelBlock(BlockBehaviour.Properties p_49046_) {
    super(p_49046_);
    this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.valueOf(false)));
  }

  @Override
  public InteractionResult use(BlockState p_49069_, Level p_49070_, BlockPos p_49071_, Player p_49072_, InteractionHand p_49073_, BlockHitResult p_49074_) {
    if (p_49070_.isClientSide) {
      return InteractionResult.SUCCESS;
    } else {
      BlockEntity blockentity = p_49070_.getBlockEntity(p_49071_);
      if (blockentity instanceof MetalBarrelBlockEntity) {
        p_49072_.openMenu((MetalBarrelBlockEntity)blockentity);
        p_49072_.awardStat(Stats.OPEN_BARREL);
        PiglinAi.angerNearbyPiglins(p_49072_, true);
      }

      return InteractionResult.CONSUME;
    }
  }

  @Override
  public void onRemove(BlockState p_49076_, Level p_49077_, BlockPos p_49078_, BlockState p_49079_, boolean p_49080_) {
    if (!p_49076_.is(p_49079_.getBlock())) {
      BlockEntity blockentity = p_49077_.getBlockEntity(p_49078_);
      if (blockentity instanceof Container) {
        Containers.dropContents(p_49077_, p_49078_, (Container)blockentity);
        p_49077_.updateNeighbourForOutputSignal(p_49078_, this);
      }

      super.onRemove(p_49076_, p_49077_, p_49078_, p_49079_, p_49080_);
    }
  }

  @Override
  public void tick(BlockState p_220758_, ServerLevel p_220759_, BlockPos p_220760_, RandomSource p_220761_) {
    BlockEntity blockentity = p_220759_.getBlockEntity(p_220760_);
    if (blockentity instanceof MetalBarrelBlockEntity) {
      ((MetalBarrelBlockEntity)blockentity).recheckOpen();
    }

  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos p_152102_, BlockState p_152103_) {
    return new MetalBarrelBlockEntity(p_152102_, p_152103_);
  }

  @Override
  public RenderShape getRenderShape(BlockState p_49090_) {
    return RenderShape.MODEL;
  }

  @Override
  public void setPlacedBy(Level p_49052_, BlockPos p_49053_, BlockState p_49054_, @Nullable LivingEntity p_49055_, ItemStack p_49056_) {
    if (p_49056_.hasCustomHoverName()) {
      BlockEntity blockentity = p_49052_.getBlockEntity(p_49053_);
      if (blockentity instanceof MetalBarrelBlockEntity) {
        ((MetalBarrelBlockEntity)blockentity).setCustomName(p_49056_.getHoverName());
      }
    }

  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState p_49058_) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState p_49065_, Level p_49066_, BlockPos p_49067_) {
    return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(p_49066_.getBlockEntity(p_49067_));
  }

  @Override
  public BlockState rotate(BlockState p_49085_, Rotation p_49086_) {
    return p_49085_.setValue(FACING, p_49086_.rotate(p_49085_.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState p_49082_, Mirror p_49083_) {
    return p_49082_.rotate(p_49083_.getRotation(p_49082_.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49088_) {
    p_49088_.add(FACING, OPEN);
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext p_49048_) {
    return this.defaultBlockState().setValue(FACING, p_49048_.getNearestLookingDirection().getOpposite());
  }
}
