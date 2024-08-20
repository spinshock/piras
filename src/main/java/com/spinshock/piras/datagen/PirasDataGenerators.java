package com.spinshock.piras.datagen;

import com.spinshock.piras.Piras;
import com.spinshock.piras.datagen.providers.PirasBlockStatesProvider;
import com.spinshock.piras.datagen.providers.PirasItemModelProvider;
import com.spinshock.piras.datagen.providers.PirasLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = Piras.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PirasDataGenerators {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        generator.addProvider(event.includeClient(), new PirasBlockStatesProvider(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new PirasItemModelProvider(packOutput, event.getExistingFileHelper()));

        generator.addProvider(event.includeClient(), new PirasLanguageProvider(packOutput, "en_us"));
    }
}
