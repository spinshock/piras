package com.spinshock.piras;

import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Piras.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PirasConfig {
        private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

        private static final ModConfigSpec.IntValue RATE_DELTA_PRECISION = BUILDER
                        .comment("Rate delta precision")
                        .defineInRange("rateDeltaPrecision", 10, 1, 20);

        static final ModConfigSpec SPEC = BUILDER.build();


        public static int rateDeltaPrecision;

        @SubscribeEvent
        static void onLoad(final ModConfigEvent event) {
                rateDeltaPrecision = RATE_DELTA_PRECISION.get();
        }
}
