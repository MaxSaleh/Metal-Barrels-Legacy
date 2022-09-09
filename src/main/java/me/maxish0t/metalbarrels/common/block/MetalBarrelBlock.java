package me.maxish0t.metalbarrels.common.block;

import me.maxish0t.metalbarrels.client.config.ClientConfig;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.common.item.extra.BarrelMoveItem;
import me.maxish0t.metalbarrels.server.BarrelNetwork;
import me.maxish0t.metalbarrels.server.packets.BarrelLockClientPacket;
import me.maxish0t.metalbarrels.server.packets.OpenedBarrelPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.network.NetworkDirection;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.IntStream;

import static net.minecraft.world.Containers.dropItemStack;

public class MetalBarrelBlock extends BarrelBlock {

  private final BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier;
  protected final String barrelName;
  private final int slotWidth;
  private final int slotHeight;

  public MetalBarrelBlock(Properties properties, String barrelName,
                          BlockEntityType.BlockEntitySupplier<BlockEntity> blockEntitySupplier, int slotWidth, int slotHeight) {
    super(properties);
    this.barrelName = barrelName;
    this.blockEntitySupplier = blockEntitySupplier;
    this.slotWidth = slotWidth;
    this.slotHeight = slotHeight;
  }

  @Override
  public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity tileentity = worldIn.getBlockEntity(pos);
      if (tileentity instanceof MetalBarrelBlockEntity) {
        if (!BarrelMoveItem.hasBarrel) {
          dropItems((MetalBarrelBlockEntity)tileentity,worldIn, pos);
          worldIn.updateNeighbourForOutputSignal(pos, this);
        }
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
  public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result) {
    if (!world.isClientSide) {
      MenuProvider tileEntity = getMenuProvider(state, world, pos);
      if (tileEntity != null) {
        MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) tileEntity;

        if (metalBarrelBlockEntity.getLocked()) {
          if (metalBarrelBlockEntity.getOwner().getString().equals(player.getDisplayName().getString())) {
            this.openBarrel(world, pos, state, metalBarrelBlockEntity, player, tileEntity);
          } else {
            boolean isWhitelisted = false;
            for (int i = 0; i < metalBarrelBlockEntity.getWhitelistedPlayers().size(); i++)
              if (metalBarrelBlockEntity.getWhitelistedPlayers().get(i).equals(player.getDisplayName().getString()))
                isWhitelisted = true;

            if (isWhitelisted)
              openBarrel(world, pos, state, metalBarrelBlockEntity, player, tileEntity);
            else
              player.sendSystemMessage(Component.translatable("metalbarrels.block.cannot.open").withStyle(ChatFormatting.RED));
          }
        } else {
          this.openBarrel(world, pos, state, metalBarrelBlockEntity, player, tileEntity);
        }
      }
    }
    return InteractionResult.SUCCESS;
  }

  private void openBarrel(Level level, BlockPos pos, BlockState state, MetalBarrelBlockEntity metalBarrelBlockEntity, Player player, MenuProvider blockEntity) {
    level.setBlock(pos, state.setValue(BarrelBlock.OPEN, true), 3);

    if (metalBarrelBlockEntity.players == 0) {
      metalBarrelBlockEntity.soundStuff(state, SoundEvents.BARREL_OPEN);
      metalBarrelBlockEntity.changeState(state, true);
    }

    metalBarrelBlockEntity.players++;
    boolean isOwner;
    isOwner = metalBarrelBlockEntity.getOwner().getString().equals(player.getDisplayName().getString());

    BarrelNetwork.CHANNEL.sendTo(new BarrelLockClientPacket(pos, metalBarrelBlockEntity.getLocked(), isOwner),
            ((ServerPlayer) player).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);

    BarrelNetwork.CHANNEL.sendTo(new OpenedBarrelPacket(pos), ((ServerPlayer) player).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);

    player.openMenu(blockEntity);
    player.awardStat(Stats.OPEN_BARREL);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(@NotNull BlockPos state, @NotNull BlockState blockState) {
    return blockEntitySupplier.create(state, blockState);
  }

  @Override
  public boolean hasAnalogOutputSignal(@NotNull BlockState blockState) {
    return true;
  }

  @Override
  public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull RandomSource randomSource) {
    super.animateTick(blockState, level, blockPos, randomSource);

    if (ClientConfig.BARREL_PARTICLES_EFFECT.get()) {
      level.addParticle(ParticleTypes.GLOW, blockPos.getX(), blockPos.getY(),
              blockPos.getZ(), 0.0D, 0.0D, 0.0D);
    }
  }

  @Override
  public int getAnalogOutputSignal(@NotNull BlockState blockState, Level world, @NotNull BlockPos pos) {
    BlockEntity barrel = world.getBlockEntity(pos);
    return barrel instanceof MetalBarrelBlockEntity ?
            ItemHandlerHelper.calcRedstoneFromInventory(((MetalBarrelBlockEntity) barrel).handler) : 0;
  }

  @Override
  public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
    if (!level.isClientSide) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (stack.hasCustomHoverName()) {
        if (blockEntity instanceof MetalBarrelBlockEntity) {
          ((MetalBarrelBlockEntity) blockEntity).setCustomName(stack.getDisplayName());
        }
      } else {
        if (placer instanceof Player player && blockEntity != null) {
          ((MetalBarrelBlockEntity) blockEntity).setOwner(Component.literal(player.getDisplayName().getString()));
          level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
        }
      }
    }
  }

  @Override
  public boolean canDropFromExplosion(BlockState state, BlockGetter level, BlockPos pos, Explosion explosion) {
    return true;
  }

  @Override
  public void appendHoverText(@NotNull ItemStack p_49816_, @Nullable BlockGetter p_49817_, @NotNull List<Component> list, @NotNull TooltipFlag p_49819_) {
    // TODO lang
    list.add(Component.literal(ChatFormatting.BLUE + "Slot Width: " + ChatFormatting.GRAY + slotWidth));
    list.add(Component.literal(ChatFormatting.BLUE + "Slot Height: " + ChatFormatting.GRAY + slotHeight));
    list.add(Component.literal(ChatFormatting.BLUE + "Slot Count: " + ChatFormatting.GRAY + (slotWidth * slotHeight)));
  }
}
