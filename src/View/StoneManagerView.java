package View;

import javax.swing.*;
import java.awt.*;

public class StoneManagerView{
    public ButtonGroup groupRadio;
    public JButton btnSave;
    public JFrame frameMain;
    public JLabel labelBrightness, labelState;
    public JPanel panelBottom, panelBrightness, panelRadio;
    public JRadioButton radioAurora, radioCandle, radioFirefly, radioOff, radioRGB, radioWave;
    public JSlider slideBrightness;

    public StoneManagerView(){
        frameMain = new JFrame();
        panelBottom = new JPanel(new GridLayout(2, 1));
        panelBrightness = new JPanel(new BorderLayout());
        panelRadio = new JPanel(new GridLayout(3, 2));

        btnSave = new JButton("저장");

        labelBrightness = new JLabel("   밝기");
        labelState = new JLabel("   색상을 선택한 뒤 저장 버튼을 클릭합니다.");

        radioAurora = new JRadioButton("오로라");
        radioCandle = new JRadioButton("촛불");
        radioFirefly = new JRadioButton("반딧불");
        radioOff = new JRadioButton("끄기");
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
        groupRadio.add(radioOff);
        groupRadio.add(radioRGB);
        groupRadio.add(radioWave);

        panelBottom.add(labelState);
        panelBottom.add(btnSave);

        panelBrightness.add(BorderLayout.NORTH, labelBrightness);
        panelBrightness.add(BorderLayout.SOUTH, slideBrightness);

        panelRadio.add(radioAurora);
        panelRadio.add(radioCandle);
        panelRadio.add(radioFirefly);
        panelRadio.add(radioRGB);
        panelRadio.add(radioWave);
        panelRadio.add(radioOff);

        frameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameMain.setLayout(new FlowLayout());
        frameMain.setLocationRelativeTo(null);
        frameMain.setResizable(false);
        frameMain.setSize(420, 190);
        frameMain.setTitle("STONE Manager");
        frameMain.setVisible(true);

        frameMain.getContentPane().add(panelRadio);
        frameMain.getContentPane().add(panelBrightness);
        frameMain.getContentPane().add(panelBottom);
    }
}
