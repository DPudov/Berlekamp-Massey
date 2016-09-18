package model;

/**
 * Created by ${DPudov} on 17.09.2016.
 */
public class PolynomialStorage {
    private int linearSpan, N;
    private byte[] cArray;

    public PolynomialStorage(int linearSpan, int n, byte[] cArray) {
        this.linearSpan = linearSpan;
        N = n;
        this.cArray = cArray;
    }

    public int getLinearSpan() {
        return linearSpan;
    }

    public void setLinearSpan(int linearSpan) {
        this.linearSpan = linearSpan;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public byte[] getcArray() {
        return cArray;
    }

    public void setcArray(byte[] cArray) {
        this.cArray = cArray;
    }
}
