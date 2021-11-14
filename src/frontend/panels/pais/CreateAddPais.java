package frontend.panels.pais;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import frontend.panels.ChangeCards;
import objetos.Pais;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateAddPais extends CreateModifyPais{

    public static JPanel create(){
        setSaveListener(new SaveListener());
        setTitlePanel("AGREGAR PAIS");
        return CreateModifyPais.create();
    }

    //Listener para el boton save
    private static class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            PaisDAO pDAO = FactoryDAO.getPaisDAO();
            Pais p = new Pais(input.getText().trim());

            //Chequeamos que no haya errores o que el campo no este vacio
            if (MiConnection.nullConnection()) {
                //Si no hay coneccion con la BD
                JOptionPane.showMessageDialog(null,
                        "No se conecto a la base de datos", "Error Message",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!error.getText().equals("")) {
                //Si hay algun error
                JOptionPane.showMessageDialog(null,
                        "Solucione primero los errores", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            } else if (input.getText().equals("")) {
                //Si el campo esta vacio
                JOptionPane.showMessageDialog(null,
                        "El campo es obligatorio", "Error Message",
                        JOptionPane.ERROR_MESSAGE);

            } else {
                //Cargamos el jugador
                try {
                    pDAO.cargar(p);
                } catch (SQLException ex){
                    System.out.println("Problema con la base de datos");
                } catch (Exception ex){
                    System.out.println("Hubo un problema. Intente de nuevo");
                }
                JOptionPane.showMessageDialog(null, "Agregado Exitoso", "Action Complete",
                        JOptionPane.INFORMATION_MESSAGE);
                cleanFields();
                ChangeCards.swapPrev();
            }
        }
    }
}
