package frontend;

import frontend.panels.*;
import frontend.panels.menu.CreateMenuConectado;
import frontend.panels.menu.CreateMenuDesconectado;

import javax.swing.*;
import java.awt.*;

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
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cl);

        cardPanel.setBackground(Color.RED);

        JPanel mainPanelConnected = CreateMenuConectado.create();
        JPanel mainPanelDesconectado = CreateMenuDesconectado.create();
        JPanel deportistasTable = CreateDeportistaTable.create();
        JPanel config = CreateConfig.create();
        JPanel addPais = CreateAddPais.create();
        JPanel addDeportista = CreateAddDeportista.create();

        //Agregamos las cards
        cardPanel.add(mainPanelConnected, "MenuC");
        cardPanel.add(mainPanelDesconectado, "MenuD");
        cardPanel.add(deportistasTable, "DeportistasTable");
        cardPanel.add(config, "Configuracion");
        cardPanel.add(addPais, "AddPais");
        cardPanel.add(addDeportista, "AddDeportista");

        //Agregamos al frame
        add(cardPanel);

        //Iniciamos la aplicacion en el menu e inicializamos la clase Change Cards
        ChangeCards.swap("MenuD", cl, cardPanel, this);

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
