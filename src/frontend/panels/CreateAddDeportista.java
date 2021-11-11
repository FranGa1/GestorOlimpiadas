package frontend.panels;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.DeportistaDAO;
import frontend.changeDefaults.buttons.ButtonUI;
import frontend.changeDefaults.ComboBoxUI;
import frontend.changeDefaults.TextFieldUI;
import frontend.changeDefaults.WPanel;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class CreateAddDeportista {

    private static final int LONGITUD = 30;

    private static TextFieldUI[] textFields;
    private static JTextField error;
    private static LinkedList<String> cola;
    private static JComboBox<String> paisCB;
    private static JComboBox<String> disciplinaCB;

    public static JPanel create(){

        //Creamos los panels
        JPanel panel = new WPanel();
        JPanel header = new WPanel();
        JPanel center = new WPanel();
        JPanel centerC = new WPanel();
        JPanel centerS = new WPanel();
        JPanel bottom = new WPanel();

        //Creamos los botones
        JButton guardar = new ButtonUI("Guardar");
        JButton cancelar = new ButtonUI("Cancelar");

        //Creamos los labels
        JLabel headerLbl = new JLabel("AGREGAR DEPORTISTA", SwingConstants.CENTER);
        JLabel[] labels = new JLabel[6];
        labels[0] = new JLabel("Nombre: ");
        labels[1] = new JLabel("Apellido: ");
        labels[2] = new JLabel("E-mail: ");
        labels[3] = new JLabel("Telefono: ");
        labels[4] = new JLabel("Pais: ");
        labels[5] = new JLabel("Disciplina: ");

        //Creamos los Text fields
        error = new JTextField(40);
        error.setEditable(false);
        error.setBorder(null);
        error.setBackground(Color.WHITE);

        textFields = new TextFieldUI[4];
        for (int i = 0; i < 4; i++ ) {
            textFields[i] = new TextFieldUI(LONGITUD);
        }
        paisCB = new ComboBoxUI<>(new String[]{""});
        disciplinaCB = new ComboBoxUI<>(new String[]{""});

        //Construimos el header
        header.setLayout(new BorderLayout());
        headerLbl.setFont(new Font("Bevan", Font.PLAIN, 30));
        header.setBackground(Color.decode("#4BB99E"));
        header.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        header.add(headerLbl);

        //Construimos el centro sur
        centerS.setLayout(new FlowLayout());
        centerS.add(error);

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
            if (i == 4) centerC.add(paisCB, c);
            else if (i == 5) centerC.add(disciplinaCB, c);
            else centerC.add(textFields[i], c);
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

        //Creamos una cola de errores
        cola = new LinkedList<>();
        cola.add("");

        //Listeners
        guardar.addActionListener(new SaveListener());
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
                ChangeCards.swapPrev();
            }
        });

        //Nombre
        textFields[0].getDocument().addDocumentListener(
                new LetrasListener("^[a-zA-Z\\s]+$|^$",
                        " ERROR: Ingrese solo letras o espacios en el nombre",
                        0));

        //Apellido
        textFields[1].getDocument().addDocumentListener(new LetrasListener("^[a-zA-Z\\s]+$|^$",
                " ERROR: Ingrese solo letras o espacios en el apellido",
                1));

        //Email
        textFields[2].getDocument().addDocumentListener(new LetrasListener("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$|^$",
                " ERROR: Ingrese un mail valido. (FORMATO: abc@abc.abc)",
                2));

        //Telefono
        textFields[3].getDocument().addDocumentListener(new LetrasListener("^[0-9]{3,}$|^$",
                " ERROR: Solo numeros para el telefono (Minimo 3 numeros )",
                3));

        //Combobox


        return panel;
    }

    //Listener para el chequeo en tiempo real
    private static class LetrasListener implements DocumentListener {

        private final String regex;
        private final String errorText;
        private final int textFieldNum;

        public LetrasListener(String regex, String errorText, int textFieldNum){
            this.regex = regex;
            this.textFieldNum = textFieldNum;
            this.errorText = errorText;
        }

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
            Pattern p = Pattern.compile(regex);
            String content = textFields[textFieldNum].getText();
            String errorStr = errorText;
            String errorActual = error.getText();

            if (!p.matcher(content).find()) {

                if (errorActual.equals("")){
                    error.setBackground(new Color(0xF13333));
                    error.setText(errorStr);
                }
                else if (!errorActual.equals(errorStr)){
                    cola.add(errorStr);
                }
            }
            else if (errorActual.equals(errorStr)){
                String last = cola.getLast();
                if (last.equals("")){
                    error.setBackground(null);
                    error.setText("");
                }
                else{
                    error.setText(last);
                    cola.removeLast();
                }
            }
        }
    }

    //Listener para el boton save
    private static class SaveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DeportistaDAO dDAO = FactoryDAO.getDeportistaDAO();

            boolean empty = false;
            for (JTextField tf : textFields){
                if (tf.getText().equals("")){
                    empty = true;
                }
            }
            if (!empty && (paisCB.getSelectedItem().equals("") || disciplinaCB.getSelectedItem().equals(""))) empty = true;

            //Chequeamos que no haya errores o que el campo no este vacio
            if (MiConnection.nullConnection()) {
                //Si no hay coneccion con la BD
                JOptionPane.showMessageDialog(null,
                        "No se conecto a la base de datos", "Warning Message",
                        JOptionPane.WARNING_MESSAGE);
            }else if (!error.getText().equals("")){
                //Si hay algun error
                JOptionPane.showMessageDialog(null,
                        "Solucione primero los errores", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }else if (empty){
                //Si el campo esta vacio
                JOptionPane.showMessageDialog(null,
                        "Todos los campos son obligatorios", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            }else{
                Deportista deportista = new Deportista();
                deportista.setNombres(textFields[0].getText());
                deportista.setApellidos(textFields[1].getText());
                deportista.setEmail(textFields[2].getText());
                deportista.setTelefono(textFields[3].getText());
                deportista.setPais(new Pais(paisCB.getSelectedItem().toString()));
                List<Disciplina> listD = new LinkedList<>();
                listD.add(new Disciplina(disciplinaCB.getSelectedItem().toString()));
                deportista.setDisciplinas(listD);

                //Cargamos el deportista
                dDAO.cargar(deportista);
                JOptionPane.showMessageDialog(null, "Agregado Exitoso", "Action Complete",
                        JOptionPane.INFORMATION_MESSAGE);
                cleanFields();
                ChangeCards.swapPrev();
            }

        }
    }


    //Borra los datos ingresados
    private static void cleanFields(){
        for (JTextField element : textFields){
            element.setText("");
        }
        error.setText("");
        paisCB.setSelectedIndex(0);
        disciplinaCB.setSelectedIndex(0);
    }

    //Actualizamos los ComboBox
    public static void updateCB(){
        List<String> pais = FactoryDAO.getPaisDAO().getPaisesAsStrings();
        List<String> disciplinas = FactoryDAO.getDisciplinaDAO().getDisciplinasAsStrings();
        pais.add(0, "" );
        paisCB.setModel(new DefaultComboBoxModel<>(pais.toArray(new String[0])));
        disciplinas.add(0, "" );
        disciplinaCB.setModel(new DefaultComboBoxModel<>(disciplinas.toArray(new String[0])));
    }
}
