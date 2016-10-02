import model.Encoder;
import model.FileManager;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Date d1 = new Date();

        try {
            Encoder.getInstance().encode("C:\\Users\\DPudov\\Git\\ReleaseNotes.html", (short) 256);
            FileManager.getInstance().writePolynomialToFile("Somefile");
            FileManager.getInstance().getPolynomialsFromFile("C:\\Users\\мвидео\\IdeaProjects\\Berlekamp-Massey\\Somefile.bma");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Date d2 = new Date();

        System.out.println((d2.getTime() - d1.getTime()) + " success");
    }


}
