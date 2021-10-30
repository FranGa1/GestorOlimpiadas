package backend.dao.implementaciones;

import backend.dao.interfaces.PaisDAO;
import objetos.Pais;

import java.util.List;


public class PaisDAOjdbc implements PaisDAO {
    @Override
    public void cargar(Pais nuevoPais) {

    }

    @Override
    public void eliminar(Pais paisEliminar) {

    }

    @Override
    public void editar(Pais paisEditar, String nombre) {

    }

    @Override
    public Pais encontrar(Pais pais) {
        return null;
    }

//    @Override
//    public Pais encontrar(Pais pais){
//        Connection connection = MiConnection.getCon();
//
//        try{
//            Statement statement = connection.createStatement();
//        } catch (SQLException e) {
//            System.out.println("Error de SQL: " + e.getMessage());
//        }
//    }

    @Override
    public List<Pais> getPaises() {
        return null;
    }
}
