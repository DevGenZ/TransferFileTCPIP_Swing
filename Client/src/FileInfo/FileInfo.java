package FileInfo;

import java.io.Serializable;

public class FileInfo implements Serializable {
    private String destinationDirectory;
    private String fileName;
    private long fileSize;
    private int piecesOfFile;
    private int lastByteLength;
    private byte[] dataBytes;
    private String status;

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public String getStatus() {
        return status;
    }
}
