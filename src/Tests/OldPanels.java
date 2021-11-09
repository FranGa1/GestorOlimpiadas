package Tests;

import frontend.Aplicacion;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class OldPanels {

    private static CardLayout cl;
    private static JPanel cardPanel;
    private static Aplicacion frame;

    public OldPanels(CardLayout cl, JPanel cardPanel, Aplicacion frame) {
        this.cl = cl;
        this.cardPanel = cardPanel;
        this.frame = frame;
    }


    static void swap(String cardName){
        switch (cardName) {
            case "Menu" -> {
                cl.show(cardPanel, "Menu");
                frame.setSize(400, 400);
                frame.setName("Gestor de Olimpiadas");

//                if (frame.userEstaLogueado())
//                    cl.show(cardPanel, "MenuConectado");
//                else
//                    cl.show(cardPanel, "MenuDesconectado");

            }
            case "Configuracion" -> {
                cl.show(cardPanel, "Configuracion");
                frame.setSize(430, 230);
                frame.setName("Gestor de Olimpiadas - CONFIGURACION");
            }
            case "AddPais" -> {
                cl.show(cardPanel, "AddPais");
                frame.setSize(500, 220);
                frame.setName("Gestor de Olimpiadas - NUEVO PAIS");
            }
            case "AddDeportista" ->{
                cl.show(cardPanel, "AddDeportista");
                frame.setSize(500, 350);
                frame.setName("Gestor de Olimpiadas - NUEVO DEPORTISTA");
            }
        }
    }

    public static JPanel createMainPanel() {

        //Creamos los paneles
        JPanel mainPanel  =new JPanel();
        JPanel panelC = new JPanel();
        JPanel panelN = new JPanel();

        //Armamos el panel central
        panelC.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        panelC.add(new JButton("1"), c);
        c.gridx = 1;
        panelC.add(new JButton("2"), c);
        c.gridx = 2;
        panelC.add(new JButton("3"), c);

        c.gridx = 0;
        c.gridy = 1;
        JButton nuevoPais = new JButton("Nuevo Pais");
        panelC.add(nuevoPais, c);
        c.gridx = 1;
        JButton nuevoDeportista = new JButton("Nuevo Deportista");
        panelC.add(nuevoDeportista, c);
        c.gridx = 2;
        panelC.add(new JButton("Sin Definir"), c);

        c.gridx = 0;
        c.gridy = 2;
        panelC.add(new JButton("Sin Definir"), c);
        c.gridx = 1;
        panelC.add(new JButton("Sin Definir"), c);
        c.gridx = 2;
        panelC.add(new JButton("Sin Definir"), c);

        //Armamos el panel norte
        panelN.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnConfig = new JButton(new ImageIcon("Files/dbConfig2.png"));
        btnConfig.setBorder(BorderFactory.createEmptyBorder(5,0 ,0,5));
        //btnConfig.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        btnConfig.setContentAreaFilled(false);
        panelN.add(btnConfig);


        //Listeners
        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                swap("Configuracion");
            }
        });

        nuevoPais.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("AddPais");
            }
        });

        nuevoDeportista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("AddDeportista");
            }
        });


        //Insertamos ambos en el panel final
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panelC, BorderLayout.CENTER);
        mainPanel.add(panelN, BorderLayout.NORTH);

        mainPanel.setSize(400, 400);

        return mainPanel;
    }

    public static JPanel createConfig(){

        //Creamos los paneles
        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel data = new JPanel();
        JPanel buttons = new JPanel();

        //Creamos los botones
        JButton btnOK = new JButton("OK");
        JButton btnCancel = new JButton("Cancelar");
        JButton btnReset = new JButton("Reset Data");

        //Creamos los labels
        JLabel userLbl = new JLabel("Usuario: ");
        JLabel passwdLbl = new JLabel("Contraseña: ");
        JLabel headerLbl = new JLabel("CONFIGURACION", SwingConstants.CENTER);

        //Creamos los textFields
        JTextField userField = new JTextField(30);
        JTextField passwdField = new JTextField(30);

        //Agregamos a data
        data.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        data.add(userLbl, c);
        c.gridx = 1;
        c.gridy = 0;
        data.add(userField, c);
        c.gridx = 0;
        c.gridy = 1;
        data.add(passwdLbl, c);
        c.gridx = 1;
        c.gridy = 1;
        data.add(passwdField, c);

        //Agregamos a buttons
        buttons.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        buttons.add(btnOK, c);
        c.gridx = 1;
        c.gridy = 0;
        buttons.add(btnReset, c);
        c.gridx = 2;
        c.gridy = 0;
        buttons.add(btnCancel, c);

        //Agregamos a header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(Color.decode("#A2AEAC"));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //Agregamos a panel
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(data, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        //Agregamos borde abajo
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));


        //Listeners
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Guardamos la informacion
                frame.setUser(userField.getText());
                frame.setPasswd(passwdField.getText());

                swap("Menu");

                System.out.println("Usuario: " + frame.getUser());
                System.out.println("Contraseña: " + frame.getPasswd());
            }
        });

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Reset user and password
                frame.setUser("");
                frame.setPasswd("");

                swap("Menu");

                System.out.println("Usuario: " + frame.getUser());
                System.out.println("Contraseña: " + frame.getPasswd());
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                swap("Menu");

                System.out.println("Usuario: " + frame.getUser());
                System.out.println("Contraseña: " + frame.getPasswd());
            }
        });
        return panel;
    }

    public static JPanel createAddPais(){

        //Creamos los paneles
        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel center = new JPanel();
        JPanel centerS = new JPanel();
        JPanel centerC = new JPanel();
        JPanel bottom = new JPanel();

        //Creamos los labels
        JLabel label = new JLabel("Nombre: ");
        JLabel headerLbl = new JLabel("CREAR NUEVO PAIS",  SwingConstants.CENTER);

        //Creamos los text fields
        JTextField inpt = new JTextField(30);
        JTextField error = new JTextField(30);
        error.setEditable(false);

        //Creamos los botones
        JButton save = new JButton("Guardar");
        JButton cancel = new JButton("Cancelar");

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(Color.decode("#4BB99E"));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //Construimos el panel bottom
        bottom.setLayout(new FlowLayout());
        bottom.add(save);
        bottom.add(cancel);

        //Construimos el panel centerS
        centerS.setLayout(new FlowLayout());
        centerS.add(error);

        //Construimos el panel centerC
        centerC.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        c.gridx = 0;
        c.gridy = 0;
        centerC.add(label);
        c.gridx = 1;
        centerC.add(inpt);

        //Construimos el panel Center
        center.setLayout(new BorderLayout());
        center.add(centerC, BorderLayout.CENTER);
        center.add(centerS, BorderLayout.SOUTH);


        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(center, BorderLayout.CENTER);
        panel.add(header, BorderLayout.NORTH);
        panel.add(bottom, BorderLayout.SOUTH);


        //listeners
        inpt.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                Pattern p = Pattern.compile("^[a-zA-Z\\s]+$");
                if (!p.matcher(inpt.getText()).find()){
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Please enter number bigger than 0", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
                    error.setText("ERROR: Ingrese solo letras o espacios");
                    error.setBackground(Color.RED);
                }
                else {
                    error.setText("");
                    error.setBackground(null);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("Menu");
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("Menu");
            }
        });

        return panel;
    }

    public static JPanel createAddDeportista(){

        //Creamos los panels
        JPanel panel = new JPanel();
        JPanel header = new JPanel();
        JPanel center = new JPanel();
        JPanel centerC = new JPanel();
        JPanel centerS = new JPanel();
        JPanel bottom = new JPanel();

        //Creamos los botones
        JButton guardar = new JButton("Guardar");
        JButton cancelar = new JButton("Cancelar");

        //Creamos los labels
        JLabel headerLbl = new JLabel("AGREGAR JUGADOR", SwingConstants.CENTER);
        JLabel[] labels = new JLabel[6];
        labels[0] = new JLabel("Nombre: ");
        labels[1] = new JLabel("Apellido: ");
        labels[2] = new JLabel("E-mail: ");
        labels[3] = new JLabel("Telefono: ");
        labels[4] = new JLabel("Pais: ");
        labels[5] = new JLabel("Disciplina: ");

        //Creamos los Text fields
        JTextField error = new JTextField(30);
        error.setEditable(false);
        JTextField[] textFields = new JTextField[6];
        for (int i = 0; i < 6; i++ ) {
            textFields[i] = new JTextField(30);
        }

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(Color.decode("#4BB99E"));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //Construimos el centro sur
        centerS.setLayout(new BorderLayout());
        centerS.add(error, BorderLayout.CENTER);

        //Construimos el centro medio
        centerC.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        for (int i = 0; i < 6; i++){
            c.gridy = i;
            c.gridx = 0;
            centerC.add(labels[i], c);
            c.gridx = 1;
            centerC.add(textFields[i], c);
        }

        //Construimos el centro
        center.setLayout(new BorderLayout());
        center.add(centerC, BorderLayout.CENTER);
        center.add(centerS, BorderLayout.SOUTH);

        //Construimos el bottom
        bottom.setLayout(new FlowLayout());
        bottom.add(guardar);
        bottom.add(cancelar);


        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(header, BorderLayout.NORTH);
        panel.add(center, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);


        //Listeners

        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("Menu");
            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swap("Menu");
            }
        });

        textFields[0].getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                Pattern p = Pattern.compile("^[a-zA-Z\\s]+$");
                if (!p.matcher(textFields[0].getText()).find()){
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Please enter number bigger than 0", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
                    error.setText("ERROR: Ingrese solo letras o espacios AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    error.setBackground(Color.RED);
                }
                else {
                    error.setText("");
                    error.setBackground(null);
                }
            }
        });
        textFields[1].getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                Pattern p = Pattern.compile("^[a-zA-Z\\s]+$");
                if (!p.matcher(textFields[1].getText()).find()){
//                    JOptionPane.showMessageDialog(null,
//                            "Error: Please enter number bigger than 0", "Error Message",
//                            JOptionPane.ERROR_MESSAGE);
                    error.setText("ERROR: Ingrese solo letras o espacios BBBBBBBBBBBBBBBBBBBBBB");
                    error.setBackground(Color.RED);
                }
                else {
                    error.setText("");
                    error.setBackground(null);
                }
            }
        });

        return panel;
    }
}
