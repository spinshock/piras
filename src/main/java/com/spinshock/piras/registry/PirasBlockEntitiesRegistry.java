package com.spinshock.piras.registry;

import com.spinshock.piras.common.blockentities.RateMeterBE;
import com.spinshock.piras.registry.PirasBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.spinshock.piras.Piras.MOD_ID;

public class PirasBlockEntitiesRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RateMeterBE>> RATE_METER_BE = BLOCK_ENTITIES.register("rate_meter", () -> BlockEntityType.Builder.of(RateMeterBE::new, PirasBlockRegistry.RATE_METER.get()).build(null));

    public static void init(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
