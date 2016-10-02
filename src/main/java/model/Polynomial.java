package model;

import java.io.Serializable;

/**
 * Created by ${DPudov} on 19.09.2016.
 */
public class Polynomial implements Serializable {
    private byte[] feedbackArray;
    private short length;
    private byte[] initialState;
    private int linearSpan;

    public Polynomial(byte[] feedbackArray, short length, int linearSpan){
        this.feedbackArray = feedbackArray;
        this.length = length;
        this.linearSpan = linearSpan;
    }

    public byte[] getFeedbackArray() {
        return feedbackArray;
    }

    public int getPeriodOfFunction() {
        int result = 2;
        result = (result << (linearSpan)) - 1;
        return result;
    }

    public short getLength() {
        return length;
    }

    public byte[] getInitialState() {
        return initialState;
    }
    public void setInitialState(byte[] initialState) {
        this.initialState = initialState;
    }
}
