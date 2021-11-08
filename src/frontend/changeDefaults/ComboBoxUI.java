package frontend.changeDefaults;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ComboBoxUI<T> extends JComboBox<T> {

    public ComboBoxUI(T[] items) {
        super(items);
        this.setPreferredSize(new Dimension(333,20));
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
        this.setBackground(new Color(0xBAE6F3));
        //0xF6043A

       for (int i = 0; i < this.getComponentCount(); i++) {
            if (this.getComponent(i) instanceof JComponent) {
                ((JComponent) this.getComponent(i)).setBorder(new EmptyBorder(0, 0, 0, 0));
//                ((JComponent) comboBox.getComponent(i)).setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
            }
//
//
            if (this.getComponent(i) instanceof AbstractButton) {
                ((AbstractButton) this.getComponent(i)).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
//                ((AbstractButton) this.getComponent(i)).setUI(new JButton().getUI());
//                ((AbstractButton) this.getComponent(i)).setSize(20,20);
            }
        }
    }
}
