package Controller;

import View.StoneManagerView;

import javax.swing.*;
import java.awt.event.ItemListener;
import java.util.Enumeration;

public class UIController{
    BTController btController;
    int[] settingValues = new int[]{50, 1, 255, 255, 255};

    public UIController(StoneManagerView stoneManagerView){
        btController = new BTController();
        ItemListener radioListener = e -> {
            if(stoneManagerView.radioAurora.isSelected()){
                settingValues = new int[]{50, 3, 0, 0, 0};
            }else if(stoneManagerView.radioCandle.isSelected()){
                settingValues = new int[]{50, 2, 0, 0, 0};
            }else if(stoneManagerView.radioFirefly.isSelected()){
                settingValues = new int[]{50, 5, 0, 0, 0};
            }else if(stoneManagerView.radioRGB.isSelected()){
                settingValues = new int[]{50, 1, 255, 64, 129};
            }else if(stoneManagerView.radioWave.isSelected()){
                settingValues = new int[]{50, 4, 0, 0, 0};
            }
        };

        stoneManagerView.radioAurora.addItemListener(radioListener);
        stoneManagerView.radioCandle.addItemListener(radioListener);
        stoneManagerView.radioFirefly.addItemListener(radioListener);
        stoneManagerView.radioRGB.addItemListener(radioListener);
        stoneManagerView.radioWave.addItemListener(radioListener);

        Enumeration<AbstractButton> enums = stoneManagerView.groupRadio.getElements();
        while(enums.hasMoreElements()){
            AbstractButton ab = enums.nextElement();
            JRadioButton jb = (JRadioButton)ab;

            if(jb.isSelected())
                System.out.println(jb.getText());
        }

        stoneManagerView.btnSave.addActionListener(e -> {
            btController = new BTController();
            btController.settingValues = settingValues;
            btController.start();
        });
    }
}
