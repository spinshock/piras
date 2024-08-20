package com.spinshock.piras.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.spinshock.piras.Piras.MOD_ID;

public class PirasItemRegistry {
    static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static void init(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

//    public static final DeferredItem<BlockItem> RATE_METER_BI = ITEMS.register("rate_meter", () -> new BlockItem(PirasBlockRegistry.RATE_METER.get(), new Item.Properties()));
}
