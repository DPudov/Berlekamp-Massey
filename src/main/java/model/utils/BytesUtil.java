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

    public static byte[] packArray(byte[] bytes) {

        BitSet set = new BitSet();
        for (int i = 0; i < bytes.length; i++) {
            set.set(i, bytes[i] == 1);
        }
        return set.toByteArray();
    }

    public static byte[] unpackArray(byte[] bytes) {
        byte[] result = new byte[bytes.length * 8];
        BitSet set = BitSet.valueOf(bytes);
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (set.get(i) ? 1 : 0);
        }
        return result;
    }
}
