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
    public void cargar(Pais nuevoPais) throws SQLException{
        Connection connection = MiConnection.getCon();

        // Se inserta el pais en la base de datos
        String sql = "INSERT INTO pais (nombre) VALUES (?)";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, nuevoPais.getNombre().toUpperCase(Locale.ROOT).trim());
        statementPais.executeUpdate();

    }

    @Override
    public void eliminar(Pais paisEliminar) throws SQLException{
        Connection connection = MiConnection.getCon();

        String sql = "DELETE FROM pais WHERE id=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setInt(1, paisEliminar.getId());
        statementPais.executeUpdate();
    }

    /**
     * Edita un pais en la base de datos
     * @param paisEditar Pais a editar, se debe ingresar un pais con id
     * @return 0 si es exitoso, 1 en caso contrario
     */
    @Override
    public void editar(Pais paisEditar) throws SQLException{
        Connection connection = MiConnection.getCon();

        String sql = "UPDATE pais SET nombre=? WHERE id=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, paisEditar.getNombre().toUpperCase(Locale.ROOT).trim());
        statementPais.setInt(2, paisEditar.getId());
        statementPais.executeUpdate();

    }

    /**
     * Se evalua si un pais esta en la base de datos
     * @param paisEncontrar Pais a buscar en la BD
     * @return true si lo encontro, false caso contrario
     */
    @Override
    public boolean existe(Pais paisEncontrar) throws SQLException {
        Connection connection = MiConnection.getCon();

        String sql = "SELECT * FROM pais WHERE nombre=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, paisEncontrar.getNombre().toUpperCase(Locale.ROOT));
        ResultSet result = statementPais.executeQuery();

        return result.next();

    }

    @Override
    public Pais encontrar(int id) throws SQLException{
        Connection connection = MiConnection.getCon();
        Pais pais = new Pais();

        String sql = "SELECT * FROM pais WHERE id=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setInt(1, id);
        ResultSet result = statementPais.executeQuery();

        // Si se encontro el pais, se lo devuelve
        if (result.next()) {
            pais.setNombre(result.getString("nombre"));
            pais.setId(id);
        }

        return pais;
    }

    @Override
    public List<Pais> getPaises() throws SQLException {
        Connection connection = MiConnection.getCon();
    List<Pais> listaPaises = new LinkedList<>();
        // Se obtienen los paises de la base de datos
        String sql = "SELECT * FROM pais ORDER BY nombre";
        Statement statement = connection.createStatement();
        ResultSet paises = statement.executeQuery(sql);

        // Se agregan en la lista que se va a devolver
        while (paises.next()){
            listaPaises.add(new Pais(paises.getString("nombre"), paises.getInt("id")));
        }

        return listaPaises;
    }

    @Override
    public List<String> getPaisesAsStrings() throws SQLException{
        Connection connection = MiConnection.getCon();
        List<String> listaPaises = new LinkedList<>();
        // Se obtienen los paises de la base de datos
        String sql = "SELECT * FROM pais ORDER BY nombre";
        Statement statement = connection.createStatement();
        ResultSet paises = statement.executeQuery(sql);

        // Se agregan en la lista que se va a devolver
        while (paises.next()){
            listaPaises.add(paises.getString("nombre"));
        }
        return listaPaises;
    }
}