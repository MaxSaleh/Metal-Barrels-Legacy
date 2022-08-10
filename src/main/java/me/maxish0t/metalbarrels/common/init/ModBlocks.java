package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.common.block.CrystalBarrelBlock;
import me.maxish0t.metalbarrels.common.block.MetalBarrelBlock;
import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.MetalBarrels;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MetalBarrels.MODID);

    private static final BlockBehaviour.Properties metal = BlockBehaviour.Properties
            .of(Material.METAL)
            .strength(2.5F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.METAL);

    private static final BlockBehaviour.Properties obsidian = BlockBehaviour.Properties
            .of(Material.STONE)
            .strength(15.0F, 6000.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.NETHER_ORE);

    /**
     * Metal Barrels Block Registry.
     */

    public static final RegistryObject<Block> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> new MetalBarrelBlock(metal, "copper", MetalBarrelBlockEntity::copper)
    );

    public static final RegistryObject<Block> IRON_BARREL = REGISTER.register(
            "iron_barrel", () -> new MetalBarrelBlock(metal, "iron", MetalBarrelBlockEntity::iron)
    );

    public static final RegistryObject<Block> SILVER_BARREL = REGISTER.register(
            "silver_barrel", () -> new MetalBarrelBlock(metal, "silver", MetalBarrelBlockEntity::silver)
    );

    public static final RegistryObject<Block> GOLD_BARREL = REGISTER.register(
            "gold_barrel", () -> new MetalBarrelBlock(metal, "gold", MetalBarrelBlockEntity::gold)
    );

    public static final RegistryObject<Block> DIAMOND_BARREL = REGISTER.register(
            "diamond_barrel", () -> new MetalBarrelBlock(metal, "diamond", MetalBarrelBlockEntity::diamond)
    );

    public static final RegistryObject<Block> OBSIDIAN_BARREL = REGISTER.register(
            "obsidian_barrel", () -> new MetalBarrelBlock(obsidian, "obsidian", MetalBarrelBlockEntity::obsidian)
    );

    public static final RegistryObject<Block> NETHERITE_BARREL = REGISTER.register(
            "netherite_barrel", () -> new MetalBarrelBlock(obsidian, "netherite", MetalBarrelBlockEntity::netherite)
    );

    public static final RegistryObject<Block> CRYSTAL_BARREL = REGISTER.register(
            "crystal_barrel", () -> new CrystalBarrelBlock(metal.noOcclusion(), "crystal", MetalBarrelBlockEntity::crystal)
    );
}
