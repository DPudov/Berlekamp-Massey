package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ${DPudov} on 19.09.2016.
 */
public class PolynomialStorage extends ArrayList<Polynomial> implements Serializable{
    private static PolynomialStorage ourInstance = new PolynomialStorage();

    public static PolynomialStorage getInstance() {
        return ourInstance;
    }

    public PolynomialStorage() {
    }
    public void clearData(){
        ourInstance.clear();
    }
}
