package model.utils;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class BytesUtil {
    public static byte[] createThePolynom(byte[] encoded) {
        Arrays.sort(encoded);
        BitSet bitSet = new BitSet(encoded[encoded.length - 1]);
        for (int i = encoded[encoded.length - 1]; i >= 0; i--) {
            bitSet.set(i, true);
        }
        return bitSet.toByteArray();
    }

    public static String convertByteToBits(byte b) {
        boolean[] bits = new boolean[8];
        for (int i = bits.length - 1; i >= 0; i--) {
            bits[i] = (b % 2 == 1);
            b = (byte) (b / 2);
        }

        return StringsUtil.bitsArrayToString(bits);
    }

    public static String convertBytesToBits(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(convertByteToBits(b));
        }
        return builder.toString();
    }

    public static int getLengthOfBits(byte[] bytes) {
        return bytes.length * 8;
    }
}
