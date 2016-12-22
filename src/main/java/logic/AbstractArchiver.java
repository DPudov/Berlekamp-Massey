package logic;

import model.polynomials.PolynomialStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ${DPudov} on 15.11.2016.
 */
public abstract class AbstractArchiver {


    protected abstract AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage polynomials, int mode);

    protected abstract FileInputStream readFile(String filePath) throws FileNotFoundException;

    protected abstract void archiveFile(String filePath,int archiveMode) throws IOException;
    protected abstract AbstractArchivedFile dearchiveFile(String filePath) throws IOException, ClassNotFoundException;
}
