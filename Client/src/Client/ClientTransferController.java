package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ClientTransferController implements ActionListener {
    private ClientTransferView view;

    public ClientTransferController(ClientTransferView view) {
        this.view = view;
        view.getBtnBrowser().addActionListener(this);
        view.getBtnSend().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(view.getBtnBrowser().getText())) {
            view.chooseFile();
        }
        if (e.getActionCommand().equals(view.getBtnSend().getText())) {
            String host = view.getTxfHost().getText().trim();
            int port = Integer.parseInt(view.getTxfPort().getText().trim());
            String sourceFilePath = view.getTxfFilePath().getText();
            if (host != "" && sourceFilePath != "") {
                String destinationDir = "D:\\Server\\";
                Client client = new Client(host, port, view.getTxaResult());
                client.connectServer();
                client.sendFile(sourceFilePath, destinationDir);
                client.closeSocket();
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ Host, Port và FilePath");
            }
        }
    }
}
