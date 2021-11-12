package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.DeportistaDAO;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DeportistaDAOjdbc implements DeportistaDAO {

    /**
     * Se carga un deportista en la base de datos
     * @param deportistaNuevo Deportista a crear
     */
    @Override
    public void cargar(Deportista deportistaNuevo) {

        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta en la tabla deportista al deportistaNuevo
            String sql =  "INSERT INTO deportista (apellidos, nombres, email, telefono, id_pais) VALUES(?,?,?,?,(SELECT id FROM pais WHERE nombre=?))";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, deportistaNuevo.getApellidos().toUpperCase(Locale.ROOT));
            statement.setString(2, deportistaNuevo.getNombres().toUpperCase(Locale.ROOT));
            statement.setString(3, deportistaNuevo.getEmail().toUpperCase(Locale.ROOT));
            statement.setString(4, deportistaNuevo.getTelefono().trim());
            statement.setString(5, deportistaNuevo.getPais().getNombre().toUpperCase(Locale.ROOT));
            statement.executeUpdate();

            // Se obtiene el id del deportista recien agregado
            ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
            generatedKeysResultSet.next();
            int idDeportista = generatedKeysResultSet.getInt(1);

            // Se cargan las disciplinas del deportista
            FactoryDAO.getDeporEnDisciplinaDAO().cargarDisciplinasDeportista(deportistaNuevo.getDisciplinas(), idDeportista);

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    /**
     * Se elimina un deportista de la base de datos
     * @param deportistaEliminar Deportista a eliminar
     */
    @Override
    public void eliminar(Deportista deportistaEliminar) {

        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();

        try {
            // Se borra al deportista de la tabla deportista_en_disciplina
            String sql = "DELETE FROM deportista_en_disciplina WHERE id_deportista=?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deportistaEliminar.getId());
            statement.executeUpdate();

            // Se eliminan las disciplinas del deportista
            FactoryDAO.getDeporEnDisciplinaDAO().eliminarDisciplinasDeportista(deportistaEliminar.getId());

        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }
    }

    /**
     * Se edita un deportista en la base de datos
     * @param deportistaEditar Deportista a editar
     */
    @Override
    public void editar(Deportista deportistaEditar) {
        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            String sql =  "UPDATE deportista " +
                    "SET nombres=?, apellidos=?, email=?, telfono=?, id_pais=?" +
                    " WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, deportistaEditar.getNombres().toUpperCase(Locale.ROOT));
            statement.setString(2, deportistaEditar.getApellidos().toUpperCase(Locale.ROOT));
            statement.setString(3, deportistaEditar.getEmail().toUpperCase(Locale.ROOT));
            statement.setString(4, deportistaEditar.getTelefono());
            statement.setInt(5, deportistaEditar.getPais().getId());
            statement.setInt(6, deportistaEditar.getId());
            statement.executeUpdate();

            // Se editan las disciplinas asociadas al deportista
            FactoryDAO.getDeporEnDisciplinaDAO().editarDisciplinasDeportista(deportistaEditar.getDisciplinas(), deportistaEditar.getId());

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    /**
     * Se obtiene una lista de deportistas
     * @return lista que contiene todos los deportistas
     */
    @Override
    public List<Deportista> getDeportistas() {
        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();
        List<Deportista> listasDeportistas = new LinkedList<>();

        try {
            String sql = "SELECT * FROM deportista";
            Statement statement = connection.createStatement();
            ResultSet deportistasBD = statement.executeQuery(sql);

            while (deportistasBD.next()){
                // Se obtienen todos los atributos del jugador del ResultSet
                String apellido = deportistasBD.getString("apellidos");
                String nombre = deportistasBD.getString("nombres");
                String telefono = deportistasBD.getString("telefono");
                String email = deportistasBD.getString("email");
                int idDeportista = deportistasBD.getInt("id");
                int idPais = deportistasBD.getInt("id_pais");
                Pais pais = FactoryDAO.getPaisDAO().encontrar(idPais);
                List<Disciplina> disciplinas =  FactoryDAO.getDisciplinaDAO().getDisciplinasDeportista(idDeportista);

                // Se lo agrega a la lista a devolver
                listasDeportistas.add(new Deportista(nombre, apellido, email, telefono, pais, disciplinas, idDeportista));
            }
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listasDeportistas;
    }
}
