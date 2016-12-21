import model.Constants;
import model.Encoder;
import model.FileManager;
import model.PolynomialStorage;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Date d1 = new Date();
        Encoder encoder = new Encoder();
        FileManager fileManager = new FileManager();
        try {
            PolynomialStorage polynomials = encoder.encode("C:\\Users\\мвидео\\Pictures\\wallp_may_2560x1600.jpg",  256, Constants.FIELD_BINARY);
            fileManager.writePolynomialToFile("Wall1",polynomials);
            PolynomialStorage polynomials1 = fileManager.getPolynomialsFromFile("Wall1.bma");
            fileManager.writeDearchivedFile("Wall.jpg", polynomials1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Date d2 = new Date();

        System.out.println((d2.getTime() - d1.getTime()) + " success");
    }


}
