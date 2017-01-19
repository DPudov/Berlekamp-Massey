package model.file;

import logic.AbstractArchivedFile;
import model.binary.BinaryArchivedFile;
import model.binary.Decoder;
import model.polynomials.Polynomial;
import model.polynomials.PolynomialStorage;

import java.io.*;

/**
 * Created by ${DPudov} on 21.09.2016.
 */
public class FileManager {

    private String fileDirectory = "";
    private final static String FILE_EXTENSION = ".bma";

    public void writeArchivedFile(AbstractArchivedFile file) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileDirectory
                + file.getFileName() + FILE_EXTENSION));
        os.writeObject(file);
        os.close();
    }

    public BinaryArchivedFile readFile(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(path));
        BinaryArchivedFile result = (BinaryArchivedFile) is.readObject();
        is.close();
        return result;
    }

    public void writeDearchivedFile(String fileName, PolynomialStorage storage) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);
        storage.unpackAll();
        Decoder decoder = new Decoder();
        for (Polynomial p: storage){
            byte[] bytes = decoder.generateBytesForOne(p);
            outputStream.write(bytes);
        }
        outputStream.close();

    }
    /*public void writePolynomialToFile(String filename, PolynomialStorage polynomials) throws IOException {
        //open stream
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileDirectory + filename + FILE_EXTENSION));
        polynomials.packAll();
        os.writeObject(polynomials);
        //close stream
        os.close();

        //clear polynomial storage
        polynomials.clearData();
    }

    public PolynomialStorage getPolynomialsFromFile(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath));
        PolynomialStorage result = (PolynomialStorage) is.readObject();
        result.unpackAll();
        is.close();
        return result;
    }

    public void writeDearchivedFile(String filePath, PolynomialStorage polynomials) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath, true);
        Decoder decoder = new Decoder();
        for (Polynomial p : polynomials) {
            byte[] b = decoder.generateBytesForOne(p);
            outputStream.write(b);
        }
        outputStream.close();
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }*/
}
