package Tests;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class RegisterPage extends JFrame {

    public RegisterPage(){

        //Creamos los paneles
        JPanel panel = new JPanel();
        JPanel center = new JPanel();
        JPanel centerN = new JPanel();
        JPanel centerC = new JPanel();
        JPanel bottom = new JPanel();

        //Creamos los labels
        JLabel label = new JLabel("Nombre: ");

        //Creamos el text field
        JTextField inpt = new JTextField(30);
        JTextField error = new JTextField(30);
        error.setEditable(false);

        //Creamos los botones
        JButton btn = new JButton("Guardar");

        //Construimos el panel bottom
        bottom.setLayout(new FlowLayout());
        bottom.add(btn);

        //Construimos el panel centerN
        centerN.setLayout(new FlowLayout());
        //error.setBorder(BorderFactory.createMatteBorder(10, 10, 0, 10, Color.RED ));
        centerN.add(error);

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
        center.add(centerN, BorderLayout.SOUTH);


        //Construimos el panel final
        panel.setLayout(new BorderLayout());
        panel.add(center, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));


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


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //agregamos al frame
        this.add(panel);
        this.setSize(430, 200);
    }

    public static void main(String[] args) {
        RegisterPage r = new RegisterPage();
        r.setVisible(true);
    }
}
