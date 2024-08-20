package com.spinshock.piras.datagen.providers;

import com.spinshock.piras.registry.PirasBlockRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.spinshock.piras.Piras.MOD_ID;

public class PirasItemModelProvider extends ItemModelProvider {
    public PirasItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(PirasBlockRegistry.RATE_METER_BI.getId().getPath(), mcLoc("block/cube_all"))
                .texture("all", "block/rate_meter");
    }
}
