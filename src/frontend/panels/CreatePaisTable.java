package frontend.panels;

import backend.MiConnection;
import frontend.Aplicacion;
import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.PasswordFieldUI;
import frontend.changeDefaults.TextFieldUI;
import frontend.changeDefaults.WPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePaisTable {
    private static JTextField userField;
    private static JTextField passwdField;

    public static JPanel create(Aplicacion frame){

        //Creamos los paneles
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel data = new WPanel();
        JPanel buttons = new WPanel();

        //Creamos los botones
        JButton btnOK = new ButtonUI("Nuevo");
        JButton btnCancel = new ButtonUI("Volver");

        //Creamos los labels
        JLabel headerLbl = new JLabel("PAISES", SwingConstants.CENTER);

        //Creamos los textFields
        userField = new TextFieldUI(30);
        passwdField = new PasswordFieldUI(30);

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
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
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

                //Si la coneccion es exitosa
                if (MiConnection.validCredentials(userField.getText(), passwdField.getText())) {
                    ChangeCards.swap("MenuC");
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "Usuario o contraseña invalidos", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
                cleanFields();
            }
        });

        btnReset.addActionListener(e -> {

            //Reset user and password
            frame.setUser("");
            frame.setPasswd("");

            ChangeCards.swap("MenuD");

            cleanFields();

        });

        btnCancel.addActionListener(e -> {

            ChangeCards.swapPrev();

            cleanFields();
        });

        // Permite apretar enter para guardar
//        passwdField.addKeyListener(new KeyAdapter() {
//
//            public void keyPressed(KeyEvent e) {
//                Pattern p = Pattern.compile("^[a-zA-Z\\s]+$|^$");
//                if (!p.matcher(passwdField.getText()).find()){
//                    error.setText("ERROR: Ingrese solo letras o espacios");
//                    error.setBackground(Color.RED);
//                }
//                else {
//                    error.setText("");
//                    error.setBackground(null);
//                }
//            }
//        });
        return panel;
    }

    private static void cleanFields(){
        userField.setText("");
        passwdField.setText("");
    }
}
