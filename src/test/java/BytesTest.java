import org.junit.Test;

import java.util.BitSet;

/**
 * Created by ${DPudov} on 22.09.2016.
 */
public class BytesTest {
    /*   @Test
       public void testPolynomials() {
           byte[] somebytes = {1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1};
           byte[] encoded = BytesUtil.createThePolynom(Encoder.forBinaryField(somebytes));
           System.out.println(BitSet.valueOf(encoded));
           Arrays.sort(encoded);
           BitSet bitSet = new BitSet(encoded[encoded.length - 1]);
           for (int i = encoded[encoded.length - 1]; i >= 0; i--) {
               bitSet.set(i, true);
           }
           System.out.println(bitSet.toString());
       }*/
    @Test
    public void testBitSet() {
        byte[] bytes = {16, 14};
        BitSet set = BitSet.valueOf(bytes);
        System.out.println("Length " + set.length());
        System.out.println("Size " + set.size());
        for (int i = 0; i < 16; i++) {
            System.out.println(String.valueOf(isSet(bytes, (i / 8) * 8 + (7 - (i % 8)))) + String.valueOf(set.get(i)));
        }

    }

    public boolean isSet(byte[] arr, int bit) {
        int index = bit / 8;  // Get the index of the array for the byte with this bit
        int bitPosition = bit % 8;  // Position of this bit in a byte

        return (arr[index] >> bitPosition & 1) == 1;
    }

}
