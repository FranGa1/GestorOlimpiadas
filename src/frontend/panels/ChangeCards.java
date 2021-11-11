package frontend.panels;

import backend.MiConnection;
import frontend.Aplicacion;
import javax.swing.*;
import java.awt.*;

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
                frame.setSize(550, 420);
                frame.setTitle("Gestor de Olimpiadas");
            }
            case "Configuracion" -> {
                cl.show(cardPanel, cardName);
                frame.setSize(500, 230);
                frame.setTitle("Gestor de Olimpiadas - CONFIGURACION");
            }
            case "AddPais" -> {
                cl.show(cardPanel, cardName);
                frame.setSize(500, 220);
                frame.setTitle("Gestor de Olimpiadas - NUEVO PAIS");
            }
            case "AddDeportista" -> {
                //Actualizamos los comboBox
                if (!MiConnection.nullConnection()) CreateAddDeportista.updateCB();

                cl.show(cardPanel, cardName);
                frame.setSize(520, 450);
                frame.setTitle("Gestor de Olimpiadas - NUEVO DEPORTISTA");
            }
            case "DeportistasTable" -> {
                //Actualizamos la tabla
                CreateDeportistaTable.updateTableDeportistas();

                cl.show(cardPanel, cardName);
                frame.setSize(800, 450);
                frame.setTitle("Gestor de Olimpiadas - DEPORTISTAS");
            }
            case "PaisesTable" -> {
                //Actualizamos la tabla
                CreatePaisTable.updateTablePais();

                cl.show(cardPanel, cardName);
                frame.setSize(530, 420);
                frame.setTitle("Gestor de Olimpiadas - PAISES");
            }
        }
        update(cardName);
    }

    private static void update(String cardName){
        ChangeCards.anterior = actual;
        ChangeCards.actual = cardName;
    }

}