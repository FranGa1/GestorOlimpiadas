package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.table.TableModelUI;
import frontend.changeDefaults.table.TableUI;
import frontend.changeDefaults.WPanel;
import objetos.Deportista;
import objetos.Disciplina;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateDeportistaTable {

    private static JTable table;
    private static final String[] titles = {"Nombre y apellido", "Pais", "Disciplinas", "", ""};

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
        //JLabel headerLbl = new JLabel("DEPORTISTAS", SwingConstants.CENTER);

        //Creamos la tabla
        //JTable table = new JTable();

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

//        Object[] columnNames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
//        Object[][] data = {
//                {"Kathy", "Smith",
//                        "Snowboarding", 5,false},
//                {"John", "Doe",
//                        "Rowing", 3, true},
//                {"Sue", "Black",
//                        "Knitting", 2,false},
//                {"Jane", "White",
//                        "Speed reading",20, true},
//                {"Joe", "Brown",
//                        "Pool", 10,false},
//                {"Kathy", "Smith",
//                        "Snowboarding", 5,false},
//                {"John", "Doe",
//                        "Rowing", 3, true},
//                {"Sue", "Black",
//                        "Knitting", 2,false},
//                {"Jane", "White",
//                        "Speed reading",20, true},
//                {"Joe", "Brown",
//                        "Pool", 10,false},
//                {"Kathy", "Smith",
//                        "Snowboarding", 5,false},
//                {"John", "Doe",
//                        "Rowing", 3, true},
//                {"Sue", "Black",
//                        "Knitting", 2,false},
//                {"Jane", "White",
//                        "Speed reading",20, true},
//                {"Joe", "Brown",
//                        "Pool", 10,false},
//                {"Kathy", "Smith",
//                        "Snowboarding", 5,false},
//                {"John", "Doe",
//                        "Rowing", 3, true},
//                {"Sue", "Black",
//                        "Knitting", 2,false},
//                {"Jane", "White",
//                        "Speed reading",20, true},
//                {"Joe", "Brown",
//                        "Pool", 10,false},
//                {"Kathy", "Smith",
//                        "Snowboarding", 5,false},
//                {"John", "Doe",
//                        "Rowing", 3, true},
//                {"Sue", "Black",
//                        "Knitting", 2,false},
//                {"Jane", "White",
//                        "Speed reading",20, true},
//                {"Joe", "Brown",
//                        "Pool", 10,false}
//        };
        String[] columnNames = {"No conection to DB"};
        Object[][] data = {{"No conection to DB"}};
        table = new TableUI(data, columnNames);
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
                if (MiConnection.nullConnection())
                    ChangeCards.swap("MenuD");
                else ChangeCards.swap("MenuC");
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("AddDeportista");
            }
        });

        return panel;
    }

    public static void updateTable(){
        //Buscamos en la base de datos
        List<Deportista> list = FactoryDAO.getDeportistaDAO().getDeportistas();
        Deportista[] array = list.toArray(new Deportista[0]);

        //Creamos la matriz
        Object[][] matrix = new Object[array.length][5];
        for (int i = 0, n = array.length; i < n; i++){
            Deportista d = array[i];
            matrix[i][0] = d.getNombres() +" "+ array[i].getApellidos();
            matrix[i][1] = d.getPais().getNombre();
            matrix[i][2] = d.getDisciplinas().get(0).getNombre();
            matrix[i][3] = new JButton("Editar");
            matrix[i][4] = new JButton("Eliminar");
        }
        //Asignamos la nueva matriz a la tabla
        TableModel model = new TableModelUI(matrix,titles);
        table.setModel(model);
    }
}
