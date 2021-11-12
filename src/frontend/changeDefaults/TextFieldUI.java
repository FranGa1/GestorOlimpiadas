package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class TextFieldUI extends JTextField {

    public TextFieldUI(int Columns){
        super(Columns);
        //this.setBackground(new Color(0xBAE6F3));
        this.setBackground(null);
        this.setBorder(null);
        //this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        this.setForeground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
    }

}
