package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.interfacesDAO.PaisDAO;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class PaisDAOjdbc implements PaisDAO {
    @Override
    public int cargar(Pais nuevoPais) {
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta el pais en la base de datos
            String sql = "INSERT INTO pais (nombre) VALUES (?)";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, nuevoPais.getNombre().toUpperCase(Locale.ROOT).trim());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        } catch (NullPointerException e){
            System.out.println("ERROR: El pais no es valido");
            return 1;
        }
        return 0;
    }

    @Override
    public int eliminar(Pais paisEliminar) {
        Connection connection = MiConnection.getCon();

        try {
            String sql = "DELETE FROM pais WHERE id=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setInt(1, paisEliminar.getId());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }
        return 0;
    }

    /**
     * Edita un pais en la base de datos
     * @param paisEditar Pais a editar, se debe ingresar un pais con id
     * @return 0 si es exitoso, 1 en caso contrario
     */
    @Override
    public int editar(Pais paisEditar) {
        Connection connection = MiConnection.getCon();
        try {
            String sql = "UPDATE pais SET nombre=? WHERE id=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, paisEditar.getNombre().toUpperCase(Locale.ROOT).trim());
            statementPais.setInt(2, paisEditar.getId());
            statementPais.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        } catch (NullPointerException e){
            System.out.println("ERROR: El pais no es valido");
            return 1;
        }
        return 0;
    }

    /**
     * Se evalua si un pais esta en la base de datos
     * @param paisEncontrar Pais a buscar en la BD
     * @return true si lo encontro, false caso contrario
     */
    @Override
    public boolean existe(Pais paisEncontrar) {
        MiConnection.login("root", "fran");
        Connection connection = MiConnection.getCon();

        try {
            String sql = "SELECT * FROM pais WHERE nombre=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setString(1, paisEncontrar.getNombre().toUpperCase(Locale.ROOT));
            ResultSet result = statementPais.executeQuery();

            return result.next();

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
            return true;
        } catch (NullPointerException e){
            System.out.println("ERROR: El pais no es valido");
            return true;
        }
    }

    @Override
    public Pais encontrar(int id){
        Connection connection = MiConnection.getCon();
        Pais pais = new Pais();

        try {
            String sql = "SELECT * FROM pais WHERE id=?";
            PreparedStatement statementPais = connection.prepareStatement(sql);
            statementPais.setInt(1, id);
            ResultSet result = statementPais.executeQuery();

            // Si se encontro el pais, se lo devuelve
            if (result.next()) {
                pais.setNombre(result.getString("nombre"));
                pais.setId(id);
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return pais;
    }

    @Override
    public List<Pais> getPaises() {
        Connection connection = MiConnection.getCon();
        List<Pais> listaPaises = new LinkedList<>();
        try {
            // Se obtienen los paises de la base de datos
            String sql = "SELECT * FROM pais ORDER BY nombre";
            Statement statement = connection.createStatement();
            ResultSet paises = statement.executeQuery(sql);

            // Se agregan en la lista que se va a devolver
            while (paises.next()){
                listaPaises.add(new Pais(paises.getString("nombre"), paises.getInt("id")));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        } catch (NullPointerException e){
            System.out.println("ERROR: No se esta conectado a la base de datos");
        }
        return listaPaises;
    }

    @Override
    public List<String> getPaisesAsStrings(){
        Connection connection = MiConnection.getCon();
        List<String> listaPaises = new LinkedList<>();
        try {
            // Se obtienen los paises de la base de datos
            String sql = "SELECT * FROM pais ORDER BY nombre";
            Statement statement = connection.createStatement();
            ResultSet paises = statement.executeQuery(sql);

            // Se agregan en la lista que se va a devolver
            while (paises.next()){
                listaPaises.add(paises.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }
        return listaPaises;
    }
}
