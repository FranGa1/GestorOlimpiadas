package frontend.panels.tables;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {

    String[] titulos;
    Object[][] datos;

    /**
     * Determina el modelo con el que se va a construir la tabla
     * @param datos
     * @param titulos
     */
    public TableModel(Object[][] datos, String[] titulos) {
        super();
        this.titulos=titulos;
        this.datos=datos;
        setDataVector(datos, titulos);
    }

    public TableModel() {
    }

    public boolean isCellEditable(int row, int column){
        return false;
    }

}