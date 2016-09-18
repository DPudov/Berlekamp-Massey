package model;

import model.utils.BytesUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by ${DPudov} on 13.09.2016.
 */
public class Encoder {
    private static Encoder ourInstance = new Encoder();

    public static Encoder getInstance() {
        return ourInstance;
    }

    private Encoder() {
    }

    public byte[] encode(String filePath) {
        byte[] bytes = BytesUtil.getFileBytes(filePath);
        byte[] resultBytes = useBerlekampMassey(bytes);

        return resultBytes;
    }

    public static byte[] useBerlekampMassey(byte[] bytes) {
        //объявление переменных
        int linearSpan;
        int countN;
        int m;
        int d;
        String bits = BytesUtil.convertBytesToBits(bytes);
        int n = bits.length();
        byte[] result = new byte[n];
        byte[] temp = new byte[n];
        byte[] buf = new byte[n];
//инициализация
        buf[0] = 1;
        result[0] = 1;
        countN = 0;
        linearSpan = 0;
        m = -1;
        // сам алгоритм
        for (; countN < n; countN++) {
            d = Byte.parseByte(String.valueOf(bits.charAt(countN)));
            for (int i = 1; i <= linearSpan; i++)
                d ^= result[i] & Byte.parseByte(String.valueOf(bits.charAt(countN-i)));
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
        System.out.println("Linear span = " + linearSpan);
        return result;
    }

    public static String getFunctionFeedback(byte[] bytesC) {
        int j = 0;
        StringBuilder builder = new StringBuilder();
        for (byte b : bytesC) {
            if (j < bytesC.length - 1) {
                if (b == 1) {
                    System.out.print(b + "x^(" + j + ") + ");
                    builder.append(b).append("x^(").append(j).append(") + ");
                }
            } else {
                if (b == 1) {
                    System.out.print(b + "x^(" + j + ")");
                    builder.append(b).append("x^(").append(j).append(")");
                }
            }
            j++;
        }
        System.out.println();
        return builder.toString();
    }

    private static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }
}
