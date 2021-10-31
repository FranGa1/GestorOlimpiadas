package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.interfaces.PaisDAO;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
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
    public int editar(Pais paisEditar, String nuevoNombre) {
        Connection connection = MiConnection.getCon();
        try {
            String sql = "UPDATE pais SET nombre=? WHERE nombre=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, nuevoNombre);
            statementPais.setString(2, paisEditar.getNombre());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }
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
        Connection connection = MiConnection.getCon();
        List<Pais> listaPaises = new LinkedList<>();
        try {
            // Se obtienen los paises de la base de datos
            String sql = "SELECT * FROM pais";
            Statement statement = connection.createStatement();
            ResultSet paises = statement.executeQuery(sql);

            // Se agregan en la lista que se va a devolver
            while (paises.next()){
                listaPaises.add(new Pais(paises.getString("nombre")));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return null;
        }
        return listaPaises;
    }
}
