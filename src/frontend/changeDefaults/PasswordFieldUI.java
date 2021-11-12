package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class PasswordFieldUI extends JPasswordField {

    public PasswordFieldUI(int Columns){
        super(Columns);
        //this.setBackground(new Color(0xBAE6F3));
        //this.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(0x282828)));
        this.setBackground(null);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
    }

}
