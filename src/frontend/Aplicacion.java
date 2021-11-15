package frontend;

import backend.export.ExportCSV;
import frontend.changeDefaults.TransparentPanel;
import frontend.panels.*;
import frontend.panels.CreateMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Aplicacion extends JFrame {

    //Elementos en el frame
    private CardLayout cl;
    private String user;
    private String passwd;

    //Constructor
    public Aplicacion(){
        setTitle("Gestor de Olimpiadas");
        setSize(400, 400);
        user = "";
        passwd = "";

        cl = new CardLayout();
        JPanel cardPanel = new TransparentPanel();
        cardPanel.setLayout(cl);

        new ExportCSV(this);

        JPanel mainPanel = CreateMenu.create();
        CreateMenu.setDisconnected();
        JPanel deportistasTable = CreateDeportistaTable.create();
        JPanel paisesTable = CreatePaisTable.create();
        JPanel config = CreateConfig.create();
        JPanel addPais = CreateModifyPais.create();
        JPanel addDeportista = CreateModifyDeportista.create();

        //Agregamos las cards
        cardPanel.add(mainPanel, "Menu");
        cardPanel.add(deportistasTable, "DeportistasTable");
        cardPanel.add(config, "Configuracion");
        cardPanel.add(addPais, "ModifPais");
        cardPanel.add(addDeportista, "ModifDeportista");
        cardPanel.add(paisesTable, "PaisesTable");

        // Seteamos la imagen de fondo
        Icon img = new ImageIcon(new ImageIcon("Files/fondoepico.jpg").getImage().getScaledInstance(1100,650, Image.SCALE_SMOOTH));
        JLabel contentPane = new JLabel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setIcon(img);
        contentPane.add(cardPanel);
        setContentPane(contentPane);

        // Seteamos el icono de la aplicacion
        setIconImage(new ImageIcon("Files/fondoepico.jpg").getImage());

        setIconImage(new ImageIcon("Files/fondoepico.jpg").getImage());

        //Iniciamos la aplicacion en el menu e inicializamos la clase Change Cards
        ChangeCards.swap("Menu", cl, cardPanel, this);

    }

    public static void main(String[] args)
    {

        // Creating Object of CardLayoutDemo class.
        Aplicacion cl = new Aplicacion();

        // Function to set default operation of JFrame.
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visibility of JFrame.
        cl.setVisible(true);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
