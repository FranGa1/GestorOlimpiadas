package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class PasswordFieldUI extends JPasswordField {

    public PasswordFieldUI(int Columns){
        super(Columns);
        this.setBackground(null);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
    }

}
