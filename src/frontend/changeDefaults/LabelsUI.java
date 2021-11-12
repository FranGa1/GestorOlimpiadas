package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class LabelsUI extends JLabel {

    public LabelsUI(String text){
        super(text);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("SansSerif", Font.BOLD, 12));
    }
}
