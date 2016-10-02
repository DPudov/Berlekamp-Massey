import model.utils.BytesUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * Created by ${DPudov} on 22.09.2016.
 */
public class BytesTest {
 /*   @Test
    public void testPolynomials() {
        byte[] somebytes = {1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
        byte[] encoded = BytesUtil.createThePolynom(Encoder.useBerlekampMassey(somebytes));
        System.out.println(BitSet.valueOf(encoded));
        Arrays.sort(encoded);
        BitSet bitSet = new BitSet(encoded[encoded.length - 1]);
        for (int i = encoded[encoded.length - 1]; i >= 0; i--) {
            bitSet.set(i, true);
        }
        System.out.println(bitSet.toString());
    }*/
    @Test
    public void testBitSet(){
        byte[] bytes = {32, 21, 24, 54, 43};
        BitSet bitSet = BitSet.valueOf(bytes);
        System.out.println(BytesUtil.getLengthOfBits(bytes));
        System.out.println(BytesUtil.convertBytesToBits(bytes).length());
        System.out.println(Arrays.toString(bitSet.toByteArray()));
    }


}
