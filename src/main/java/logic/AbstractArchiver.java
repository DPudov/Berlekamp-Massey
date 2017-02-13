package logic;

import model.polynomials.PolynomialStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Base archiver.
 */
public abstract class AbstractArchiver {


    protected abstract AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage polynomials, int mode);

    protected abstract FileInputStream readFile(String filePath) throws FileNotFoundException;

    public abstract void archiveFile(String filePath, int archiveMode) throws IOException;
    public abstract void dearchiveFile(String filePath) throws IOException, ClassNotFoundException;
}
