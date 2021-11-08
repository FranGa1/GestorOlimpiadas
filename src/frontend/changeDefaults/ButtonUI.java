package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class ButtonUI extends JButton {

    public ButtonUI(String text){
        super(text);
        this.setUI(new StyledButtonUI());
        this.setFont(new Font("Calibri", Font.BOLD, 18));
        this.setBackground(new Color(0x4C9ED9));
        this.setForeground(Color.white);
        //0x4C9ED9
    }
}
