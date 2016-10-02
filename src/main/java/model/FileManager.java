package model;

import java.io.*;

/**
 * Created by ${DPudov} on 21.09.2016.
 */
public class FileManager {
    private static FileManager ourInstance = new FileManager();
    private String fileDirectory = "";
    private final static String FILE_EXTENSION = ".bma";

    public static FileManager getInstance() {
        return ourInstance;
    }

    private FileManager() {
    }

    public void writePolynomialToFile(String filename) throws IOException {
        System.out.println(PolynomialStorage.getInstance().size());
        //open stream
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileDirectory + filename + FILE_EXTENSION));
        os.writeObject(PolynomialStorage.getInstance());

        //close stream
        os.close();

        //clear polynomial storage
        PolynomialStorage.getInstance().clearData();
    }

    public PolynomialStorage getPolynomialsFromFile(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath));
        PolynomialStorage decodedObj = (PolynomialStorage) is.readObject();
        System.out.println(decodedObj.size());
        is.close();
        return decodedObj;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }
}
