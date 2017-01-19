package logic;

import model.polynomials.Polynomial;

/**
 * Created by ${DPudov} on 05.12.2016.
 */
public abstract class AbstractDecoder {

    public byte[] decodePolynomial(Polynomial which) {
        byte[] initState = which.getFeedbackArray();
        int resSize = which.getLength();
        byte[] result = new byte[resSize];
        System.arraycopy(initState, 0, result, 0, initState.length);
        for (int i = initState.length; i < result.length; i++) {
            result[i] = generateByte();
        }
        return result;
    }

    protected abstract byte generateByte();

    protected abstract boolean generateBit();

}
