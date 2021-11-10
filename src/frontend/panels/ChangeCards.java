package frontend.panels;

import backend.MiConnection;
import backend.dao.implementacionesDAO.DisciplinaDAOjdbc;
import backend.dao.implementacionesDAO.PaisDAOjdbc;
import frontend.Aplicacion;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import static frontend.panels.CreateAddDeportista.updateCB;

public class ChangeCards {

    private static CardLayout cl;
    private static JPanel cardPanel;
    private static Aplicacion frame;
    private static String actual;
    private static String anterior;

    public static void swap(String cardName, CardLayout cl, JPanel cardPanel, Aplicacion frame) {
        ChangeCards.cl = cl;
        ChangeCards.cardPanel = cardPanel;
        ChangeCards.frame = frame;
        ChangeCards.actual = cardName;
        ChangeCards.anterior = cardName;
        swap(cardName);
    }


    //Muestra la card anterior
    static void swapPrev(){
        swap(anterior);
    }

    public static void swap(String cardName) {
        switch (cardName) {
            case "MenuD", "MenuC" -> {
                cl.show(cardPanel, cardName);
                frame.setSize(500, 380);
                frame.setName("Gestor de Olimpiadas");
            }
            case "Configuracion" -> {
                cl.show(cardPanel, cardName);
                frame.setSize(480, 230);
                frame.setName("Gestor de Olimpiadas - CONFIGURACION");
            }
            case "AddPais" -> {
                cl.show(cardPanel, cardName);
                frame.setSize(500, 220);
                frame.setName("Gestor de Olimpiadas - NUEVO PAIS");
            }
            case "AddDeportista" -> {
                //Actualizamos los comboBox
                if (!MiConnection.nullConnection()) CreateAddDeportista.updateCB();

                cl.show(cardPanel, cardName);
                frame.setSize(510, 420);
                frame.setName("Gestor de Olimpiadas - NUEVO DEPORTISTA");
            }
            case "DeportistasTable" -> {
                //Actualizamos la tabla
                if (!MiConnection.nullConnection()) CreateDeportistaTable.updateTable();

                cl.show(cardPanel, cardName);
                frame.setSize(700, 420);
                frame.setName("Gestor de Olimpiadas - DEPORTISTAS");
            }
        }
        update(cardName);
    }

    private static void update(String cardName){
        ChangeCards.anterior = actual;
        ChangeCards.actual = cardName;
    }

}