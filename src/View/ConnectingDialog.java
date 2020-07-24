package View;

import javax.swing.*;

public class ConnectingDialog extends JFrame{
    public JLabel labelState;
    public JPanel panelState;

    public ConnectingDialog(){
        panelState = new JPanel();
        labelState = new JLabel();

        labelState.setText("Bluetooth 서비스에 연결하는 중...");

        panelState.add(labelState);

        setContentPane(panelState);
        setResizable(false);
        setSize(150, 100);
        setTitle("STONE Manager");
        setUndecorated(true);
        setVisible(true);
    }
}
