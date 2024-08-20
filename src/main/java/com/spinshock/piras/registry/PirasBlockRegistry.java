package com.spinshock.piras.registry;

import com.spinshock.piras.common.blocks.RateMeter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.spinshock.piras.Piras.MOD_ID;
import static com.spinshock.piras.Piras.LOGGER;

public class PirasBlockRegistry {
    // Blocks
    static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredBlock<RateMeter> RATE_METER = BLOCKS.register("rate_meter", RateMeter::new);

    // BlockItems
    static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(MOD_ID);
    public static final DeferredItem<BlockItem> RATE_METER_BI = BLOCK_ITEMS.register("rate_meter", () -> new BlockItem(RATE_METER.get(), new Item.Properties()));

    public static void init(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
    }

    public static ArrayList<DeferredBlock<?>> getBlocks() {
        ArrayList<DeferredBlock<?>> blocks = new ArrayList<>();
        Field[] fields = PirasBlockRegistry.class.getDeclaredFields();

        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                    java.lang.reflect.Modifier.isFinal(field.getModifiers()) &&
                    DeferredBlock.class.isAssignableFrom(field.getType())) {
                try {
                    DeferredBlock<?> deferredBlock = (DeferredBlock<?>) field.get(null);
                    blocks.add(deferredBlock);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return blocks;
    }

    public static ArrayList<DeferredItem<?>> getBlockItems() {
        ArrayList<DeferredItem<?>> blockItems = new ArrayList<>();
        Field[] fields = PirasBlockRegistry.class.getDeclaredFields();

        for (Field field : fields) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) &&
                    java.lang.reflect.Modifier.isFinal(field.getModifiers()) &&
                    DeferredItem.class.isAssignableFrom(field.getType())) {
                try {
                    DeferredItem<?> deferredItem = (DeferredItem<?>) field.get(null);
                    blockItems.add(deferredItem);
                } catch (IllegalAccessException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
        return blockItems;
    }
}
