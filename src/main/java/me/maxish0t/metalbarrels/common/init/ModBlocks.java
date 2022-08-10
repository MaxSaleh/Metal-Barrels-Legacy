package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.common.block.CrystalBarrelBlock;
import me.maxish0t.metalbarrels.common.block.MetalBarrelBlock;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.MetalBarrels;
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
            "copper_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "copper", MetalBarrelBlockEntity::copper)
    );

    public static final RegistryObject<Block> IRON_BARREL = REGISTER.register(
            "iron_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "iron", MetalBarrelBlockEntity::iron)
    );

    public static final RegistryObject<Block> SILVER_BARREL = REGISTER.register(
            "silver_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "silver", MetalBarrelBlockEntity::silver)
    );

    public static final RegistryObject<Block> GOLD_BARREL = REGISTER.register(
            "gold_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "gold", MetalBarrelBlockEntity::gold)
    );

    public static final RegistryObject<Block> DIAMOND_BARREL = REGISTER.register(
            "diamond_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "diamond", MetalBarrelBlockEntity::diamond)
    );

    public static final RegistryObject<Block> OBSIDIAN_BARREL = REGISTER.register(
            "obsidian_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "obsidian", MetalBarrelBlockEntity::obsidian)
    );

    public static final RegistryObject<Block> NETHERITE_BARREL = REGISTER.register(
            "netherite_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "netherite", MetalBarrelBlockEntity::netherite)
    );

    public static final RegistryObject<Block> CRYSTAL_BARREL = REGISTER.register(
            "crystal_barrel", () -> new CrystalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR)
                    .requiresCorrectToolForDrops().strength(2.5F),
                    "crystal", MetalBarrelBlockEntity::crystal)
    );
}
