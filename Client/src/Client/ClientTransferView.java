package Client;

import javax.swing.*;

public class ClientTransferView extends JFrame {
    private JTextField txfHost, txfPort, txfFilePath;
    private JTextArea txaResult;
    private JButton btnBrowser, btnSend;

    public ClientTransferView() {
        setTitle("Transfer file by TCP/IP");
        setSize(600, 350);

        JLabel lblHost = new JLabel("Host:");
        lblHost.setBounds(20, 20, 50, 25);
        add(lblHost);
        txfHost = new JTextField();
        txfHost.setBounds(55, 20, 120, 25);
        add(txfHost);

        JLabel lblPort = new JLabel("Port:");
        lblPort.setBounds(190, 20, 50, 25);
        add(lblPort);
        txfPort = new JTextField();
        txfPort.setBounds(220, 20, 50, 25);
        add(txfPort);

        txfFilePath = new JTextField();
        txfFilePath.setBounds(20, 50, 450, 25);
        txfFilePath.setEditable(false);
        add(txfFilePath);

        txaResult = new JTextArea();
        txaResult.setBounds(20, 110, 490, 150);
        add(txaResult);

        btnBrowser = new JButton("...");
        btnBrowser.setBounds(480, 50, 30, 25);
        add(btnBrowser);

        btnSend = new JButton("SEND");
        btnSend.setBounds(20, 80, 80, 25);
        add(btnSend);

        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JTextField getTxfHost() {
        return txfHost;
    }

    public void setTxfHost(JTextField txfHost) {
        this.txfHost = txfHost;
    }

    public JTextField getTxfPort() {
        return txfPort;
    }

    public void setTxfPort(JTextField txfPort) {
        this.txfPort = txfPort;
    }

    public JTextField getTxfFilePath() {
        return txfFilePath;
    }

    public void setTxfFilePath(JTextField txfFilePath) {
        this.txfFilePath = txfFilePath;
    }

    public JTextArea getTxaResult() {
        return txaResult;
    }

    public void setTxaResult(JTextArea txaResult) {
        this.txaResult = txaResult;
    }

    public JButton getBtnBrowser() {
        return btnBrowser;
    }

    public void setBtnBrowser(JButton btnBrowser) {
        this.btnBrowser = btnBrowser;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }
}
