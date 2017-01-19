import model.binary.BinaryArchiver;
import model.file.ArchiveModes;

import java.io.IOException;
import java.util.Date;

/**
 * Created by ${DPudov} on 11.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date date = null;
        BinaryArchiver binaryArchiver = new BinaryArchiver();
        try {
            binaryArchiver.archiveFile("C:\\Users\\DPudov\\Git\\ReleaseNotes.html", ArchiveModes.MODE_BINARY);
            date = new Date();
            binaryArchiver.dearchiveFile("C:\\Users\\мвидео\\IdeaProjects\\Berlekamp-Massey\\ReleaseNotes.html.bma");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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

        Date d2 = new Date();

        System.out.println((date.getTime() - d1.getTime()) + " milliseconds");
    }


}
