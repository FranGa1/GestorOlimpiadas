package frontend.changeDefaults;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class ComboBoxUI<T> extends JComboBox<T> {

    public ComboBoxUI(T[] items) {
        super(items);
        this.setPreferredSize(new Dimension(333,20));
        this.setBackground(new Color(0xFFFFFF));

        this.setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.WHITE));
        this.setForeground(Color.BLACK);

       for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) this.getComponent(i)).setBorder(null);
                ((AbstractButton) this.getComponent(i)).setBackground(Color.WHITE);
            }
        }
    }
}
