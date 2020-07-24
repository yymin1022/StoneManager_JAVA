package View;

import javax.swing.*;
import java.awt.*;

public class ConnectingDialog extends JFrame{
    public JLabel labelState;
    public JPanel panelState;

    public ConnectingDialog(){
        panelState = new JPanel(new BorderLayout());
        labelState = new JLabel();

        labelState.setText("   Bluetooth 서비스에 연결하는 중...");

        panelState.add(BorderLayout.NORTH, new JLabel(""));
        panelState.add(BorderLayout.CENTER, labelState);

        setContentPane(panelState);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(250, 150);
        setTitle("STONE Manager");
        setVisible(true);
    }
}
