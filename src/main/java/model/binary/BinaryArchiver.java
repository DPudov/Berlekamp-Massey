package model.binary;

import logic.AbstractArchivedFile;
import logic.AbstractArchiver;
import model.file.ArchiveModes;
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
    protected AbstractArchivedFile createFile(String fileName, String fileExtension, PolynomialStorage storage, int archiveMode) {
        return new BinaryArchivedFile(fileName, fileExtension, storage, archiveMode);
    }

    @Override
    protected FileInputStream readFile(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }

    @Override
    public void archiveFile(String filePath, int archiveMode) throws IOException {
        switch (archiveMode) {
            case ArchiveModes.MODE_BINARY:
                Encoder encoder = new Encoder();
                File file = new File(filePath);
                PolynomialStorage polynomials = encoder.encode(file.getPath(), 512, ArchiveModes.MODE_BINARY);
                polynomials.packAll();
                AbstractArchivedFile archivedFile = createFile(file.getName(), getFileExtension(file), polynomials, ArchiveModes.MODE_BINARY);
                FileManager manager = new FileManager();
                manager.writeArchivedFile(archivedFile);

        }

    }

    @Override
    public void dearchiveFile(String filePath) throws IOException, ClassNotFoundException {
        FileManager manager = new FileManager();
        BinaryArchivedFile archivedFile = manager.readFile(filePath);
        manager.writeDearchivedFile(archivedFile.getFileName(), archivedFile.getPolynomialStorage());
    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

}
