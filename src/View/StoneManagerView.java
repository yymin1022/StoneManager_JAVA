package View;

import javax.swing.*;
import java.awt.*;

public class StoneManagerView{
    public ButtonGroup groupRadio;
    public JButton btnSave;
    public JFrame frameMain;
    public JPanel panelButton;
    public JRadioButton radioAurora, radioCandle, radioFirefly, radioRGB, radioWave;

    public StoneManagerView(){
        frameMain = new JFrame();
        panelButton = new JPanel(new GridLayout(6, 1));

        btnSave = new JButton("저장");

        radioAurora = new JRadioButton("오로라");
        radioCandle = new JRadioButton("촛불");
        radioFirefly = new JRadioButton("반딧불");
        radioRGB = new JRadioButton("단일 색상");
        radioWave = new JRadioButton("파도");

        groupRadio = new ButtonGroup();
        groupRadio.add(radioAurora);
        groupRadio.add(radioCandle);
        groupRadio.add(radioFirefly);
        groupRadio.add(radioRGB);
        groupRadio.add(radioWave);

        panelButton.add(radioAurora);
        panelButton.add(radioCandle);
        panelButton.add(radioFirefly);
        panelButton.add(radioRGB);
        panelButton.add(radioWave);
        panelButton.add(btnSave);

        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMain.setSize(200, 200);
        frameMain.setTitle("STONE Manager");
        frameMain.setVisible(true);

        frameMain.getContentPane().add(panelButton);
    }
}
