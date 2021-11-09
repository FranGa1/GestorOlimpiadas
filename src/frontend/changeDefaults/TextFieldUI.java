package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class TextFieldUI extends JTextField {

    public TextFieldUI(int Columns){
        super(Columns);
        this.setBackground(new Color(0xBAE6F3));
        this.setBorder(null);
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(0x282828)));
    }

}
