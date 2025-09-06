package com.cache;

import java.util.BitSet;

public class BloomFilterUtil {
    private final BitSet bitSet = new BitSet(1024);
    private final int[] hashSeeds = {7, 11, 13};

    public void add(String key) {
        for (int seed : hashSeeds) {
            int hash = key.hashCode() * seed & 1023;
            bitSet.set(hash);
        }
    }

    public boolean mightContain(String key) {
        for (int seed : hashSeeds) {
            int hash = key.hashCode() * seed & 1023;
            if (!bitSet.get(hash)) return false;
        }
        return true;
    }
}

