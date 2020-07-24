package Controller;

import View.ColorPickerView;
import View.StoneManagerView;

import javax.swing.*;
import java.awt.event.ItemListener;

public class UIController{
    public int[] settingValues = new int[]{50, 1, 255, 255, 255};

    BTController btController;
    public JButton btnSave;
    public JLabel labelBrightness, labelState;
    public JRadioButton radioAurora, radioCandle, radioFirefly, radioOff, radioRGB, radioWave;
    public JSlider slideBrightness;

    public UIController(StoneManagerView stoneManagerView){
        btnSave = stoneManagerView.btnSave;
        labelBrightness = stoneManagerView.labelBrightness;
        labelState = stoneManagerView.labelState;
        radioAurora = stoneManagerView.radioAurora;
        radioCandle = stoneManagerView.radioCandle;
        radioFirefly = stoneManagerView.radioFirefly;
        radioOff = stoneManagerView.radioOff;
        radioRGB = stoneManagerView.radioRGB;
        radioWave = stoneManagerView.radioWave;
        slideBrightness = stoneManagerView.slideBrightness;
        
        ItemListener radioListener = e -> {
            if(radioAurora.isSelected()){
                settingValues = new int[]{slideBrightness.getValue(), 3, 0, 0, 0};
            }else if(radioCandle.isSelected()){
                settingValues = new int[]{slideBrightness.getValue(), 2, 0, 0, 0};
            }else if(radioFirefly.isSelected()){
                settingValues = new int[]{slideBrightness.getValue(), 5, 0, 0, 0};
            }else if(radioRGB.isSelected()){
                settingValues = new int[]{slideBrightness.getValue(), 1, 0, 0, 0};
                new ColorPickerView(settingValues, this);
            }else if(radioWave.isSelected()){
                settingValues = new int[]{slideBrightness.getValue(), 4, 0, 0, 0};
            }

            if(radioOff.isSelected()) {
                settingValues = new int[]{slideBrightness.getValue(), 0, 0, 0, 0};

                labelBrightness.setEnabled(false);
                slideBrightness.setEnabled(false);
            }else{
                labelBrightness.setEnabled(true);
                slideBrightness.setEnabled(true);
            }
        };

        radioAurora.addItemListener(radioListener);
        radioCandle.addItemListener(radioListener);
        radioFirefly.addItemListener(radioListener);
        radioOff.addItemListener(radioListener);
        radioRGB.addItemListener(radioListener);
        radioWave.addItemListener(radioListener);

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
            radioOff.setEnabled(true);
            radioRGB.setEnabled(true);
            radioWave.setEnabled(true);
            slideBrightness.setEnabled(true);
        }else{
            btnSave.setEnabled(false);
            labelBrightness.setEnabled(false);
            radioAurora.setEnabled(false);
            radioCandle.setEnabled(false);
            radioFirefly.setEnabled(false);
            radioOff.setEnabled(false);
            radioRGB.setEnabled(false);
            radioWave.setEnabled(false);
            slideBrightness.setEnabled(false);
        }
    }
}