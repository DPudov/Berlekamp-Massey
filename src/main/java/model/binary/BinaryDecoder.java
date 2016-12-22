package model.binary;

import logic.AbstractDecoder;

import java.util.BitSet;

/**
 * Created by ${DPudov} on 05.12.2016.
 */
public class BinaryDecoder extends AbstractDecoder {
    @Override
    protected byte generateByte() {
        BitSet set = new BitSet(8);
        for (int i = 0; i < 8; i++) {
            set.set(i, generateBit());
        }
        return set.toByteArray()[0];
    }

    @Override
    protected boolean generateBit() {

        return false;
    }
}
