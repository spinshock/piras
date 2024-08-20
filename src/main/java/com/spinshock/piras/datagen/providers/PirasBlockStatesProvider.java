package com.spinshock.piras.datagen.providers;

import com.spinshock.piras.registry.PirasBlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.spinshock.piras.Piras.MOD_ID;

public class PirasBlockStatesProvider extends BlockStateProvider {
    public PirasBlockStatesProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (DeferredBlock<?> deferredBlock : PirasBlockRegistry.getBlocks()) {
            Block block = deferredBlock.get();
            simpleBlock(block, models().cubeAll(deferredBlock.getId().getPath(), blockTexture(block)));
        }
    }
}
