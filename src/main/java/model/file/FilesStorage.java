package model.file;

import model.binary.BinaryArchivedFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${DPudov} on 21.12.2016.
 */
public class FilesStorage {
    public Map<String, BinaryArchivedFile> map = new HashMap<>();
    private static FilesStorage ourInstance = new FilesStorage();

    public static FilesStorage getInstance() {
        return ourInstance;
    }

    private FilesStorage() {
    }
}
