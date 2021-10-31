package frontend;

import javax.swing.*;
import java.awt.*;

public class Gestor extends JFrame {

    //Elementos en el frame
    private CardLayout cl;
    private String user;
    private String passwd;

    //Constructor
    public Gestor(){
        setTitle("Gestor de Olimpiadas");
        setSize(400, 400);
        user = "";
        passwd = "";

        cl = new CardLayout();
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cl);

        cardPanel.setBackground(Color.RED);

        Panels panels = new Panels(cl, cardPanel, this);
        JPanel mainPanel = panels.CreateMainPanel();
        JPanel card2 = panels.CreateConfig();
        JPanel addPais = panels.CreateAddPais();

        //Agregamos las cards
        cardPanel.add(mainPanel, "Menu");
        cardPanel.add(card2, "Configuracion");
        cardPanel.add(addPais, "AddPais");

        //Agregamos al frame
        add(cardPanel);
    }

    public static void main(String[] args)
    {

        // Creating Object of CardLayoutDemo class.
        Gestor cl = new Gestor();

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
