package frontend.panels;

//import backend.export.ExportCSV;
import backend.export.ExportCSV;
import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.exceptions.PaisUsedException;
import frontend.changeDefaults.buttons.ButtonTable;
import frontend.changeDefaults.buttons.ButtonUI;
import frontend.changeDefaults.table.TableModelUI;
import frontend.changeDefaults.table.TableUI;
import frontend.changeDefaults.WPanel;
import objetos.Pais;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CreatePaisTable {

    private static final Object[] titles = {"ID", "Nombre", "Modificar", "Eliminar"};
    private static TableUI table;
    private static List<Pais> list;

    public static JPanel create() {
        //Creamos los panels
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel buttonPanel = new WPanel();


        //Creamos los botones
        JButton nuevo = new ButtonUI("+ Nuevo");
        JButton volver = new ButtonUI("Volver");
        JButton exportar = new ButtonUI("Exportar CSV");

        //Creamos los labels
        JLabel headerLbl = new JLabel("PAISES", SwingConstants.CENTER);

        //Creamos la tabla
        String[] columnNames = {"No conection to DB"};
        Object[][] data = {{"No conection to DB"}};
        table = new TableUI(data, columnNames);
        JScrollPane scrollPane = new  JScrollPane(table);

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(new Color(0xEC34E1));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl, BorderLayout.CENTER);

        //construimos los botones
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nuevo);
        buttonPanel.add(exportar);
        buttonPanel.add(volver);

        //Construimos el center
        center.setLayout(new BorderLayout());
        center.add(buttonPanel, BorderLayout.NORTH);
        center.add(scrollPane , BorderLayout.CENTER);
        center.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));

        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        //Listeners
        table.addMouseListener(new ListenerTable());

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("Menu");
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateModifyPais.setAdd();
                ChangeCards.swap("ModifPais");}
        });

        exportar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MiConnection.nullConnection()){
                    JOptionPane.showMessageDialog(null,
                            "No hay conexion", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<List<String>> data = new LinkedList<>();
                List<String> header = Arrays.asList("ID", "Nombre");
                data.add(header);
                for (Pais p : list){
                    List<String> line = Arrays.asList( String.valueOf(p.getId()), p.getNombre());
                    data.add(line);
                }
                ExportCSV.Export(data);
            }
        });

        return panel;
    }

    public static void updateTablePais(){
        Object[][] matrix;
        Object[] header = titles;

        if (MiConnection.nullConnection()) {
            matrix = new Object[][]{{"No connection to DB"}};
            header = new Object[]{"No connection to DB"};

            //Asignamos la nueva matriz a la tabla
            TableModel model = new TableModelUI(matrix, header);
            table.setModel(model);
            return;
        }
        try {
            list = FactoryDAO.getPaisDAO().getPaises();
        } catch (SQLException e) {
            System.out.println("No se pudo traer la lista de deportistas");
            return;
        } catch (Exception e){
            System.out.println("Hubo un problema. Intente de nuevo");
            return;
        }

        //Creamos los botones
        JButton editarBtn = new ButtonTable("Editar");
        editarBtn.setName("edit");
        JButton eliminarBtn = new ButtonTable("Eliminar");
        eliminarBtn.setName("remove");

        //Creamos la matriz
        matrix = new Object[list.size()][5];
        for (int i = 0, n = list.size(); i < n; i++) {
            Pais p = list.get(i);
            matrix[i][0] = p.getId();
            matrix[i][1] = p.getNombre();
            matrix[i][2] = editarBtn;
            matrix[i][3] = eliminarBtn;
        }
        //Asignamos la nueva matriz a la tabla
        TableModel model = new TableModelUI(matrix, header);
        table.setModel(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(35);
    }

    private static class ListenerTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent evt) {
            int column = table.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY() / table.getRowHeight();
            System.out.println("Column: " + column + "\n Fila: " + row);

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton boton) {
                    Pais pais = list.get(row);

                    if (boton.getName().equals("edit")) {
                        CreateModifyPais.setEditable(pais);
                        ChangeCards.swap("ModifPais");
                    }
                    if (boton.getName().equals("remove")) {
                        int reply = JOptionPane.showConfirmDialog(null, "Seguro que desea " +
                                        "eliminar al deportista " + pais.getNombre(),
                                "Eliminar Deportista",
                                JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            try {
                                FactoryDAO.getPaisDAO().eliminar(pais);
                                ((TableModelUI)table.getModel()).removeRow(row);
                                list.remove(row);
                            } catch (PaisUsedException e){
                                JOptionPane.showMessageDialog(null,
                                        "El Pais se encuentra asigando a deportistas. Eliminalos e intente de nuevo"
                                        , "Error Message",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            catch (SQLException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Verfique la conexion a la base de datos"
                                        , "Error Message",
                                        JOptionPane.ERROR_MESSAGE);
                                //e.printStackTrace();
                            } catch (Exception e){
                                JOptionPane.showMessageDialog(null,
                                        "Hubo un problema. Intente de nuevo mas tarde"
                                        , "Error Message",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}