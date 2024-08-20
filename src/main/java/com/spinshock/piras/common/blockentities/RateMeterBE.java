package com.spinshock.piras.common.blockentities;

import com.spinshock.piras.PirasConfig;
import com.spinshock.piras.registry.PirasBlockEntitiesRegistry;
import com.spinshock.piras.util.InventoryCount;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

import static com.spinshock.piras.Piras.LOGGER;

public class RateMeterBE extends BlockEntity {
    private final Map<Direction, InventoryCount> adjInventoryCount = new EnumMap<>(Direction.class);

    public RateMeterBE(BlockPos pos, BlockState blockState) {
        super(PirasBlockEntitiesRegistry.RATE_METER_BE.get(), pos, blockState);
    }

    public void gaugeInventory(Level level, BlockPos blockPos, BlockState blockState) {
        for (Direction direction : Direction.values()) {
            IItemHandler itemHandler = level.getCapability(Capabilities.ItemHandler.BLOCK, blockPos.relative(direction), direction.getOpposite());
            if (itemHandler == null) {
                continue;
            }
            int itemCount = 0;
            for(int i = 0; i < itemHandler.getSlots(); i++) {
                itemCount += itemHandler.getStackInSlot(i).getCount();
            }

            LOGGER.info("Direction: {}, itemCount: {}", direction, itemCount);

            InventoryCount inventoryCount = adjInventoryCount.getOrDefault(direction, new InventoryCount(itemCount));

            inventoryCount.setValue(itemCount);

            // Store the updated InventoryCount in the map
            adjInventoryCount.put(direction, inventoryCount);

            LOGGER.info("Direction: {}, Items Entered/Left: {}", direction, inventoryCount.getDelta());
        }
    }

    @Override
    public void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
    }


    public static <T extends BlockEntity> void serverTick(Level level, BlockPos blockPos, BlockState blockState, T t) {
        ChunkPos chunkPos = new ChunkPos(blockPos);
        long tick = level.getGameTime() % PirasConfig.rateDeltaPrecision;
        if (tick != 0) {
            return;
        }
        if (t instanceof RateMeterBE && level.hasChunk(chunkPos.x, chunkPos.z)) {
            ((RateMeterBE) t).gaugeInventory(level, blockPos, blockState);
        }
    }

    public static <T extends BlockEntity> void clientTick(Level level, BlockPos blockPos, BlockState blockState, T t) {
    }
}
