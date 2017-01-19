package model.binary;

import model.BitLine;
import model.polynomials.Polynomial;

import java.util.BitSet;

/**
 * Class for a dearchivation.
 */
public class Decoder {

    /**
     * Method that produces binary sequence from polynomial.
     *
     * @return bytes from BitSet(binary sequence)
     */
    public byte[] generateBytesForOne(Polynomial polynomial) {
        //initialization
        int length = polynomial.getLength();
        byte[] result = null;
        byte[] initState = polynomial.getInitState();
        BitSet set = BitSet.valueOf(initState);
        int[] taps = getTaps(polynomial);
        int q = set.length();
        for (int i = q; i < length * 8 - 2; i++) {
            int sum = 0;
            for (int b : taps) {
                sum += set.get(b) ? 1 : 0;
            }
            boolean next = sum % 2 == 1;
            //move right
            //set first bit
            set = moveRightAndSet(set, next);
        }
        /*BitLine bitLine = new BitLine(BitSet.valueOf(initState));
        for (int i = bitLine.length(); i < length * 8; i++) {

            bitLine.set(i,
                    generateNextBit(feedback, new BitLine(bitLine.get(i - initState.length, i - 1))));

        }*/
        result = set.toByteArray();
        return result;
    }

    private BitSet moveRightAndSet(BitSet set, boolean first) {
        BitSet result = new BitSet();
        result.set(0, first);
        for (int i = 0; i < set.length(); i++) {
            result.set(i + 1, set.get(i));
        }
        return result;
    }

    private boolean generateNextBit(byte[] taps, BitLine previousPeriod) {
        return previousPeriod.xorForTaps(taps);
    }

    /**
     * Period of function is 2^N-1.
     * N - linear span of function
     *
     * @param linearSpan represents linearSpan for current function.
     * @return period.
     */
    private int getPeriodOfFunction(int linearSpan) {
        return (1 << linearSpan) - 1;
    }

    private int[] getTaps(Polynomial polynomial) {
        byte[] feedback = polynomial.getFeedbackArray();
        int counter = 0;
        for (int i = 0; i < feedback.length; i++) {
            if (feedback[i] == 1) {
                counter++;
            }
        }
        int[] taps = new int[counter];
        int c = 0;
        for (int i = 0; i < feedback.length; i++) {
            if (feedback[i] == 1) {
                taps[c] = i;
                c++;
            }
        }
        return taps;
    }
}
