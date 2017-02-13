package model;


import model.utils.BytesUtil;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Structure and methods of Berlekamp-Massey algorithm. One object - one polynomial.
 */
public class BMAlgorithm {
    private static final Logger logger = Logger.getLogger(BMAlgorithm.class.getName());

    //parameters
    private int linearSpan;
    private byte[] buf;
    private byte[] temp;
    private byte[] result;
    private final int n;
    private final byte[] initBits;

    /**
     * Default constructor
     * @param bytesForBits Bytes that will be converted in bits line
     */
    public BMAlgorithm(byte[] bytesForBits) {
        initBits = BytesUtil.bitsValueOf(bytesForBits);
        logger.info(Arrays.toString(initBits));
        n = bytesForBits.length * 8;
        if (n != 0) {
            result = new byte[n];
            temp = new byte[n];
            buf = new byte[n];
        }
    }


    /**
     * Main method of class. It returns byte array that describe linear feedback
     * after Berlekamp-Massey algorithm for binary field.
     *
     * @return NOTE! Byte array contains NUMBERS FROM -128 to 127! Before reading find positions with 1 or 0!!!
     * Byte array that keeps feedback. Positions with 1 are power of
     * polynomial. E.g. linearFeedback[4] = 1 means, that there is x^4.
     */
    public byte[] forBinaryField() {
        //initialization
        if (buf != null && result != null) {
            buf[0] = 1;
            result[0] = 1;
            int countN = 0;
            linearSpan = 0;
            int m = -1;

            //algorithm
            for (; countN < n; countN++) {
                int d = initBits[countN];
                for (int i = 1; i <= linearSpan; i++)
                    d ^= result[i] & initBits[countN - i];
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
        }
        logger.info("Linear span of this polynomial = " + linearSpan);
        logger.info("Result array: " + Arrays.toString(Arrays.copyOfRange(result, 0, linearSpan)));
        result = (Arrays.copyOfRange(result, 0, linearSpan-1));
        return result;
    }

    /**
     * Get the linear span of this polynomial. It's always the highest power in the polynomial.
     * E.g. For x^4 + x^2 + x + 1 linear span is 4.
     *
     * @return linear span of feedback polynomial
     */
    public int getLinearSpan() {
        return linearSpan;
    }
}
