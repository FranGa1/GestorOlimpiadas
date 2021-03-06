package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.DeportistaDAO;
import backend.exceptions.NotConnectedException;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DeportistaDAOjdbc implements DeportistaDAO {

    /**
     * Se carga un deportista en la base de datos.
     * @param deportistaNuevo deportista a agregar.
     * @throws Exception en el caso de problemas con la BD u otros.
     */
    @Override
    public void cargar(Deportista deportistaNuevo) throws Exception {

        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        // Se inserta en la tabla deportista al deportistaNuevo
        String sql =  "INSERT INTO deportista (apellidos, nombres, email, telefono, id_pais) VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, deportistaNuevo.getApellidos());
        statement.setString(2, deportistaNuevo.getNombres());
        statement.setString(3, deportistaNuevo.getEmail());
        statement.setString(4, deportistaNuevo.getTelefono());
        statement.setInt(5, FactoryDAO.getPaisDAO().getIdPais(deportistaNuevo.getPais()));
        statement.executeUpdate();

        // Se obtiene el id del deportista recien agregado
        ResultSet generatedKeysResultSet = statement.getGeneratedKeys();
        generatedKeysResultSet.next();
        int idDeportista = generatedKeysResultSet.getInt(1);

        // Se cargan las disciplinas del deportista
        FactoryDAO.getDeporEnDisciplinaDAO().cargarDisciplinasDeportista(deportistaNuevo.getDisciplinas(), idDeportista);
    }

    /**
     * Se elimina a un deportista de la base de datos.
     * @param deportistaEliminar deportista a eliminar.
     * @throws Exception en el caso de problemas con la BD u otros.
     */
    @Override
    public void eliminar(Deportista deportistaEliminar) throws Exception{

        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();

        // Se eliminan las disciplinas del deportista
        FactoryDAO.getDeporEnDisciplinaDAO().eliminarDisciplinasDeportista(deportistaEliminar.getId());

        // Se borra al deportista de la tabla deportista_en_disciplina
        String sql = "DELETE FROM deportista WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, deportistaEliminar.getId());
        statement.executeUpdate();
    }

    /**
     * Se edita a un deportista en la base de datos.
     * @param deportistaEditar deportista a editar.
     * @throws Exception en el caso de problemas con la BD u otros.
     */
    @Override
    public void editar(Deportista deportistaEditar) throws Exception{
        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        String sql =  "UPDATE deportista " +
                "SET nombres=?, apellidos=?, email=?, telefono=?, id_pais=?" +
                " WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, deportistaEditar.getNombres());
        statement.setString(2, deportistaEditar.getApellidos());
        statement.setString(3, deportistaEditar.getEmail());
        statement.setString(4, deportistaEditar.getTelefono());
        statement.setInt(5, FactoryDAO.getPaisDAO().getIdPais(deportistaEditar.getPais()));
        statement.setInt(6, deportistaEditar.getId());
        statement.executeUpdate();

        // Se editan las disciplinas asociadas al deportista
        FactoryDAO.getDeporEnDisciplinaDAO().editarDisciplinasDeportista(deportistaEditar.getDisciplinas(), deportistaEditar.getId());
    }

    /**
     * Se obtiene una lista de deportista.
     * @return lista que contiene los deportistas.
     * @throws Exception en el caso de problemas con la BD u otros.
     */
    @Override
    public List<Deportista> getDeportistas() throws Exception {

        if (MiConnection.nullConnection())
            throw new NotConnectedException();

        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();

        List<Deportista> listasDeportistas = new LinkedList<>();

        String sql = "SELECT * FROM deportista ORDER BY nombres";
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
            List<Disciplina> disciplinas =  FactoryDAO.getDeporEnDisciplinaDAO().getDisciplinasDeportista(idDeportista);

            // Se lo agrega a la lista a devolver
            listasDeportistas.add(new Deportista(nombre, apellido, email, telefono, pais, disciplinas, idDeportista));
        }
        return listasDeportistas;
    }

    /**
     * Retorna verdadero o falso segun si un pais esta asignado a algun deportista
     * @param pais a ser comprobado
     * @return boolean
     * @throws Exception en el caso de problemas con la BD u otros.
     */
    @Override
    public boolean paisIsUsed(Pais pais) throws Exception{
        Connection connection = MiConnection.getCon();
        String sql = "SELECT * FROM deportista WHERE id_pais=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, pais.getId());
        ResultSet deportistasQueUsanPais = statement.executeQuery();
        return deportistasQueUsanPais.next();
    }
}