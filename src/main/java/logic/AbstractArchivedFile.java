package logic;

import java.io.Serializable;

/**
 * Logic of arch
 */
public abstract class AbstractArchivedFile implements Serializable {
    protected String fileName;
    protected String fileExtension; //e.g. ".jpg"
    protected int archiveMode;

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getArchiveMode() {
        return archiveMode;
    }

    public void setArchiveMode(int archiveMode) {
        this.archiveMode = archiveMode;
    }
}
