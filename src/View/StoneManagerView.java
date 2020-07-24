package View;

import javax.swing.*;
import java.awt.*;

public class StoneManagerView{
    public ButtonGroup groupRadio;
    public JButton btnSave;
    public JFrame frameMain;
    public JLabel labelBrightness;
    public JPanel panelBrightness, panelButton;
    public JRadioButton radioAurora, radioCandle, radioFirefly, radioRGB, radioWave;
    public JSlider slideBrightness;

    public StoneManagerView(){
        frameMain = new JFrame();
        panelBrightness = new JPanel(new BorderLayout());
        panelButton = new JPanel(new GridLayout(3, 2));

        btnSave = new JButton("저장");

        labelBrightness = new JLabel("   밝기");

        radioAurora = new JRadioButton("오로라");
        radioCandle = new JRadioButton("촛불");
        radioFirefly = new JRadioButton("반딧불");
        radioRGB = new JRadioButton("단일 색상");
        radioWave = new JRadioButton("파도");

        slideBrightness = new JSlider(0, 100, 50);
        slideBrightness.setMajorTickSpacing(50);
        slideBrightness.setMinorTickSpacing(10);
        slideBrightness.setPaintLabels(true);
        slideBrightness.setPaintTicks(true);

        groupRadio = new ButtonGroup();
        groupRadio.add(radioAurora);
        groupRadio.add(radioCandle);
        groupRadio.add(radioFirefly);
        groupRadio.add(radioRGB);
        groupRadio.add(radioWave);

        panelBrightness.add(BorderLayout.NORTH, labelBrightness);
        panelBrightness.add(BorderLayout.SOUTH, slideBrightness);

        panelButton.add(radioAurora);
        panelButton.add(radioCandle);
        panelButton.add(radioFirefly);
        panelButton.add(radioRGB);
        panelButton.add(radioWave);

        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMain.setLayout(new FlowLayout());
        frameMain.setLocationRelativeTo(null);
        frameMain.setResizable(false);
        frameMain.setSize(420, 170);
        frameMain.setTitle("STONE Manager");
        frameMain.setVisible(true);

        frameMain.getContentPane().add(panelButton);
        frameMain.getContentPane().add(panelBrightness);
        frameMain.getContentPane().add(btnSave);
    }
}
