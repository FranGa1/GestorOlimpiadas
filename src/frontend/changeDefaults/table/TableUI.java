package frontend.changeDefaults.table;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableUI extends JTable {

    public TableUI(TableModel model, int width, int height){
        super(model);
        this.setPreferredScrollableViewportSize(new Dimension(width, height));
        this.setFont(new Font("Calibri", Font.PLAIN, 18));
        this.setRowHeight(30);
    }
}
