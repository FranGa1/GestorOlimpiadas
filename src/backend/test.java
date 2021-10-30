package backend;

import backend.dao.implementaciones.PaisDAOjdbc;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
//        Disciplina di = new Disciplina();
//        di.setID(1);
//        di.setNombre("asdf");
//
//        Deportista d = new Deportista();
//        d.setApellido("garay");
//        d.setNombre("fran");
//        d.setEmail("asdf");
//        d.setTelefono("9784235");

//        Pais p = new Pais();
//        p.setNombre("Argentina");
//        PaisDAOjdbc pDAO = new PaisDAOjdbc();
//        pDAO.cargar(p);

        System.out.println(MiConnection.getCon());

//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", "root", "fran");
    }
}
