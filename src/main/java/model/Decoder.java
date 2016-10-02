package model;

import model.utils.BytesUtil;

import java.util.BitSet;

/**
 * Created by ${DPudov} on 13.09.2016.
 */
public class Decoder {
    private static Decoder ourInstance = new Decoder();

    public static Decoder getInstance() {
        return ourInstance;
    }

    private Decoder() {
    }


    /**
     * Method that produces binary sequence from polynomial.
     *
     * @return bytes from BitSet(binary sequence)
     */
    public byte[] generateBinarySequenceForOne(Polynomial polynomial) {
        short length = polynomial.getLength();
        BitSet initialState = BitSet.valueOf(polynomial.getInitialState());
        BitSet result = new BitSet(length);
        BitSet feedback = bitSetFromBinaryBytes(polynomial.getFeedbackArray());
        int period = polynomial.getPeriodOfFunction();
        //If the period is less than the length of binary sequence
        //we just copy sequence period times
        if (period < length) {
            int wholeCount = length / period;
            BitSet periodical = new BitSet(period);
            periodical.or(initialState);
            for (int i = initialState.length() - 1; i < period; i++) {
                generateNextBit(initialState.length() + i, periodical);
            }
            for (int i = period; i < length; i++) {
                result.set(period + i, periodical.get((i % period)));
            }

        } else {
            result.or(initialState);
            for (int i = 0; i < length; i++) {
                generateNextBit(initialState.length() + i, result);
            }
        }


        return result.toByteArray();
    }

    private void generateNextBit(int position, BitSet periodicalSet) {


    }

    private BitSet bitSetFromBinaryBytes(byte[] bytes) {
        String bits = BytesUtil.convertBytesToBits(bytes);
        BitSet res = new BitSet(bits.length());
        for (int i = 0; i < bits.length(); i++) {
            res.set(i, bits.charAt(i) == '1');
        }
        return res;
    }
}
