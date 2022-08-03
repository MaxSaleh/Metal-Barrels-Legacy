package com.tfar.metalbarrels.common.init;

import net.minecraftforge.eventbus.api.IEventBus;

public class ModRegistries {

    public static void registries(IEventBus eventBus) {
        ModBlockEntities.REGISTER.register(eventBus);
        ModBlocks.REGISTER.register(eventBus);
        ModItems.REGISTER.register(eventBus);
        ModMenuTypes.REGISTER.register(eventBus);
    }

}
