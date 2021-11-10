<<<<<<< HEAD
package frontend.changeDefaults.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof JButton){
            JButton btn = (JButton) value;
            return btn;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
=======
package frontend.changeDefaults.table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (value instanceof JButton){
            JButton btn = (JButton) value;
            return btn;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

    public boolean isCellEditable(int row, int column){
        return false;
    }
}
>>>>>>> merges
