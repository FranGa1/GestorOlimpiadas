package frontend.changeDefaults.buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonTable extends JButton {

    public ButtonTable(String text){
        super(text);
        this.setUI(new StyledButtonTable());
        this.setFont(new Font("Calibri", Font.BOLD, 18));
        this.setBackground(new Color(0xCB2DC0));
        this.setForeground(Color.white);
    }
}
