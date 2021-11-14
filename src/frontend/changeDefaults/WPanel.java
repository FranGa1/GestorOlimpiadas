package frontend.changeDefaults;

import javax.swing.*;
import java.awt.*;

public class WPanel extends JPanel {

    public WPanel(){
        super();
        this.setBackground(new Color(0x666666));
        setOpaque(false);
        setBackground(new Color(0,0,0,55));
    }

    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        Rectangle r = g.getClipBounds();
        g.fillRect(r.x, r.y, r.width, r.height);
        super.paintComponent(g);
    }
}
