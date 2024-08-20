package com.spinshock.piras.datagen.providers;

import com.spinshock.piras.registry.PirasBlockRegistry;
import com.spinshock.piras.registry.PirasCreativeTabRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

import static com.spinshock.piras.Piras.MOD_ID;
import static com.spinshock.piras.Piras.MOD_NAME;

public class PirasLanguageProvider extends LanguageProvider {

    public PirasLanguageProvider(PackOutput packOutput, String locale) {
        super(packOutput, MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(PirasCreativeTabRegistry.CREATIVE_TAB_TITLE_COMPONENT.getString(), MOD_NAME.toUpperCase());
        addBlock(PirasBlockRegistry.RATE_METER, "Rate Meter");
    }
}
