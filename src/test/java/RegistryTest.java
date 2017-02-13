import model.BMAlgorithm;
import model.utils.StringsUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

import static junit.framework.TestCase.assertEquals;
import static model.utils.BytesUtil.convertBytesToBits;

/**
 * Created by ${DPudov} on 09.09.2016.
 */
public class RegistryTest {
    private byte[] b, c, t, s;
    private int N, L, m;

    @Test
    public void testAlg() {
        int sequenceLength = 32;
        b = new byte[sequenceLength];
        c = new byte[sequenceLength];
        t = new byte[sequenceLength];
        s = new byte[sequenceLength];
        for (int i = 0; i < sequenceLength; i++)
            b[i] = c[i] = t[i] = 0;
        b[0] = c[0] = 1;
        N = L = 0;
        m = -1;
    }

    @Test
    public void runTest() {
        int d;
        testAlg();
        while (N < s.length) {
            d = 0;
            for (int i = 0; i <= L; i++)
                d += s[N - i] * c[i];
            d = d % 2;
            if (d != 0) {
                t = c.clone();
                for (int i = 0; i <= s.length + m - 1 - N; i++)
                    c[N - m + i] = (byte) (c[N - m + i] ^ b[i]);
                if (L <= (N / 2)) {
                    L = N + 1 - L;
                    m = N;
                    b = t.clone();
                }
            }
            N++;
        }

    }

    @Test
    public void berMastest()

    {
        byte[] array = {0b1101101} ;
        byte[] array1 = {0, 0, 0, 0, 0};
        byte[] array2 = {0b001000001, 0b0001001101};
        byte[] array3 = {0, 0, 1, 0, 0, 1, 0, 0, 1};
        byte[] array4 = {1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0};

        System.out.println(Arrays.toString(new BMAlgorithm(array).forBinaryField()));
        System.out.println(Arrays.toString(new BMAlgorithm(array1).forBinaryField()));
        System.out.println(Arrays.toString(new BMAlgorithm(array2).forBinaryField()));
//        getFunctionFeedback(forBinaryField(array2));
//        getFunctionFeedback(forBinaryField(array3));
//        getFunctionFeedback(forBinaryField(array4));

    }

    @Test
    public void decoderExpert() {
        byte[] array = {0b1010101, 0b01010101, 0b01010101, 0b01010101};
        BMAlgorithm algorithm = new BMAlgorithm(array);
        byte[] feedback = algorithm.forBinaryField();
        byte[] result = new byte[array.length];
        int[] taps = {0};
        byte[] initState = Arrays.copyOfRange(array, 0, feedback.length - 1);
        BitSet init = BitSet.valueOf(initState);
        int q = 0;
        while (init.length() < array.length * 8) {
            boolean next = init.get(taps[0]);
            init.set(init.length(), next);
            q++;
        }
        result = init.toByteArray();
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testConvert() {
        byte[] array1 = {0, 1, 0, 1};
        byte[] array2 = {0, 1, 0, 1, 0, 1};
        byte[] array3 = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
        byte[] array4 = {0, 1, 0, 1, 0};
        assertEquals(convertBytesToBits(array1), "0101");
        assertEquals(convertBytesToBits(array2), "010101");
        assertEquals(convertBytesToBits(array3), "01010101010101");
        assertEquals(convertBytesToBits(array4), "01010");

    }

    @Test
    public void xorY() {
        BitSet f = BitSet.valueOf(new byte[]{0, 41, 37});
        f.flip(0);
        System.out.println();

    }

    @Test
    public void booleanToString() {
        boolean[] booleen = {false, false, false, true, false, true, false};
        assertEquals(StringsUtil.bitsArrayToString(booleen), "1010");
    }


}
