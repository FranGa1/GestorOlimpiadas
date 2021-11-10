package frontend.changeDefaults;

import frontend.changeDefaults.table.CellsStyle;
import frontend.changeDefaults.table.Render;
import frontend.changeDefaults.table.TableModelUI;

import javax.swing.*;

public class TableUI extends JTable {
    public TableUI(Object[][] tableContent){
        setDefaultRenderer(Object.class, new Render());

        String[] indices = new String[] {"ID", "Nombre","btnModificar", "btnEliminar"};

        TableModelUI model = new TableModelUI(tableContent, indices);
        setModel(model);

        setPreferredScrollableViewportSize(getPreferredSize());

        getColumnModel().getColumn(0).setCellRenderer(new CellsStyle("numerico"));
        getColumnModel().getColumn(1).setCellRenderer(new CellsStyle("numerico"));
//        getColumnModel().getColumn(2).setCellRenderer(new CellsStyle(""));
//        getColumnModel().getColumn(3).setCellRenderer(new CellsStyle(""));

        setRowHeight(25);//tamaño de las celdas
        setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        getColumnModel().getColumn(0).setPreferredWidth(130);
        getColumnModel().getColumn(1).setPreferredWidth(380);
        getColumnModel().getColumn(2).setPreferredWidth(350);
        getColumnModel().getColumn(3).setPreferredWidth(130);

        //personaliza el encabezado
//        JTableHeader jtableHeader = getTableHeader();
//        jtableHeader.setDefaultRenderer(new Render());
//        setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane

//        scrollPaneTabla.setViewportView();
    }
}
