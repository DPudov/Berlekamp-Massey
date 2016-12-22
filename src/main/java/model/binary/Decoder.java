package model.binary;

import model.BitLine;
import model.polynomials.Polynomial;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
        byte[] initState = polynomial.getInitialState();
        BitLine bitLine = new BitLine(BitSet.valueOf(initState));
        for (int i = 0; i < length * 8; i++) {
            bitLine.set(i + bitLine.toByteArray().length * 8,
                    generateNextBit(polynomial, bitLine));

        }
        result = bitLine.toByteArray();
        return result;
    }

    private boolean generateNextBit(Polynomial polynomial, BitLine previousPeriod) {
        return previousPeriod.xorForTaps(getTaps(polynomial));
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
        Set<Integer> taps = new HashSet<>();
        for (int i : polynomial.getFeedbackArray()) {
            if (i != 0)
                taps.add(i);
        }
        int[] res = new int[taps.size()];
        Iterator<Integer> iterator = taps.iterator();
        for (int i = 0; i < taps.size(); i++) {
            res[i] = iterator.next();
        }
        return res;
    }
}
