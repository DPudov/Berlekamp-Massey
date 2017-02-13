package model.binary;

import logic.AbstractArchivedFile;
import logic.AbstractArchiver;
import model.file.FileManager;
import model.polynomials.PolynomialStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Writing and reading files.
 */
public class BinaryArchiver extends AbstractArchiver {


    @Override
    protected AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage storage) {
        return new BinaryArchivedFile(fileName, fileExtension, storage);
    }

    @Override
    protected FileInputStream readFile(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }

    @Override
    public void archiveFile(String filePath) throws IOException {


        Encoder encoder = new Encoder();
        File file = new File(filePath);
        PolynomialStorage polynomials = encoder.encode(file.getPath());
        AbstractArchivedFile archivedFile = createFile(file.getName(), getFileExtension(file), polynomials);
        FileManager manager = new FileManager();
        manager.writeArchivedFile(archivedFile);


    }

    @Override
    public void dearchiveFile(String filePath) throws IOException, ClassNotFoundException {
        FileManager manager = new FileManager();
        BinaryArchivedFile archivedFile = manager.readFile(filePath);
        manager.writeDearchivedFile(archivedFile.getFileName(), archivedFile.getPolynomialStorage());
    }


    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

}
