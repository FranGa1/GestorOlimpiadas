package backend;

import backend.dao.FactoryDAO;
import objetos.Deportista;
import objetos.Pais;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    public static int prueba(int numero){
        return numero;
    }

    public static void main(String[] args) throws SQLException {
        MiConnection.login("root", "fran");
//        FactoryDAO.getPaisDAO().cargar(new Pais("franciass"));

        System.out.println(FactoryDAO.getPaisDAO().encontrar(1).getNombre());
    }
}
