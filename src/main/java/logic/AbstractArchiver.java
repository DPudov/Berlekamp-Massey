package logic;

import model.polynomials.PolynomialStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Base archiver.
 */
public abstract class AbstractArchiver {


    protected abstract AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage polynomials);

    protected abstract FileInputStream readFile(String filePath) throws FileNotFoundException;

    public abstract void archiveFile(String filePath) throws IOException;
    public abstract void dearchiveFile(String filePath) throws IOException, ClassNotFoundException;
}
