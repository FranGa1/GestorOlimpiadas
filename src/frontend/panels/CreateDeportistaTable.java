package frontend.panels;

import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.table.TableUI;
import frontend.changeDefaults.WPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                ChangeCards.swapPrev();
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(table.getColumnModel().getColumns());
                table.setModel(new DefaultTableModel(null, columnNames));
            }
        });

        return panel;
    }

    public static void updateTable(){
        //Buscamos en la base de datos

        //Creamos la matriz

        //Asignamos la nueva matriz a la tabla
        //TableModel model = new TableModelUI(data,titles);
        //table.setModel(model);
    }
}
