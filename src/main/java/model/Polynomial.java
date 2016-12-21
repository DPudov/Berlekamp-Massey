package model;

import model.utils.BytesUtil;

import java.io.Serializable;

public class Polynomial implements Serializable {
    private byte[] feedbackArray;
    private int length;
    private byte[] initialState;
    private int linearSpan;

    /**
     * General constructor
     *
     * @param feedbackArray  byte array of polynomial
     * @param lengthOfBuffer short int - count of bytes that represented by feedback
     * @param linearSpan     linearSpan of polynomial
     */
    public Polynomial(byte[] feedbackArray, int lengthOfBuffer, int linearSpan) {
        this.feedbackArray = feedbackArray;
        this.length = lengthOfBuffer;
        this.linearSpan = linearSpan;
    }

    public byte[] getFeedbackArray() {
        return feedbackArray;
    }

    public int getLength() {
        return length;
    }

    public byte[] getInitialState() {
        return initialState;
    }

    public void setInitialState(byte[] initialState) {
        this.initialState = initialState;
    }

    public int getLinearSpan() {
        return linearSpan;
    }

    // pack e.g.{0, 1, 0, 1} into {5}
    public void pack() {
        initialState = BytesUtil.packArray(initialState);
        feedbackArray = BytesUtil.packArray(feedbackArray);
    }

    public void unpack() {
        feedbackArray = BytesUtil.unpackArray(feedbackArray);

    }
}
