package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import backend.exceptions.PaisExistsException;
import backend.exceptions.PaisUsedException;
import frontend.changeDefaults.LabelsUI;
import frontend.changeDefaults.TextFieldUI;
import frontend.changeDefaults.WPanel;
import frontend.changeDefaults.buttons.ButtonUI;
import objetos.Pais;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class CreateModifyPais {

    protected static JTextField input;
    protected static JTextField error;
    private static JButton save;
    private static JLabel headerLbl;

    public static JPanel create(){

        //Creamos los paneles
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel centerS = new WPanel();
        JPanel centerC = new WPanel();
        JPanel bottom = new WPanel();

        //Creamos los labels
        JLabel label = new LabelsUI("Nombre: ");
        headerLbl = new JLabel("",  SwingConstants.CENTER);

        //Creamos los text fields
        input = new TextFieldUI(30);
        error = new TextFieldUI(30);
        error.setEditable(false);
        error.setBorder(null);


        //Creamos los botones
        save = new ButtonUI("Guardar");
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
        centerC.add(input);

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

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
                ChangeCards.swapPrev();
            }
        });

        input.getDocument().addDocumentListener(new DocumentListener() {
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
                Pattern p = Pattern.compile("^[a-zA-Z\\s]+$|^$");
                if (!p.matcher(input.getText()).find()){
                    error.setText("ERROR: Ingrese solo letras o espacios");
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

    //Limpia los datos ingresados por el usuario
    protected static void cleanFields(){
        input.setText("");
        error.setText("");
    }

    public static void setEditable(Pais pais){
        //Seteamos el listener para el boton save
        for(ActionListener act : save.getActionListeners()) {
            save.removeActionListener(act);
        }
        save.addActionListener(new SaveEditListener(pais));

        //seteamos el header y los valores de las casillas
        headerLbl.setText("EDITAR PAIS");
        input.setText(pais.getNombre());
    }

    public static void setAdd(){
        //Seteamos el listener para el boton save
        for(ActionListener act : save.getActionListeners()) {
            save.removeActionListener(act);
        }
        save.addActionListener(new SaveNewListener());

        //seteamos el header
        headerLbl.setText("AGREGAR PAIS");
    }

    private static class SaveNewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            PaisDAO pDAO = FactoryDAO.getPaisDAO();
            //Chequeamos que no haya errores o que el campo no este vacio
            if (MiConnection.nullConnection()) {
                //Si no hay coneccion con la BD
                JOptionPane.showMessageDialog(null,
                        "No se conecto a la base de datos", "Error Message",
                        JOptionPane.WARNING_MESSAGE);
            }else if (!error.getText().equals("")){
                //Si hay algun error
                JOptionPane.showMessageDialog(null,
                        "Solucione primero los errores", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }else if (input.getText().equals("")){
                //Si el campo esta vacio
                JOptionPane.showMessageDialog(null,
                        "El campo es obligatorio", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }
            else{
                //Cargamos el pais
                try {
                    Pais p = new Pais(input.getText().trim());
                    pDAO.cargar(p);
                    JOptionPane.showMessageDialog(null, "Agregado Exitoso", "Action Complete",
                            JOptionPane.INFORMATION_MESSAGE);
                    cleanFields();
                } catch (SQLException ex) {
                    System.out.println("No se pudo cargar el pais");
                    ex.printStackTrace();
                } catch (PaisExistsException ex){
                    JOptionPane.showMessageDialog(null, "El Pais ya se encuentra en la base de datos",
                            "Error message",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex){
                    System.out.println("Hubo un problema. Intente de nuevo");
                }
                ChangeCards.swapPrev();
            }
        }
    }

    private static class SaveEditListener implements ActionListener{

        private static Pais pais;

        public SaveEditListener(Pais pais) {
            SaveEditListener.pais = pais;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            PaisDAO pDAO = FactoryDAO.getPaisDAO();
            //Chequeamos que no haya errores o que el campo no este vacio
            if (MiConnection.nullConnection()) {
                //Si no hay coneccion con la BD
                JOptionPane.showMessageDialog(null,
                        "No se conecto a la base de datos", "Error Message",
                        JOptionPane.WARNING_MESSAGE);
            }else if (!error.getText().equals("")){
                //Si hay algun error
                JOptionPane.showMessageDialog(null,
                        "Solucione primero los errores", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }else if (input.getText().equals("")){
                //Si el campo esta vacio
                JOptionPane.showMessageDialog(null,
                        "El campo es obligatorio", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }
            else{
                //Cargamos el pais
                try {
                    //Modificamos el pais
                    pais.setNombre(input.getText());

                    //Actualizamos el Pais
                    pDAO.editar(pais);
                    JOptionPane.showMessageDialog(null, "Se edito exitosamente", "Action Complete",
                            JOptionPane.INFORMATION_MESSAGE);
                    cleanFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Verfique la conexion a la base de datos",
                            "Error message",
                            JOptionPane.ERROR_MESSAGE);
                }catch (PaisExistsException ex){
                    JOptionPane.showMessageDialog(null, "El Pais ya se encuentra en la base de datos",
                            "Error message",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Hubo un problema. Intente mas tarde",
                            "Error message",
                            JOptionPane.ERROR_MESSAGE);
                }
                ChangeCards.swapPrev();
            }
        }
    }
}