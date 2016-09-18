package model.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class BytesUtil {

    public static byte[] getFileBytes(String fileName) {
        byte[] result = new byte[0];
        try {
            InputStream fileStream = new FileInputStream(fileName);
            result = new byte[fileStream.available()];
            int countOfBytes = fileStream.read(result, 0, fileStream.available());
            fileStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
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
}
