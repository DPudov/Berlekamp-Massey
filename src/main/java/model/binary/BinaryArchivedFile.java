package model.binary;

import logic.AbstractArchivedFile;
import model.polynomials.PolynomialStorage;

/**
 * File for b
 */
public class BinaryArchivedFile extends AbstractArchivedFile {

    private final PolynomialStorage polynomialStorage;

    public BinaryArchivedFile(String fileName, String previousExtension, PolynomialStorage polynomialStorage) {
        this.polynomialStorage = polynomialStorage;
        this.fileName = fileName;
        this.fileExtension = previousExtension;
        this.archiveMode = archiveMode;
    }

    public PolynomialStorage getPolynomialStorage() {
        return polynomialStorage;
    }


}
