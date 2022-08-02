package com.tfar.metalbarrels.init;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.block.MetalBarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, MetalBarrels.MODID);

    public static final RegistryObject<Block> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> new MetalBarrelBlock(BlockBehaviour.Properties.of(Material.AIR))
    );

}
