package View;

import Controller.UIController;
import com.bric.colorpicker.ColorPicker;

import javax.swing.*;
import java.awt.*;

public class ColorPickerView extends JFrame {
    public ColorPickerView(int[] settingValues, UIController uiController){
        JButton btnClose = new JButton("확인");
        JPanel panelColorPicker =  new JPanel(new FlowLayout());

        btnClose.addActionListener(e -> this.dispose());

        com.bric.colorpicker.ColorPicker colorPicker = new ColorPicker(true, false);
        colorPicker.setColor(Color.BLUE);
        colorPicker.addColorListener(colorModel -> uiController.settingValues = new int[]{settingValues[0], settingValues[1], colorPicker.getColor().getRed(), colorPicker.getColor().getGreen(), colorPicker.getColor().getBlue()});

        panelColorPicker.add(colorPicker);
        panelColorPicker.add(btnClose);

        setContentPane(panelColorPicker);
        setResizable(false);
        setSize(550, 410);
        setTitle("Color Picker");
        setUndecorated(true);
        setVisible(true);
    }
}