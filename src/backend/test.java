package backend;

import backend.dao.FactoryDAO;
import backend.dao.implementaciones.DisciplinaDAOjdbc;
import backend.dao.implementaciones.PaisDAOjdbc;
import backend.dao.interfaces.DeportistaDAO;
import backend.dao.interfaces.DisciplinaDAO;
import backend.dao.interfaces.PaisDAO;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class test {
    public static void main(String[] args) throws SQLException {
        Deportista d = new Deportista();
        d.setApellidos("garay");
        d.setNombres("fran");
        d.setEmail("asdf");
        d.setTelefono("9784235");


//        Disciplina di = new Disciplina("Atletismo");
//        List<Disciplina> dis = new LinkedList<>();
//        dis.add(di);
//
//        Pais p = new Pais("Brasil");
//        d.setPais(p);
//        d.setDisciplinas(dis);
        DeportistaDAO dao = FactoryDAO.getDeportistaDAO();
////        dao.cargar(d);
        dao.eliminar(d);
//        List<Deportista> ds = dao.getDeportistas();
//        for (Deportista deportista : ds)
//            System.out.println(deportista.getNombres());
//
//
//
//        ds = dao.getDeportistas();
//        for (Deportista deportista : ds)
//            System.out.println(deportista.getNombres());

//        PaisDAO pDAO = FactoryDAO.getPaisDAO();
//        DeportistaDAO d = FactoryDAO.getDeportistaDAO();

        


//        pDAO.cargar(p);
//
//        DisciplinaDAO di = FactoryDAO.getDisciplinaDAO();
//        List<Disciplina> listD = di.getDisciplinas();
//
//
//        System.out.println(pDAO.existe(new Pais("Brasil")));
//        System.out.println(pDAO.encontrar(new Pais("Argentina")));

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
