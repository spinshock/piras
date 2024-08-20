package com.spinshock.piras.util;

import java.util.LinkedList;

public class FixedSizeDoubleQueue extends LinkedList<Double> {
    private final int maxSize;

    public FixedSizeDoubleQueue(int size) {
        this.maxSize = size;
    }

    @Override
    public boolean add(Double e) {
        if (size() >= maxSize) {
            removeFirst();
        }
        return super.add(e);
    }
}
