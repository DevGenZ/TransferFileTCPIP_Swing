package Server;

import FileInfo.FileInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private final int port = 922;

    public static void main(String[] args) {
        Server server = new Server();
        server.open();
        server.start();
    }

    public void open() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is open on port 922");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            Socket server = null;
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;

            try {
                server = serverSocket.accept();
                System.out.println("Connected to - " + server.getRemoteSocketAddress());

                ois = new ObjectInputStream(server.getInputStream());
                FileInfo fileInfo = (FileInfo) ois.readObject();
                if (fileInfo != null) {
                    creatFile(fileInfo);
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
                try {
                    if (ois != null) {
                        ois.close();
                    }

                    if (oos != null) {
                        oos.close();
                    }

                    if (server != null) {
                        server.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean creatFile(FileInfo fileInfo) {
        BufferedOutputStream bos = null;
        try {
            if (fileInfo != null) {
                File fileReceive = new File("C:\\Users\\is2vi\\OneDrive\\Desktop\\" + fileInfo.getFileName());
                bos = new BufferedOutputStream(new FileOutputStream(fileReceive));
                bos.write(fileInfo.getDataBytes());
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
