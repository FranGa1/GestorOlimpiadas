package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.PaisDAO;
import backend.exceptions.PaisExistsException;
import backend.exceptions.PaisUsedException;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class PaisDAOjdbc implements PaisDAO {

    /**
     * Se carga un pais en la base de datos.
     * @param nuevoPais pais a cargar.
     * @throws Exception en el caso de que el pais exista, problemas con la BD u otros.
     */
    @Override
    public void cargar(Pais nuevoPais) throws Exception {
        Connection connection = MiConnection.getCon();

        if(existe(nuevoPais))
            throw new PaisExistsException();

        // Se inserta el pais en la base de datos
        String sql = "INSERT INTO pais (nombre) VALUES (?)";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, nuevoPais.getNombre().toUpperCase(Locale.ROOT).trim());
        statementPais.executeUpdate();
    }

    /**
     * Se elimina a un pais de la base de datos.
     * @param paisEliminar para a eliminar.
     * @throws Exception en el caso de que el pais este asignado a algun deportista, problemas con la BD u otros.
     */
    @Override
    public void eliminar(Pais paisEliminar) throws Exception {

        if (FactoryDAO.getDeportistaDAO().paisIsUsed(paisEliminar))
            throw new PaisUsedException();

        Connection connection = MiConnection.getCon();

        String sql = "DELETE FROM pais WHERE id=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setInt(1, paisEliminar.getId());
        int rowsUpdated = statementPais.executeUpdate();
        if (rowsUpdated == 0) throw new PaisUsedException();
    }

    /**
     * * Edita un pais en la base de datos.
     *  @param paisEditar Pais a editar, se debe ingresar un pais con id.
     *  @throws Exception en el caso de un problema con la base de datos u otros.
     */
    @Override
    public void editar(Pais paisEditar) throws Exception{

        if (existe(paisEditar))
            throw new PaisExistsException();

        Connection connection = MiConnection.getCon();

        String sql = "UPDATE pais SET nombre=? WHERE id=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, paisEditar.getNombre().toUpperCase(Locale.ROOT).trim());
        statementPais.setInt(2, paisEditar.getId());
        statementPais.executeUpdate();
    }

    /**
     * Se evalua si un pais esta en la base de datos.
     * @param paisEncontrar Pais a buscar en la BD.
     * @return true o false segun si lo encontro.
     * @throws Exception en el caso de un problema con la base de datos u otros.
     */
    @Override
    public boolean existe(Pais paisEncontrar) throws Exception {
        Connection connection = MiConnection.getCon();

        String sql = "SELECT * FROM pais WHERE nombre=?";
        PreparedStatement statementPais = connection.prepareStatement(sql);
        statementPais.setString(1, paisEncontrar.getNombre().toUpperCase(Locale.ROOT));
        ResultSet result = statementPais.executeQuery();

        return result.next();
    }

    /**
     * Se obtiene un pais de la base de datos en base a su id.
     * @param id del pais a encontrar.
     * @return una objeto Pais con nombre e id.
     * @throws Exception en el caso de un problema con la base de datos u otros.
     */
    @Override
    public Pais encontrar(int id) throws Exception{
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

    /**
     * Se obtiene una lista de paises de la base de datos.
     * @return lista con los paises con nombre e id.
     * @throws Exception en el caso de un problema con la base de datos u otros.
     */
    @Override
    public List<Pais> getPaises() throws Exception {
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

    /**
     * Se obtiene una lista de paises como Strings con solo su nombre.
     * @return listaPaises con los nombres de los paises.
     * @throws Exception en el caso de un problema con la base de datos u otros.
     */
    @Override
    public List<String> getPaisesAsStrings() throws Exception{
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