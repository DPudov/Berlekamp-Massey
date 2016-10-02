package model;

import com.sun.istack.internal.NotNull;
import model.utils.BytesUtil;

import java.util.BitSet;

/**
 * Structure and methods of Berlekamp-Massey algorithm
 */
public class BMAlgorithm {
    //parameters
    private int linearSpan;
    private int countN;
    private int m;
    private int d;
    private byte[] buf;
    private byte[] temp;
    private byte[] result;
    private int n;
    private BitSet bits;

    /**
     * Default constructor
     *
     * @param bytesForBits Bytes that will be converted in bits line
     */
    public BMAlgorithm(@NotNull byte[] bytesForBits) {
        n = BytesUtil.getLengthOfBits(bytesForBits);
        bits = BitSet.valueOf(bytesForBits);
        result = new byte[n];
        temp = new byte[n];
        buf = new byte[n];
    }

    /**
     * Main method of class. It returns byte array that describe linear feedback
     * after Berlekamp-Massey algorithm
     *
     * @return byte array that keeps feedback. Positions with 1 are power of
     * polynomial. E.g. linearFeedback[4] = 1 means, that there is x^4.
     */
    public byte[] useBerlekampMassey() {
        //initialization
        buf[0] = 1;
        result[0] = 1;
        countN = 0;
        linearSpan = 0;
        m = -1;

        //algorithm
        for (; countN < n; countN++) {
            d = bits.get(countN) ? 1 : 0;
            for (int i = 1; i <= linearSpan; i++)
                d ^= result[i] & (bits.get(countN - i) ? 1 : 0);
            if (d == 1) {
                System.arraycopy(result, 0, temp, 0, n);
                for (int i = 0; (i + countN - m) < n; i++)
                    result[i + countN - m] ^= buf[i];
                if (linearSpan <= (countN / 2)) {
                    linearSpan = countN + 1 - linearSpan;
                    m = countN;
                    System.arraycopy(temp, 0, buf, 0, n);
                }
            }
        }
        return result;
    }
    //getters

    /**
     * Just a getter of linear span
     * @return linear span of feedback polynomial
     */
    public int getLinearSpan() {
        return linearSpan;
    }
}
