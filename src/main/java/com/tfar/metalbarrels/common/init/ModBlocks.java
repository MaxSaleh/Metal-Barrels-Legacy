package com.tfar.metalbarrels.common.init;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.common.block.MetalBarrelBlock;
import com.tfar.metalbarrels.common.container.MetalBarrelContainer;
import com.tfar.metalbarrels.common.tile.MetalBarrelBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MetalBarrels.MODID);

    /**
     * Metal Barrels Block Registry.
     */

    public static final RegistryObject<Block> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "copper", ModBlockEntities.COPPER_BARREL.get())
    );

    public static final RegistryObject<Block> IRON_BARREL = REGISTER.register(
            "iron_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "iron", ModBlockEntities.IRON_BARREL.get())
    );

    public static final RegistryObject<Block> SILVER_BARREL = REGISTER.register(
            "silver_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "silver", ModBlockEntities.SILVER_BARREL.get())
    );

    public static final RegistryObject<Block> GOLD_BARREL = REGISTER.register(
            "gold_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "gold", ModBlockEntities.GOLD_BARREL.get())
    );

    public static final RegistryObject<Block> DIAMOND_BARREL = REGISTER.register(
            "diamond_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "diamond", ModBlockEntities.DIAMOND_BARREL.get())
    );

    public static final RegistryObject<Block> OBSIDIAN_BARREL = REGISTER.register(
            "obsidian_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "obsidian", ModBlockEntities.OBSIDIAN_BARREL.get())
    );

    public static final RegistryObject<Block> NETHERITE_BARREL = REGISTER.register(
            "netherite_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR),
                    "netherite", ModBlockEntities.NETHERITE_BARREL.get())
    );

    public static final RegistryObject<Block> CRYSTAL_BARREL = REGISTER.register(
            "crystal_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR), // TODO
                    "crystal", ModBlockEntities.CRYSTAL_BARREL.get())
    );

}
