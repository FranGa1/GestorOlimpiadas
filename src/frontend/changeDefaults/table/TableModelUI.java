package frontend.changeDefaults.table;

import javax.swing.table.DefaultTableModel;

public class TableModelUI extends DefaultTableModel {

    String[] titulos;
    Object[][] datos;

    /**
     * Determina el modelo con el que se va a construir la tabla
     * @param datos
     * @param titulos
     */
    public TableModelUI(Object[][] datos, String[] titulos) {
        super();
        this.titulos=titulos;
        this.datos=datos;
        setDataVector(datos, titulos);
    }

    public TableModelUI() {
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

}