package me.maxish0t.metalbarrels.common.init;

import me.maxish0t.metalbarrels.common.block.entity.MetalBarrelBlockEntity;
import me.maxish0t.metalbarrels.MetalBarrels;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> REGISTER =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MetalBarrels.MODID);

    /**
     * Metal Barrels Block Entity Type Registry.
     */

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> COPPER_BARREL = REGISTER.register(
            "copper_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::copper, ModBlocks.COPPER_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> IRON_BARREL = REGISTER.register(
            "iron_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::iron, ModBlocks.IRON_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> SILVER_BARREL = REGISTER.register(
            "silver_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::silver, ModBlocks.SILVER_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> GOLD_BARREL = REGISTER.register(
            "gold_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::gold, ModBlocks.GOLD_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> DIAMOND_BARREL = REGISTER.register(
            "diamond_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::diamond, ModBlocks.DIAMOND_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> OBSIDIAN_BARREL = REGISTER.register(
            "obsidian_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::obsidian, ModBlocks.OBSIDIAN_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> NETHERITE_BARREL = REGISTER.register(
            "netherite_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::netherite, ModBlocks.NETHERITE_BARREL.get())
                    .build(null)
    );

    public static final RegistryObject<BlockEntityType<MetalBarrelBlockEntity>> CRYSTAL_BARREL = REGISTER.register(
            "crystal_barrel", () -> BlockEntityType.Builder.of(
                            MetalBarrelBlockEntity::crystal, ModBlocks.CRYSTAL_BARREL.get())
                    .build(null)
    );
}
