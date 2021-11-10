package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.TableUI;
import frontend.changeDefaults.WPanel;
import objetos.Pais;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreatePaisTable {

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

        MiConnection.login("root", "fran");
        PaisDAO pDAO = FactoryDAO.getPaisDAO();

        //Creamos la tabla
        TableUI table = new TableUI(createTable(pDAO.getPaises()));
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

        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);

        return panel;
    }

    private static Object[][] createTable(List<Pais> paises){

        int rows = paises.size();
        int cols = 4;

        Object [][] matrixPaises = new Object[rows][cols];

        for (int i=0; i<rows; i++){
            matrixPaises[i][0] = paises.get(i).getId();
            matrixPaises[i][1] = paises.get(i).getNombre();
            matrixPaises[i][2] = new ButtonUI("Modificar");
            matrixPaises[i][3] = "EDITAR";
        }

        for (Pais pais : paises)
            System.out.println(pais.getId() + " "+ pais.getNombre());

        return matrixPaises;
    }
}
