import model.BMAlgorithm;
import model.binary.Decoder;
import model.polynomials.Polynomial;
import model.utils.BytesUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by ${DPudov} on 15.11.2016.
 */
public class BerlekampMasseyTest {
    public final static byte[] arrow = {25, 1, 5, 21, 85, 127, -128};
    public final static byte[] someArrow = {1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0};
    final static String s = "HELLO, WORLD!";

    @Test
    public void testArchiver() {
        byte[] arr = s.getBytes();
        System.out.println("Before archiving bytes:\n" + Arrays.toString(arr));
        System.out.println("Init length = " + arr.length);
        BMAlgorithm algorithm = new BMAlgorithm(arr);
        byte[] feedBack = BytesUtil.packArray(algorithm.forBinaryField());
        System.out.println("Feedback = " + Arrays.toString(feedBack));
        Polynomial polynomial = new Polynomial(feedBack, arr.length, algorithm.getLinearSpan());
        System.out.println("Init state\n" + Arrays.toString(Arrays.copyOfRange(arr, 0, feedBack.length - 1)));
        polynomial.setInitState(Arrays.copyOfRange(arr, 0, feedBack.length - 1));

        System.out.println("After achiving bytes:\n" + Arrays.toString(polynomial.getFeedbackArray()));
        polynomial.unpack();
        Decoder decoder = new Decoder();
        System.out.println("Polynomial length = " + polynomial.getLength());
        byte[] som = decoder.generateBytesForOne(polynomial);
        byte[] etal = BytesUtil.unpackArray(arr);
        byte[] real = BytesUtil.unpackArray(som);
        int c = 0;
        for (int i = 0; i< etal.length; i++){
            if (etal[i] != real[i]){
                c++;
            }
        }
        System.out.println(c);
        System.out.println("After dearchiving:\n" + Arrays.toString(BytesUtil.unpackArray(som)));
        System.out.println(Arrays.toString(BytesUtil.unpackArray(arr)));
        System.out.println("Final length = " + som.length);
        System.out.println("Your string is " + new String(som));

    }


    @Test
    public void testUnpack() {
        System.out.println(Arrays.toString(BytesUtil.unpackArray(BytesUtil.packArray(someArrow))));
    }

    @Test
    public void testPack() {
        System.out.println(Arrays.toString(BytesUtil.packArray(someArrow)));
    }

    @Test
    public void bmaForBinaryField() {
        BMAlgorithm algorithm = new BMAlgorithm(arrow);
        System.out.println(Arrays.toString(algorithm.forBinaryField()));

        System.out.println(Arrays.toString(BytesUtil.packArray(algorithm.forBinaryField())));

    }

    @Test
    public void bitSet() {
        BitSet set = BitSet.valueOf(arrow);
        System.out.println(Arrays.toString(set.toByteArray()));
        for (int i = 0; i < set.length(); i++) {
            if (i % 8 == 0)
                System.out.print(" ");
            System.out.print(BitSet.valueOf(arrow).get(i) ? 1 : 0);
        }

    }


    @Test
    public void testLSFR() {
        StringBuilder lsfr = new StringBuilder("1000");
        for (int i = 0; i < 10; i++) {
            System.out.println("Time " + i + ":\n " + lsfr.toString());
            boolean b = (Integer.parseInt(String.valueOf(lsfr.charAt(2))) + Integer.parseInt(String.valueOf(lsfr.charAt(3)))) % 2 == 1;
            lsfr.insert(0, b ? "1" : "0");
        }
    }


}
