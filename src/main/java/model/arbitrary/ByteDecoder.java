package model.arbitrary;

import logic.AbstractDecoder;

/**
 * Created by ${DPudov} on 22.12.2016.
 */
public class ByteDecoder extends AbstractDecoder {
    @Override
    protected byte generateByte() {
        return 0;
    }

    @Override
    protected boolean generateBit() {
        return false;
    }
}
