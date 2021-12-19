package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientTransferController {
    private ClientTransferView view;

    public ClientTransferController(ClientTransferView view) {
        this.view = view;

        view.getBtnBrowser().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(view);

                try {
                    if (fileChooser.getSelectedFile() != null) {
                        view.getTxfFilePath().setText(fileChooser.getSelectedFile().getPath());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        view.getBtnSend().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = view.getTxfHost().getText().trim();
                int port = Integer.parseInt(view.getTxfPort().getText().trim());
                String sourceFilePath = view.getTxfFilePath().getText();

                if (host != "" && sourceFilePath != "") {
                    String destinationDir = "D:\\Server\\";
                    Client client = new Client(host, port, view.getTxaResult());
                    client.connectServer();
                    client.sendFile(sourceFilePath, destinationDir);

                    try {
                        if (client != null) {
                            client.getClient().close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ host, port và filepath");
                }
            }
        });
    }
}
