package com.spinshock.piras.util;

import com.spinshock.piras.PirasConfig;
import lombok.Getter;

import java.util.Arrays;

public class InventoryCount {
    private static final int MAX_DELTAS = 40 / PirasConfig.rateDeltaPrecision;

    @Getter
    private double lastValue;
    @Getter
    private double newValue;
    private final FixedSizeDoubleQueue deltas = new FixedSizeDoubleQueue(MAX_DELTAS);

    public InventoryCount(double value) {
        this.lastValue = value;
        this.newValue = value;
    }

    public double getDelta() {
        return average(deltas.toArray(new Double[0]));
    }

    public void setValue(double value) {
        this.lastValue = this.newValue;
        this.newValue = value;
        deltas.add(this.newValue - this.lastValue);
    }

    public static Double average(Double... values) {
        return Arrays.stream(values).reduce(0.0, Double::sum) / values.length;
    }
}