import org.junit.Test;

import java.util.BitSet;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ${DPudov} on 12.02.2017.
 */
public class BitSetTest {
    @Test
    public void bitSetFromByte(){
        BitSet bits = new BitSet(8);
        byte[] bytes = new byte[]{23};
        byte b = bytes[0];
        for (int i = 0; i < 8; i++)
        {
            bits.set(i, (b & 1) == 1);
            b >>= 1;
        }
        BitSet other = BitSet.valueOf(bytes);
        assertEquals(bits, other);
    }
}
