package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import frontend.changeDefaults.ButtonUI;
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
import java.util.List;

public class CreatePaisTable {

    private static TableUI table;
    private static Object[] titles = {"ID", "Nombre", "Modificar", "Eliminar"};

    public static JPanel create() {
        //Creamos los panels
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel buttonPanel = new WPanel();


        //Creamos los botones
        JButton nuevo = new ButtonUI("+ Nuevo");
        JButton volver = new ButtonUI("Volver");

        //Creamos los labels
        JLabel headerLbl = new JLabel("PAISES", SwingConstants.CENTER);

        //Creamos la tabla
        Object[][] data = {{"No connection to DB"}};
        Object[] title = {"No connection to DB"};
        table = new TableUI(data,title);
        JScrollPane scrollPane = new JScrollPane(table);
        
        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(new Color(0xEC34E1));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl, BorderLayout.CENTER);

        //construimos los botones
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nuevo);
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
        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swapPrev();
            }
        });

        nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ChangeCards.swap("AddPais");}
        });

        return panel;
    }

    private static Object[][] createTable(List<Pais> paises){

        int rows = paises.size();
        int cols = 4;

        Object [][] matrixPaises = new Object[rows][cols];

        for (int i=0; i<rows; i++){
            matrixPaises[i][0] = paises.get(i).getId();
            matrixPaises[i][1] = paises.get(i).getNombre();
            matrixPaises[i][2] = new JButton("Modificar");
            matrixPaises[i][3] = new JButton("Eliminar");
        }

        return matrixPaises;
    }

    public static void updateTable(){

        Object[][] matrix;
        TableModel model;

        if (MiConnection.nullConnection()){
            matrix = new Object[][]{{"No connection to DB"}};
            Object[] title = {"No connection to DB"};
            model = new TableModelUI(matrix,title);
        }
        else {
            //Buscamos en la base de datos
            List<Pais> list = FactoryDAO.getPaisDAO().getPaises();

            //Creamos la matriz
            matrix = new Object[list.size()][5];

            for (int i = 0, n = list.size(); i < n; i++) {
                matrix[i][0] = list.get(i).getId();
                matrix[i][1] = list.get(i).getNombre();
                matrix[i][2] = new ButtonUI("Modificar");
                matrix[i][3] = new ButtonUI("Eliminar");
            }
            //Asignamos la nueva matriz a la tabla
            model = new TableModelUI(matrix,titles);
        }

        table.setModel(model);
    }
}