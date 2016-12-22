package model.file;

import logic.AbstractArchivedFile;
import model.polynomials.PolynomialStorage;

/**
 * File for b
 */
public class BinaryArchivedFile extends AbstractArchivedFile {

    private PolynomialStorage polynomialStorage;

    public BinaryArchivedFile(String fileName, String previousExtension, PolynomialStorage polynomialStorage, int archiveMode) {
        this.polynomialStorage = polynomialStorage;
        this.fileName = fileName;
        this.fileExtension = previousExtension;
        this.archiveMode = archiveMode;
    }

    public PolynomialStorage getPolynomialStorage() {
        return polynomialStorage;
    }


}
