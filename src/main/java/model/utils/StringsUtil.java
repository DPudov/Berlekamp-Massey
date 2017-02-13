package model.utils;

/**
 * Class for bit's String representation
 */
public class StringsUtil {
    private final static byte ONE = 1;
    private final static byte ZERO = 0;

    public static String bitsArrayToString(boolean[] bits) {
        StringBuilder builder = new StringBuilder();
        boolean wasOne = false;
        for (byte i = 0; i < bits.length; i++) {
            if (bits[i]) {
                builder.append(ONE);
                wasOne = true;
            } else if (wasOne || i == bits.length - 1)
                builder.append(ZERO);
        }
        return builder.toString();
    }


}
