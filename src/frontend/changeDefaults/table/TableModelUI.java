package frontend.changeDefaults.table;

import javax.swing.table.DefaultTableModel;


public class TableModelUI extends DefaultTableModel {

    public TableModelUI(Object[][] datos, Object[] titulos) {
        super(datos, titulos);
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}