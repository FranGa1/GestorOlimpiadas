package frontend.panels;

import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.WPanel;

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

        c.gridx = 0;
        panelC.add(new ButtonUI("1"), c);
        c.gridx = 1;
        panelC.add(new ButtonUI("2"), c);
        c.gridx = 2;
        panelC.add(new ButtonUI("3"), c);

        c.gridx = 0;
        c.gridy = 1;
        JButton nuevoPais = new ButtonUI("Nuevo Pais");
        panelC.add(nuevoPais, c);
        c.gridx = 1;
        JButton nuevoDeportista = new ButtonUI("Nuevo Deportista");
        panelC.add(nuevoDeportista, c);

        c.gridx = 2;
        panelC.add(new ButtonUI("Sin Definir"), c);

        c.gridx = 0;
        c.gridy = 2;
        panelC.add(new ButtonUI("Sin Definir"), c);
        c.gridx = 1;
        panelC.add(new ButtonUI("Sin Definir"), c);
        c.gridx = 2;
        panelC.add(new ButtonUI("Sin Definir"), c);

        //Armamos el panel norte
//        panelN.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        JButton btnConfig = new JButton(new ImageIcon("Files/dbConfig2.png"));
//        btnConfig.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
//        btnConfig.setContentAreaFilled(false);
//        panelN.add(btnConfig);

        panelN.setLayout(new BorderLayout());
        JButton btnConfig = new JButton(new ImageIcon("Files/dbConfigIcon2.png"));
        btnConfig.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
        btnConfig.setContentAreaFilled(false);
        panelN.add(btnConfig, BorderLayout.EAST);
        JLabel conectionStatus = new JLabel(new ImageIcon(URL));
        conectionStatus.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
        panelN.add(conectionStatus, BorderLayout.WEST);


        //Listeners
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("Configuracion");
            }
        });

        nuevoPais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("AddPais");
            }
        });

        nuevoDeportista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swap("AddDeportista");
            }
        });


        //Insertamos ambos en el panel final
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panelC, BorderLayout.CENTER);
        mainPanel.add(panelN, BorderLayout.NORTH);

        mainPanel.setSize(400, 400);
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
