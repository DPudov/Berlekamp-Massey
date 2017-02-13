package model.binary;

import model.BitLine;
import model.polynomials.Polynomial;

import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Logger;

/**
 * Class for a dearchivation.
 */
public class Decoder {
    private static final Logger logger = Logger.getLogger(Decoder.class.getName());

    /**
     * Method that produces binary sequence from polynomial.
     *
     * @return bytes from BitSet(binary sequence)
     */
    public byte[] generateBytesForOne(Polynomial polynomial) {
        //initialization
        int length = polynomial.getLength();
        byte[] result;
        byte[] initState = polynomial.getInitState();
        logger.info("Init state " + Arrays.toString(initState));
        BitSet set = BitSet.valueOf(initState);
        logger.info("Init bit set " + set);
        int[] taps = getTaps(polynomial);
        int q = set.length();
        int counter = 0;

        while (q < length * 8) {

            boolean next = generateNextBit(taps, set, counter);
            counter++;
            //move right
            //set first bit
            logger.info("BitSet" + set);
            set.set(q, next);
            q++;
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

    private boolean generateNextBit(int[] taps, BitSet currentPos, int counter) {
        int sum = 0;
        for (int tap : taps) {
            sum += currentPos.get(tap + counter) ? 1 : 0;
        }
        return sum % 2 == 1;
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
        for (int i = 0; i < feedback.length - 1; i++) {
            if (feedback[i] == 1) {
                counter++;
            }
        }
        int[] taps = new int[counter];
        int c = 0;
        for (int i = 0; i < feedback.length - 1; i++) {
            if (feedback[i] == 1) {
                taps[c] = i;
                c++;
            }
        }
        logger.info("Taps +" + Arrays.toString(taps));
        return taps;
    }
}
