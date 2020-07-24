package Controller;

import View.ColorPickerView;
import View.StoneManagerView;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.Enumeration;

public class UIController{
    public int[] settingValues = new int[]{50, 1, 255, 255, 255};

    BTController btController;
    public JButton btnSave;
    public JLabel labelBrightness, labelState;
    public JRadioButton radioAurora, radioCandle, radioFirefly, radioRGB, radioWave;
    public JSlider slideBrightness;

    public UIController(StoneManagerView stoneManagerView){
        btnSave = stoneManagerView.btnSave;
        labelBrightness = stoneManagerView.labelBrightness;
        labelState = stoneManagerView.labelState;
        radioAurora = stoneManagerView.radioAurora;
        radioCandle = stoneManagerView.radioCandle;
        radioFirefly = stoneManagerView.radioFirefly;
        radioRGB = stoneManagerView.radioRGB;
        radioWave = stoneManagerView.radioWave;
        slideBrightness = stoneManagerView.slideBrightness;
        
        ItemListener radioListener = e -> {
            if(radioAurora.isSelected()){
                settingValues = new int[]{50, 3, 0, 0, 0};
            }else if(radioCandle.isSelected()){
                settingValues = new int[]{50, 2, 0, 0, 0};
            }else if(radioFirefly.isSelected()){
                settingValues = new int[]{50, 5, 0, 0, 0};
            }else if(radioRGB.isSelected()){
                settingValues = new int[]{50, 1, 0, 0, 0};
                new ColorPickerView(settingValues, this);
            }else if(radioWave.isSelected()){
                settingValues = new int[]{50, 4, 0, 0, 0};
            }
        };

        radioAurora.addItemListener(radioListener);
        radioCandle.addItemListener(radioListener);
        radioFirefly.addItemListener(radioListener);
        radioRGB.addItemListener(radioListener);
        radioWave.addItemListener(radioListener);

        Enumeration<AbstractButton> enums = stoneManagerView.groupRadio.getElements();
        while(enums.hasMoreElements()){
            AbstractButton ab = enums.nextElement();
            JRadioButton jb = (JRadioButton)ab;

            if(jb.isSelected())
                System.out.println(jb.getText());
        }

        slideBrightness.addChangeListener(e -> settingValues[0] = slideBrightness.getValue());

        btnSave.addActionListener(e -> {
            btController = new BTController(this);
            btController.settingValues = settingValues;
            btController.start();
        });
    }
    
    public void enableControl(boolean isEnabled){
        if(isEnabled){
            btnSave.setEnabled(true);
            labelBrightness.setEnabled(true);
            radioAurora.setEnabled(true);
            radioCandle.setEnabled(true);
            radioFirefly.setEnabled(true);
            radioRGB.setEnabled(true);
            radioWave.setEnabled(true);
            slideBrightness.setEnabled(true);
        }else{
            btnSave.setEnabled(false);
            labelBrightness.setEnabled(false);
            radioAurora.setEnabled(false);
            radioCandle.setEnabled(false);
            radioFirefly.setEnabled(false);
            radioRGB.setEnabled(false);
            radioWave.setEnabled(false);
            slideBrightness.setEnabled(false);
        }
    }
}