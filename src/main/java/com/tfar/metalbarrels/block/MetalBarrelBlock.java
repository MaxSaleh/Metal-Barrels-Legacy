package com.tfar.metalbarrels.block;

import com.tfar.metalbarrels.tile.MetalBarrelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static net.minecraft.world.Containers.dropItemStack;

@SuppressWarnings("deprecation")
public class MetalBarrelBlock extends BarrelBlock {
  public MetalBarrelBlock(Properties properties) {
    super(properties);
  }

  @Override
  public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
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
            .filter(stack -> !stack.isEmpty()).forEach(stack -> dropItemStack(world, pos.getX(), pos.getY(), pos.getZ(), stack));
  }

  @Override
  public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
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
  public BlockEntity newBlockEntity(BlockPos state, BlockState blockState) {
    return MetalBarrelBlockEntity.copper(state, blockState);
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState p_49058_) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
    BlockEntity barrel = world.getBlockEntity(pos);
    return barrel instanceof MetalBarrelBlockEntity ? ItemHandlerHelper.calcRedstoneFromInventory(((MetalBarrelBlockEntity) barrel).handler) : 0;
  }

  @Override
  public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
    if (stack.hasCustomHoverName()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);
      if (tileentity instanceof MetalBarrelBlockEntity) {
        ((MetalBarrelBlockEntity)tileentity).setCustomName(stack.getDisplayName());
      }
    }
  }

  /**
  @Nullable
  @Override
  public ToolType getHarvestTool(BlockState state) {
    return ToolType.PICKAXE;
  }**/
}
