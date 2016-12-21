package model;

import com.sun.istack.internal.NotNull;

import java.util.BitSet;

/**
 * Structure and methods of Berlekamp-Massey algorithm
 */
public class BMAlgorithm {
    //parameters
    private int linearSpan;
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
        bits = BitSet.valueOf(bytesForBits);
        n = bits.size();
        if (n != 0) {
            result = new byte[n];
            temp = new byte[n];
            buf = new byte[n];
        }
    }


    /**
     * Main method of class. It returns byte array that describe linear feedback
     * after Berlekamp-Massey algorithm for binary field
     *
     * @return NOTE! Byte array contains NUMBERS FROM -128 to 127!!! Before reading find positions with 1 or 0!!!
     * Byte array that keeps feedback. Positions with 1 are power of
     * polynomial. E.g. linearFeedback[4] = 1 means, that there is x^4.
     */
    public byte[] forBinaryField() {
        //initialization
        buf[0] = 1;
        result[0] = 1;
        int countN = 0;
        linearSpan = 0;
        int m = -1;

        //algorithm
        for (; countN < n; countN++) {
            int d = bits.get(countN) ? 1 : 0;
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

    /**
     * use for field 256
     *
     * @return feedback function
     */
    public byte[] forByteField() {

        return null;
    }

    public byte[] forArbitraryField(int field) {
        return null;
    }
    //getters

    /**
     * Just a getter of linear span
     *
     * @return linear span of feedback polynomial
     */
    int getLinearSpan() {
        return linearSpan;
    }
}
