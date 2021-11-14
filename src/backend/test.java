package backend;

import backend.dao.FactoryDAO;
import backend.exceptions.PaisUsedException;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.SQLException;
public class test {
    public static int prueba(int numero){
        return numero;
    }

    public static void main(String[] args) throws SQLException {
//        int prueba = 5;
//        try {
//            if (prueba > 1)
//                throw new ManejoExcepciones(5);
//        } catch (ManejoExcepciones e){
//            System.out.println(e.getMessage());
//        }

        MiConnection.login("root", "fran");

        try {
            FactoryDAO.getPaisDAO().eliminar(new Pais("ARG"));
        } catch (SQLException e) {
            System.out.println("sqlex");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

//        try {
//            FactoryDAO.getPaisDAO().cargar(new Pais(null));
//        } catch (Exception e) {
//            System.out.println("SQL ERROR: "+e.getMessage());
//        }
    }
}
