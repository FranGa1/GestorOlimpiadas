package backend;

import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import objetos.Deportista;

import java.sql.SQLException;

public class test {
    public static int prueba(int numero){
        return numero;
    }

    public static void main(String[] args) throws SQLException {
//        Deportista d = new Deportista();
//        int a;
//        try {
//            a = "asdf" + d.getNombres();
//        } catch (NullPointerException e){
//            System.out.println("ERROR");
//        }
//        System.out.println(prueba(d));
//        Deportista d = new Deportista();
//        d.setApellidos("garay");
//        d.setNombres("fran");
//        d.setEmail("asdf");
//        d.setTelefono("9784235");


//        Disciplina di = new Disciplina("Atletismo");
//        List<Disciplina> dis = new LinkedList<>();
//        dis.add(di);
//
//        Pais p = new Pais("Brasil");
//        d.setPais(p);
//        d.setDisciplinas(dis);
//        DeportistaDAO dao = FactoryDAO.getDeportistaDAO();
////        dao.cargar(d);
//        dao.eliminar(d);
//        List<Deportista> ds = dao.getDeportistas();
//        for (Deportista deportista : ds)
//            System.out.println(deportista.getNombres());
//
//
//
//        ds = dao.getDeportistas();
//        for (Deportista deportista : ds)
//            System.out.println(deportista.getNombres());

        PaisDAO pDAO = FactoryDAO.getPaisDAO();
        MiConnection.login("root", "fran");
        System.out.println(pDAO.getPaisesAsStrings());
//        DeportistaDAO d = FactoryDAO.getDepor
//        tistaDAO();

//        System.out.println(pDAO.existe(new Pais("Brasil")));


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
