package model.file;

import logic.AbstractArchivedFile;
import model.binary.BinaryArchivedFile;
import model.binary.Decoder;
import model.polynomials.Polynomial;
import model.polynomials.PolynomialStorage;

import java.io.*;

/**
 * This class manages files. It can write and read.
 */
public class FileManager {

    private final static String FILE_EXTENSION = ".bma";

    /**
     * Writing archived file.
     * @param file - AbstractArchivedFile
     * @throws IOException
     */
    public void writeArchivedFile(AbstractArchivedFile file) throws IOException {
        String fileDirectory = "";
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileDirectory
                + file.getFileName() + FILE_EXTENSION));
        os.writeObject(file);
        os.close();
    }

    /**
     * Reads archived file from @param path
     * @param path - exactly String of file path
     * @return BinaryArchivedFile that was archived
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public BinaryArchivedFile readFile(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(path));
        BinaryArchivedFile result = (BinaryArchivedFile) is.readObject();
        is.close();
        return result;
    }

    /**
     * Decoding polynomials and writing dearchived file.
     * @param fileName - Previous file name
     * @param storage - Archived polynomials. NOTE: you mustn't unpack them!
     * @throws IOException throws when there is exception in output stream.
     */
    public void writeDearchivedFile(String fileName, PolynomialStorage storage) throws IOException {
        OutputStream outputStream = new FileOutputStream(fileName);
        storage.unpackAll();
        Decoder decoder = new Decoder();
        for (Polynomial p : storage) {
            byte[] bytes = decoder.generateBytesForOne(p);
            outputStream.write(bytes);
        }
        outputStream.close();

    }
}
