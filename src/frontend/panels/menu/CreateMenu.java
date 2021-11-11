package frontend.panels.menu;

import frontend.changeDefaults.buttons.ButtonUI;
import frontend.changeDefaults.WPanel;
import frontend.panels.ChangeCards;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CreateMenu {

    private static String URL;

    public static JPanel create() {

        //Creamos los paneles
        JPanel mainPanel = new JPanel();
        JPanel panelC = new WPanel();
        JPanel panelN = new WPanel();

        //Armamos el panel central
        panelC.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridy = 0;
        c.gridx = 0;
        JButton deportistas = new ButtonUI("Deportistas");
        panelC.add(deportistas);
        c.gridx = 1;
        JButton paises = new ButtonUI("Paises");
        panelC.add(paises, c);
        c.gridx = 2;
        panelC.add(new ButtonUI("3"), c);

        //Agregamos los botones sin definir
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++){
                c.gridy = i + 1;
                c.gridx = j;
                JButton btn = new ButtonUI("Sin Definir");
                panelC.add(btn, c);
            }
        }

        //Armamos el panel norte

            //Creamos el boton config
        panelN.setLayout(new BorderLayout());
        JButton btnConfig = new JButton(new ImageIcon("Files/dbConfigIcon2.png"));
        btnConfig.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
        btnConfig.setContentAreaFilled(false);
            //Creamos la imagen de estado
        JLabel conectionStatus = new JLabel(new ImageIcon(URL));
        conectionStatus.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
            //Agregamos al panel
        panelN.add(btnConfig, BorderLayout.EAST);
        panelN.add(conectionStatus, BorderLayout.WEST);


        //Listeners
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("Configuracion");
            }
        });

        deportistas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("DeportistasTable");
            }
        });

        paises.addActionListener(e -> {
            ChangeCards.swap("PaisesTable");
        });


        //Construimos el panel final
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panelC, BorderLayout.CENTER);
        mainPanel.add(panelN, BorderLayout.NORTH);

        mainPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));

        return mainPanel;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        CreateMenu.URL = URL;
    }

}
