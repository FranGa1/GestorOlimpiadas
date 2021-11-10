package frontend.changeDefaults.table;

import javax.swing.*;
<<<<<<< HEAD
=======
import javax.swing.table.TableModel;
>>>>>>> merges
import java.awt.*;

public class TableUI extends JTable {

<<<<<<< HEAD
    public TableUI(Object[][] data, Object[] titles){
        //this.setPreferredScrollableViewportSize(new Dimension(width, height));
=======
    public TableUI(Object[][] data, String[] titles){
>>>>>>> merges
        this.setDefaultRenderer(Object.class, new Render());
        this.setModel(new TableModelUI(data, titles));
        this.setPreferredScrollableViewportSize(getPreferredSize());
        this.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.setRowHeight(30);
    }
}
