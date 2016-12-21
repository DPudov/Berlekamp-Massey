package model;

import java.util.BitSet;

/**
 * Special realization of BitSet.
 */
public class BitLine extends BitSet {
    public BitLine(int length) {
        super(length);
    }
    public BitLine(BitSet set){
        super(set.size());
        this.or(set);
    }

    public BitLine(byte[] bytesForBits) {
        this.or(BitSet.valueOf(bytesForBits));
    }

    public boolean xorForTaps(int[] taps) {
        byte buf = 0;
        for (int i : taps) {
            buf += this.get(i) ? 1 : 0;
        }
        return buf % 2 == 1;
    }
}
