package Client;

import FileInfo.FileInfo;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket client;
    private String host;
    private int port;
    private JTextArea txaLog;

    public Client(String host, int port, JTextArea txaLog) {
        this.host = host;
        this.port = port;
        this.txaLog = txaLog;
    }

    public Socket getClient() {
        return client;
    }

    public void connectServer() {
        try {
            client = new Socket(host, port);
            txaLog.append("Connected to server.\n");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String sourceFilePath, String destinationDir) {
        DataOutputStream dos = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("Hello from " + client.getLocalSocketAddress());

            FileInfo fileInfo = getFileInfo(sourceFilePath, destinationDir);

            oos = new ObjectOutputStream(client.getOutputStream());
            oos.writeObject(fileInfo);

            ois = new ObjectInputStream(client.getInputStream());
            fileInfo = (FileInfo) ois.readObject();
            if (fileInfo != null) {
                txaLog.append("Send file to server " + fileInfo.getStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }

                if (ois != null) {
                    ois.close();
                }

                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileInfo getFileInfo(String sourceFilePath, String destinationDir) {
        FileInfo fileInfo = null;
        BufferedInputStream bis = null;

        try {
            File sourceFile = new File(sourceFilePath);
            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            fileInfo = new FileInfo();
            byte[] fileBytes = new byte[(int) sourceFile.length()];

            bis.read(fileBytes, 0, fileBytes.length);
            fileInfo.setFileName(sourceFile.getName());
            fileInfo.setDataBytes(fileBytes);
            fileInfo.setDestinationDirectory(destinationDir);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileInfo;
    }
}
