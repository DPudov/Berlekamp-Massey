package model;

import java.io.File;

/**
 * Created by ${DPudov} on 13.09.2016.
 */
public class Decoder {
    private static Decoder ourInstance = new Decoder();

    public static Decoder getInstance() {
        return ourInstance;
    }

    private Decoder() {
    }
    public void decode(File file){
        //TODO: realization

    }
}
