package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.exceptions.NotConnectedException;
import backend.export.ExportCSV;
import frontend.changeDefaults.buttons.ButtonTable;
import frontend.changeDefaults.buttons.ButtonUI;
import frontend.changeDefaults.table.TableModelUI;
import frontend.changeDefaults.table.TableUI;
import frontend.changeDefaults.WPanel;
import objetos.Deportista;
import objetos.Disciplina;
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


public class CreateDeportistaTable {

    private static JTable table;
    private static final Object[] titles = {"Nombre y apellido", "Pais", "Disciplinas", "Editar", "Eliminar"};
    private static List<Deportista> lista;

    public static JPanel create(){

        //Creamos los panels
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel buttonPanel = new WPanel();


        //Creamos los botones
        JButton nuevo = new ButtonUI("+ Nuevo");
        JButton export = new ButtonUI("Exportar CSV");
        JButton volver = new ButtonUI("Volver");

        //Creamos los labels
        JLabel headerLbl = new JLabel("DEPORTISTAS", SwingConstants.CENTER);
        JLabel headerTableLbl = new JLabel("DEPORTISTAS", SwingConstants.CENTER);

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(new Color(0xEC34E1));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //construimos los botones
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nuevo);
        buttonPanel.add(export);
        buttonPanel.add(volver);

        //Construimos la table
        String[] columnNames = {""};
        Object[][] data = {{""}};
        table = new TableUI(data, columnNames);
        table.addMouseListener(new ListenerTable());
        JScrollPane scrollPane = new  JScrollPane(table);

        //Construimos el center
        center.setLayout(new BorderLayout());
        center.add(buttonPanel, BorderLayout.NORTH);
        center.add(scrollPane, BorderLayout.CENTER);
        center.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));


        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        //Listeners
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("Menu");
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateModifyDeportista.setAdd();
                ChangeCards.swap("ModifDeportista");
            }
        });

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MiConnection.nullConnection()){
                    JOptionPane.showMessageDialog(null,
                            "No hay conexion", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                List<List<String>> data = new LinkedList<>();
                List<String> header = Arrays.asList("ID", "Nombre", "Apellido", "Email", "Telefono", "Pais", "Disciplinas");
                data.add(header);
                for (Deportista p : lista){
                    List<String> line = Arrays.asList( String.valueOf(p.getId()), p.getNombres(), p.getApellidos(), p.getEmail(),
                            p.getTelefono(), p.getPais().getNombre(), p.getDisciplinas().get(0).getNombre());
                    data.add(line);
                }
                ExportCSV.Export(data);
            }
        });

        return panel;
    }

    public static void updateTableDeportistas() {

        Object[][] matrix;
        Object[] header = titles;

        //Buscamos en la base de datos
        try {
            lista = FactoryDAO.getDeportistaDAO().getDeportistas();
        } catch (NotConnectedException e) {

            matrix = new Object[][]{{"No connection to DB "}};
            header = new Object[]{"ERROR"};

            //Asignamos la nueva matriz a la tabla
            TableModel model = new TableModelUI(matrix, header);
            table.setModel(model);
            return;

        } catch (SQLException e) {
            matrix = new Object[][]{{"Hubo problemas con la base de datos."}};
            header = new Object[]{"ERROR"};

            //Asignamos la nueva matriz a la tabla
            TableModel model = new TableModelUI(matrix, header);
            table.setModel(model);
            return;
        } catch (Exception e){
            matrix = new Object[][]{{"Hubo un problema. Intente de nuevo mas tarde" }};
            header = new Object[]{"ERROR"};

            //Asignamos la nueva matriz a la tabla
            TableModel model = new TableModelUI(matrix, header);
            table.setModel(model);
            return;
        }

        //Creamos los botones
        JButton editarBtn = new ButtonTable("Editar");
        editarBtn.setName("edit");
        JButton eliminarBtn = new ButtonTable("Eliminar");
        eliminarBtn.setName("remove");

        //Creamos la matriz
        matrix = new Object[lista.size()][5];
        for (int i = 0, n = lista.size(); i < n; i++) {
            Deportista d = lista.get(i);
            matrix[i][0] = d.getNombres() + " " + d.getApellidos();
            matrix[i][1] = d.getPais().getNombre();
            List<Disciplina> disciplinas = d.getDisciplinas();
            matrix[i][2] = disciplinas.get(0).getNombre();
            matrix[i][3] = editarBtn;
            matrix[i][4] = eliminarBtn;
        }
        //Asignamos la nueva matriz a la tabla
        TableModel model = new TableModelUI(matrix, header);
        table.setModel(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(150);
    }

    private static class ListenerTable implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent evt) {
            int column = table.getColumnModel().getColumnIndexAtX(evt.getX());
            int row = evt.getY() / table.getRowHeight();
            System.out.println("Column: " + column + "\n Fila: " + row);

            //Si el click es en una casilla valida
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton boton) {
                    Deportista dep = lista.get(row);

                    if (boton.getName().equals("edit")) {
                        CreateModifyDeportista.setEditable(dep);
                        ChangeCards.swap("ModifDeportista");
                    }
                    if (boton.getName().equals("remove")) {
                        int reply = JOptionPane.showConfirmDialog(null, "Seguro que desea " +
                                "eliminar al deportista " + dep.getNombres() + " " + dep.getApellidos(),
                                "Eliminar Deportista",
                                JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            try {
                                FactoryDAO.getDeportistaDAO().eliminar(dep);
                                ((TableModelUI)table.getModel()).removeRow(row);
                                lista.remove(row);
                            } catch (SQLException e) {
                                System.out.println("No se pudo eliminar");
                                e.printStackTrace();
                            } catch (Exception e){
                                System.out.println("Hubo un problema. Intente de nuevo");
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
