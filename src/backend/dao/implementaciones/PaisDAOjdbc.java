package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.interfaces.PaisDAO;
import objetos.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class PaisDAOjdbc implements PaisDAO {
    @Override
    public int cargar(Pais nuevoPais) {
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta el pais en la base de datos
            String sql = "INSERT INTO pais (nombre) VALUES (?)";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, nuevoPais.getNombre());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int eliminar(Pais paisEliminar) {
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta el pais en la base de datos
            String sql = "DELETE FROM pais WHERE nombre=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, paisEliminar.getNombre());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int editar(Pais paisEditar, String nombre) {

        return 0;
    }

    @Override
    public boolean encontrar(Pais pais) {
        Connection connection = MiConnection.getCon();


//        try {
//            // Se inserta el pais en la base de datos
//            String sql = "SELECT FROM pais WHERE nombre=?";
//            PreparedStatement statementPais = connection.prepareStatement(sql);
//            statementPais.setString(1, paisEliminar.getNombre());
//            statementPais.executeUpdate();
//
//            return true
//        } catch (SQLException e) {
//            System.out.println("Error de SQL: "+e.getMessage());
//            return false;
//        }
        return true;
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
