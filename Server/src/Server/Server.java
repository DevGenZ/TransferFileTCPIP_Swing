package Server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import FileInfo.FileInfo;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port = 922;

    public static void main(String[] args) {
        Server tcpServer = new Server();
        tcpServer.open();
        tcpServer.start();
    }

    public void open() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is open on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            Socket server = null;
            DataInputStream inFromClient = null;
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            try {
                server = serverSocket.accept();
                System.out.println("Connected to " + server.getRemoteSocketAddress());

                inFromClient = new DataInputStream(server.getInputStream());
                System.out.println(inFromClient.readUTF());

                ois = new ObjectInputStream(server.getInputStream());
                FileInfo fileInfo = (FileInfo) ois.readObject();
                if (fileInfo != null) {
                    createFile(fileInfo);
                }

                oos = new ObjectOutputStream(server.getOutputStream());
                fileInfo.setStatus("Success");
                fileInfo.setDataBytes(null);
                oos.writeObject(fileInfo);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeStream(ois);
                closeStream(oos);
                closeStream(inFromClient);
                closeSocket(server);
            }
        }
    }

    private boolean createFile(FileInfo fileInfo) {
        BufferedOutputStream bos = null;

        try {
            if (fileInfo != null) {
                File fileReceive = new File(fileInfo.getDestinationDirectory() + fileInfo.getFileName());
                bos = new BufferedOutputStream(new FileOutputStream(fileReceive));
                bos.write(fileInfo.getDataBytes());
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeStream(bos);
        }
        return true;
    }

    public void closeSocket(Socket socket) {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeStream(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeStream(OutputStream outputStream) {
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}