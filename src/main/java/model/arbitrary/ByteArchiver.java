package model.arbitrary;

import logic.AbstractArchivedFile;
import logic.AbstractArchiver;
import model.polynomials.PolynomialStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ${DPudov} on 22.12.2016.
 */
public class ByteArchiver extends AbstractArchiver {
    @Override
    protected AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage polynomials, int mode) {
        return null;
    }

    @Override
    protected FileInputStream readFile(String filePath) throws FileNotFoundException {
        return null;
    }

    @Override
    public void archiveFile(String filePath, int archiveMode) throws IOException {

    }

    @Override
    public void dearchiveFile(String filePath) throws IOException, ClassNotFoundException {

    }
}
