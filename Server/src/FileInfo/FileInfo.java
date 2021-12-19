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

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
