package frontend.panels;

import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import frontend.changeDefaults.ButtonUI;
import frontend.changeDefaults.TextFieldUI;
import frontend.changeDefaults.WPanel;
import objetos.Pais;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class CreateAddPais {

    public static JPanel create(){

        //Creamos los paneles
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel centerS = new WPanel();
        JPanel centerC = new WPanel();
        JPanel bottom = new WPanel();

        //Creamos los labels
        JLabel label = new JLabel("Nombre: ");
        JLabel headerLbl = new JLabel("CREAR NUEVO PAIS",  SwingConstants.CENTER);

        //Creamos los text fields
        JTextField inpt = new TextFieldUI(30);
        JTextField error = new TextFieldUI(30);
        error.setEditable(false);

        //Creamos los botones
        JButton save = new ButtonUI("Guardar");
        JButton cancel = new ButtonUI("Cancelar");

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
        error.setBorder(null);
        error.setBackground(Color.WHITE);

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
                PaisDAO pDAO = FactoryDAO.getPaisDAO();
                Pais pais = new Pais(inpt.getText());
                if (!pDAO.existe(pais)) {
                    pDAO.cargar(pais);
                    ChangeCards.swapPrev();
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            "El pais ya se encuentra en la Base de Datos", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeCards.swapPrev();
            }
        });

        return panel;
    }
}
