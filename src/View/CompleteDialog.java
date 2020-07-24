package View;

import javax.swing.*;

public class CompleteDialog extends JFrame{
    public JLabel labelComplete;
    public JButton btnComplete;
    public JPanel panelComplete;

    public CompleteDialog(){
        panelComplete = new JPanel();
        btnComplete = new JButton("확인");
        labelComplete = new JLabel();

        btnComplete.addActionListener(e -> this.dispose());
        labelComplete.setText("STONE에 설정이 적용되었습니다!");

        panelComplete.add(labelComplete);
        panelComplete.add(new JLabel(""));
        panelComplete.add(btnComplete);

        setContentPane(panelComplete);
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(250, 150);
        setTitle("STONE Manager");
        setVisible(true);
    }
}
