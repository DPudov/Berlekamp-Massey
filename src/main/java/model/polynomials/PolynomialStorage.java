package model.polynomials;

import java.io.Serializable;
import java.util.ArrayList;


public class PolynomialStorage extends ArrayList<Polynomial> implements Serializable {
    private int field;
    public void clearData() {
        this.clear();
    }

    public void packAll() {
        for (Polynomial p : this) {
            p.pack();
        }
    }
    public void unpackAll(){
        for (Polynomial p: this){
            p.unpack();
        }
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
