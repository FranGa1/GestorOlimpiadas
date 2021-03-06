package frontend.panels;

import frontend.changeDefaults.TransparentPanel;
import frontend.changeDefaults.buttons.ButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateMenu {

    private static final ImageIcon ICONO_CONECTADO = new ImageIcon(new ImageIcon(CreateMenu.class.getResource("/img/conectadoOriginal2.png")).getImage().getScaledInstance(200, 60, Image.SCALE_DEFAULT));

    private static final ImageIcon ICONO_DESCONECTADO = new ImageIcon(new ImageIcon(CreateMenu.class.getResource("/img/desconectadoOriginal2.png"))
            .getImage().getScaledInstance(230, 60, Image.SCALE_DEFAULT));

    private static JLabel conectionStatus;
    private static JLabel headerLbl;

    public static JPanel create()  {

        //Creamos los paneles
        JPanel mainPanel = new TransparentPanel();
        JPanel panelC = new TransparentPanel();
        JPanel panelN = new TransparentPanel();
        JPanel header = new TransparentPanel();
        JPanel panelNyC = new TransparentPanel();

        //Creamos los labels
        conectionStatus = new JLabel();
        headerLbl = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 40));
        header.setBackground(new Color(0xE30D28));
        headerLbl.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //Creamos los botones
        JButton deportistas = new ButtonUI(" Deportistas", new ImageIcon(CreateMenu.class.getResource("/img/deportistas.png")), 180, 85);
        JButton paises = new ButtonUI(" Paises", new ImageIcon(CreateMenu.class.getResource("/img/world.png")), 180, 85);
        JButton disciplinas = new ButtonUI(" Disciplinas", new ImageIcon(CreateMenu.class.getResource("/img/disciplinas.png")), 180, 85);

        //Armamos el panel central
        panelC.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridy = 0;
        c.gridx = 0;
        panelC.add(deportistas);
        c.gridx = 1;
        panelC.add(paises, c);
        c.gridx = 2;
        panelC.add(disciplinas, c);

        //Agregamos los botones sin definir
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 3; j++){
                c.gridy = i + 1;
                c.gridx = j;
                JButton btn = new ButtonUI("Sin Definir",140, 85);
                panelC.add(btn, c);
            }
        }

        //Creamos el boton config
        panelN.setLayout(new BorderLayout());
        JButton btnConfig = new JButton(new ImageIcon(new ImageIcon(CreateMenu.class.getResource("/img/dbConfigIconWhite.png"))
                .getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        btnConfig.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
        btnConfig.setContentAreaFilled(false);

        //Creamos la imagen de estado
        conectionStatus = new JLabel();
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

        paises.addActionListener(e -> ChangeCards.swap("PaisesTable"));

        panelNyC.setLayout(new BorderLayout());
        panelNyC.add(panelC, BorderLayout.CENTER);
        panelNyC.add(panelN, BorderLayout.NORTH);


        //Construimos el panel final
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(panelNyC, BorderLayout.CENTER);

        mainPanel.setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));

        return mainPanel;
    }

    public static void setConnected(){
        conectionStatus.setIcon(ICONO_CONECTADO);
    }

    public static void setDisconnected(){
        conectionStatus.setIcon(ICONO_DESCONECTADO);
    }
}
