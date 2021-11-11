package frontend.changeDefaults.table;

import javax.swing.*;
import java.awt.*;

public class TableUI extends JTable {

    public TableUI(Object[][] data, Object[] titles){
        this.setDefaultRenderer(Object.class, new Render());
        this.setModel(new TableModelUI(data, titles));
        this.setPreferredScrollableViewportSize(getPreferredSize());
        this.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.setRowHeight(30);
    }
}
