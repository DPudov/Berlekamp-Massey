package model;

import java.util.BitSet;

/**
 * Special realization of BitSet.
 */
public class BitLine extends BitSet {
    public BitLine(int length) {
        super(length);
    }

    public BitLine(BitSet set) {
        super(set.size());
        this.or(set);
    }

    public BitLine(byte[] bytesForBits) {
        this.or(BitSet.valueOf(bytesForBits));
    }

    public boolean xorForTaps(byte[] taps) {
        int tapsLength = taps.length;
        int sum = 0;
        for (int i = 0; i < tapsLength; i++) {
            if (taps[i] == 1) {
                sum += this.get(i) ? 1 : 0;
            }
        }
        return sum % 2 == 1;
    }
}
