import model.binary.BinaryArchiver;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This class is useless, because it's only needed for some tests.
 */
public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Date d1 = new Date();
        Date date = null;
        BinaryArchiver binaryArchiver = new BinaryArchiver();
        try {
            binaryArchiver.archiveFile("C:\\Users\\DPudov\\Git\\ReleaseNotes.html");
            date = new Date();
            binaryArchiver.dearchiveFile("C:\\Users\\мвидео\\IdeaProjects\\Berlekamp-Massey\\ReleaseNotes.html.bma");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ignored) {
        }
       /* Encoder encoder = new Encoder();
        FileManager fileManager = new FileManager();
        try {
            PolynomialStorage polynomials = encoder.encode("C:\\Users\\мвидео\\Pictures\\wallp_may_2560x1600.jpg",  256, ArchiveModes.MODE_BINARY);
            fileManager.writePolynomialToFile("Wall1",polynomials);
            PolynomialStorage polynomials1 = fileManager.getPolynomialsFromFile("Wall1.bma");
            fileManager.writeDearchivedFile("Wall.jpg", polynomials1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        System.out.println(((date != null ? date.getTime() : 0) - d1.getTime()) + " milliseconds");
    }


}
