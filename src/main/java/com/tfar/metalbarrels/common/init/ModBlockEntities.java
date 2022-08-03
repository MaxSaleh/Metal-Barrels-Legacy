package com.tfar.metalbarrels.common.init;

import com.tfar.metalbarrels.MetalBarrels;
import com.tfar.metalbarrels.common.tile.MetalBarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MetalBarrels.MODID);

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::copper, ModBlocks.COPPER_BARREL.get())
                    .build(null)
    );
}
