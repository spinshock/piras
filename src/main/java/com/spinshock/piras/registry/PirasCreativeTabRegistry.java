package com.spinshock.piras.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.spinshock.piras.Piras.MOD_ID;
import static com.spinshock.piras.Piras.LOGGER;

public class PirasCreativeTabRegistry {

    public static final Component CREATIVE_TAB_TITLE_COMPONENT = Component.translatable("itemGroup." + MOD_ID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PIRAS_TAB = CREATIVE_MODE_TABS
            .register("piras", () -> CreativeModeTab.builder()
                    .title(CREATIVE_TAB_TITLE_COMPONENT)
                    .icon(() -> new ItemStack(PirasBlockRegistry.RATE_METER.get()))
                    .displayItems((_itemDisplayParameters, output) -> {
                        PirasBlockRegistry.getBlocks().forEach(output::accept);
                    })
                    .build());

    public static void init(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
