package backend;

import backend.dao.implementaciones.PaisDAOjdbc;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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

//        Pais p = new Pais("Chile");
        PaisDAOjdbc pDAO = new PaisDAOjdbc();
//        pDAO.cargar(p);

        pDAO.editar();

//        List<Pais> listaPaises = pDAO.getPaises();
//
//        for (Pais pais : listaPaises)
//            System.out.println(pais.getNombre());

//        pDAO.eliminar(new Pais("Chile"));

//        System.out.println("------------------");
//
//        pDAO.editar(new Pais("Argentina"), "Brasil");
//        List<Pais> listaPaises2 = pDAO.getPaises();
//
//        for (Pais pais2 : listaPaises2)
//            System.out.println(pais2.getNombre());

//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", "root", "fran");
    }
}
