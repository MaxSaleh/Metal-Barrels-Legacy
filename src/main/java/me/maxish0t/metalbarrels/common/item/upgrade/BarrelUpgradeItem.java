package me.maxish0t.metalbarrels.common.item.upgrade;

import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.IntStream;

public class BarrelUpgradeItem extends Item {

  protected Set<ItemStack> barrelItems = new HashSet<>();
  protected final UpgradeInfo upgradeInfo;

  public BarrelUpgradeItem(Properties properties, UpgradeInfo info) {
    super(properties);
    this.upgradeInfo = info;
  }

  private static final Component s = Component.translatable("tooltip.metalbarrels.ironchest")
          .append(ChatFormatting.GREEN.toString());

  public static boolean IRON_CHESTS_LOADED;

  @Override
  @OnlyIn(Dist.CLIENT)
  public void appendHoverText(ItemStack p_41421_, @org.jetbrains.annotations.Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
    if (IRON_CHESTS_LOADED) {
      tooltip.add(s);
    }
  }

  @Nonnull
  @Override
  public InteractionResult useOn(UseOnContext context) {
    Player player = context.getPlayer();
    BlockPos pos = context.getClickedPos();
    Level world = context.getLevel();
    ItemStack heldStack = context.getItemInHand();
    BlockState state = world.getBlockState(pos);

    if (player == null || !upgradeInfo.canUpgrade(world.getBlockState(pos).getBlock())) {
      return InteractionResult.FAIL;
    }
    if (world.isClientSide || player.getPose() != Pose.CROUCHING)
      return InteractionResult.PASS;

    if (state.getBlock() instanceof BarrelBlock)
      if (state.getValue(BlockStateProperties.OPEN)) {
        player.sendSystemMessage(Component.translatable("metalbarrels.in_use")
                .append((Component) Style.EMPTY.applyFormat(ChatFormatting.RED)));
        return InteractionResult.PASS;
      }

    BlockEntity oldBarrel = world.getBlockEntity(pos);
    final List<ItemStack> oldBarrelContents = new ArrayList<>();

    Direction facing = Direction.NORTH;
    if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
      facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
    } else if (state.hasProperty(BlockStateProperties.FACING)) {
      facing = state.getValue(BlockStateProperties.FACING);
    }

    // Barrel Block Item Transfer
    if (oldBarrel != null) {
      if (oldBarrel.getBlockState().getBlock() == Blocks.BARREL) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BarrelBlockEntity) {
          for (int i = 0; i < 27; i++) {
            if (!(((BarrelBlockEntity) blockEntity).getItem(i).getItem() instanceof AirItem)) {
              barrelItems.add(((BarrelBlockEntity) blockEntity).getItem(i));
            }
          }
        }
      }
    }

    if (oldBarrel instanceof ChestBlockEntity) {
      for (int i = 0; i < 27; i++) {
        oldBarrelContents.add(((ChestBlockEntity) oldBarrel).getItem(i));
      }
    } else {
      oldBarrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
              .ifPresent((itemHandler) -> IntStream.range(0, itemHandler.getSlots())
                      .mapToObj(itemHandler::getStackInSlot).forEach(oldBarrelContents::add));
    }
    oldBarrel.setRemoved();

    Block newBlock = upgradeInfo.getBlock(state.getBlock());

    BlockState newState = newBlock.defaultBlockState();

    if (newState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)){
      newState = newState.setValue(BlockStateProperties.HORIZONTAL_FACING,facing);
    } else if (newState.hasProperty(BlockStateProperties.FACING)){
      newState = newState.setValue(BlockStateProperties.FACING,facing);
    }

    world.setBlock(pos, newState, 3);
    BlockEntity newBarrel = world.getBlockEntity(pos);

    // Barrel Block Item Transfer
    if (newBarrel instanceof MetalBarrelBlockEntity) {
      for (ItemStack itemStack : barrelItems) {
        newBarrel.saveToItem(itemStack);
      }
    }

    newBarrel.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((itemHandler) ->
            IntStream.range(0, oldBarrelContents.size()).forEach(i -> itemHandler.insertItem(i, oldBarrelContents.get(i), false)));

    if (!player.getAbilities().instabuild)
      heldStack.shrink(1);

    player.sendSystemMessage(Component.translatable("metalbarrels.upgrade_successful")
            .withStyle(ChatFormatting.GREEN));

    return InteractionResult.SUCCESS;
  }
}