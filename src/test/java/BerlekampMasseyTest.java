import model.BMAlgorithm;
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
    @Test
    public void testUnpack(){
        System.out.println(Arrays.toString(BytesUtil.unpackArray(BytesUtil.packArray(someArrow))));
    }
    @Test
    public void testPack(){
        System.out.println(Arrays.toString(BytesUtil.packArray(someArrow)));
    }
    @Test
    public void bmaForBinaryField(){
        BMAlgorithm algorithm = new BMAlgorithm(arrow);
        System.out.println(Arrays.toString(algorithm.forBinaryField()));

        System.out.println(Arrays.toString(BytesUtil.packArray(algorithm.forBinaryField())));

    }
    @Test
    public void bitSet(){
        BitSet set = BitSet.valueOf(arrow);
        System.out.println(Arrays.toString(set.toByteArray()));
        for (int i = 0; i<set.length(); i++) {
            if (i%8 ==0)
                System.out.print(" ");
            System.out.print(BitSet.valueOf(arrow).get(i)? 1: 0);
        }

    }
}
