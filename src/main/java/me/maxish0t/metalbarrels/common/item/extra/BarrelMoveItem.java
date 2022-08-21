package me.maxish0t.metalbarrels.common.item.extra;

import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.common.init.ModTabs;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraftforge.items.ItemStackHandler;
import org.antlr.v4.runtime.misc.NotNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BarrelMoveItem extends Item {

    private final Map<ItemStack, Integer> storedItems = new HashMap<>();
    private BarrelBlock barrelBlock;
    public static boolean hasBarrel;

    public BarrelMoveItem() {
        super(new Item.Properties()
                .tab(ModTabs.tab)
                .stacksTo(1)
                .setNoRepair());
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (level.isClientSide || Objects.requireNonNull(player).getPose() != Pose.CROUCHING)
            return InteractionResult.PASS;

        if (level.getBlockState(pos).getBlock() instanceof BarrelBlock) {
            if (!hasBarrel) {
                barrelBlock = (BarrelBlock) level.getBlockState(pos).getBlock();
                MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) level.getBlockEntity(pos);

                if (metalBarrelBlockEntity != null) {
                    if (metalBarrelBlockEntity.getOwner().getString().equals(player.getDisplayName().getString())) {
                        ItemStackHandler stackHandler = metalBarrelBlockEntity.handler;
                        for (int i = 0; i < stackHandler.getSlots(); i++) {
                            if (!(stackHandler.getStackInSlot(i).getItem() instanceof AirItem)) {
                                storedItems.put(stackHandler.getStackInSlot(i), i);
                            }
                        }

                        hasBarrel = true;
                        level.removeBlock(pos, false);
                        level.removeBlockEntity(pos);
                        player.sendMessage(new TranslatableComponent("metalbarrels.player_message.successful")
                                .withStyle(ChatFormatting.GREEN), player.getUUID());
                    } else {
                        player.sendMessage(new TranslatableComponent("metalbarrels.player_message.not_owner")
                                .withStyle(ChatFormatting.RED), player.getUUID());
                    }
                }
            } else {
                player.sendMessage(new TranslatableComponent("metalbarrels.player_message.sorry")
                        .withStyle(ChatFormatting.RED), player.getUUID());
            }

            return InteractionResult.PASS;
        } else {
            if (!hasBarrel) {
                player.sendMessage(new TranslatableComponent("metalbarrels.player_message.sorry_metal")
                        .withStyle(ChatFormatting.RED), player.getUUID());
            }

        }

        if (hasBarrel) {
            BlockPos newPos;
            if (level.getBlockState(pos).getBlock() instanceof BushBlock
                    || level.getBlockState(pos).getBlock() instanceof TallGrassBlock) {
                newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
            } else {
                newPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            }

            Direction direction = player.getDirection();
            switch (direction) {
                case NORTH -> level.setBlock(newPos, barrelBlock.defaultBlockState().rotate(level, newPos, Rotation.CLOCKWISE_180), 2);
                case WEST -> level.setBlock(newPos, barrelBlock.defaultBlockState().rotate(level, newPos, Rotation.CLOCKWISE_90), 2);
                case EAST -> level.setBlock(newPos, barrelBlock.defaultBlockState().rotate(level, newPos, Rotation.COUNTERCLOCKWISE_90), 2);
                case SOUTH -> level.setBlock(newPos, barrelBlock.defaultBlockState().rotate(level, newPos, Rotation.NONE), 2);
            }

            player.sendMessage(new TranslatableComponent("metalbarrels.player_message.placed")
                    .withStyle(ChatFormatting.GREEN), player.getUUID());

            MetalBarrelBlockEntity metalBarrelBlockEntity = (MetalBarrelBlockEntity) level.getBlockEntity(newPos);
            if (metalBarrelBlockEntity != null) {
                for (ItemStack itemStack : storedItems.keySet()) {
                    metalBarrelBlockEntity.handler.setStackInSlot(storedItems.get(itemStack), itemStack);
                }
                metalBarrelBlockEntity.setOwner(new TextComponent(player.getDisplayName().getString()));
            }

            storedItems.clear();
            hasBarrel = false;
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> list, @NotNull TooltipFlag tooltipFlag) {
        if (!hasBarrel) {
            list.add(new TranslatableComponent("metalbarrels.player_message.tooltip_no_barrel")
                    .withStyle(ChatFormatting.RED));
        } else {
            list.add(new TranslatableComponent("metalbarrels.player_message.tooltip_with_barrel")
                    .withStyle(ChatFormatting.GREEN).append(barrelBlock.getName().getString()));
        }
    }
}
