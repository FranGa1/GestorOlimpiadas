package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfaces.DeportistaDAO;
import backend.dao.interfaces.DisciplinaDAO;
import backend.dao.interfaces.PaisDAO;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DeportistaDAOjdbc implements DeportistaDAO {

    /**
     * Se carga un deportista en la base de datos
     * @param deportistaNuevo
     */
    @Override
    public int cargar(Deportista deportistaNuevo) {

        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta en la tabla deportista al deportistaNuevo
            String sql =  "INSERT INTO deportista (apellidos, nombres, email, telefono, id_pais) VALUES(?,?,?,?,(SELECT id FROM pais WHERE nombre=?))";
            PreparedStatement statementDeportista = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statementDeportista.setString(1, deportistaNuevo.getApellidos());
            statementDeportista.setString(2, deportistaNuevo.getNombres());
            statementDeportista.setString(3, deportistaNuevo.getEmail());
            statementDeportista.setString(4, deportistaNuevo.getTelefono());
            statementDeportista.setString(5, deportistaNuevo.getPais().getNombre());
            statementDeportista.executeUpdate();

            // Se obtiene el id del deportista recien agregado
            ResultSet generatedKeysResultSet = statementDeportista.getGeneratedKeys();
            generatedKeysResultSet.next();
            int idDeportista = generatedKeysResultSet.getInt(1);

            // Se le asignan las disciplinas al deportista
            sql =  "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) VALUES(?,(SELECT id FROM disciplina WHERE nombre=?))";
            PreparedStatement statementDisciplina = connection.prepareStatement(sql);

            List<Disciplina> disciplinasDeportista = deportistaNuevo.getDisciplinas();
            for(Disciplina disciplina : disciplinasDeportista){
                statementDisciplina.setInt(1, idDeportista);
                statementDisciplina.setString(2, disciplina.getNombre());
                statementDisciplina.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    /**
     * Se elimina un deportista de la base de datos
     * @param deportistaEliminar
     */
    @Override
    public int eliminar(Deportista deportistaEliminar) {

        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();

        try {
            // Se borra al deportista de la tabla deportista_en_disciplina
            String sql = "DELETE FROM deportista_en_disciplina WHERE id_deportista=?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, deportistaEliminar.getId());
            statement.executeUpdate();

            // Se borra al deportista de la tabla deportista
            sql = "DELETE FROM deportista WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, deportistaEliminar.getId());
            statement.executeUpdate();

        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }
        return 0;
    }

    @Override
    public int editar(Deportista deportistaEditar) {
        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            String sql =  "UPDATE deportista " +
                    "SET nombres=?, apellidos=?, email=?, telfono=?, id_pais=(SELECT id FROM pais WHERE nombre=?)" +
                    " WHERE id=?";
            PreparedStatement statementDeportista = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statementDeportista.setString(1, deportistaEditar.getNombres());
            statementDeportista.setString(2, deportistaEditar.getApellidos());
            statementDeportista.setString(3, deportistaEditar.getEmail());
            statementDeportista.setString(4, deportistaEditar.getTelefono());
            statementDeportista.setString(5, deportistaEditar.getPais().getNombre());
            statementDeportista.setInt(6, deportistaEditar.getId());
            statementDeportista.executeUpdate();

            // Se le asignan las disciplinas al deportista
            sql =  "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) " +
                    "VALUES(?,(SELECT id FROM disciplina WHERE nombre=?))";
            PreparedStatement statementDisciplina = connection.prepareStatement(sql);

            for(Disciplina disciplina : deportistaEditar.getDisciplinas()){
                statementDisciplina.setInt(1, deportistaEditar.getId());
                statementDisciplina.setString(2, disciplina.getNombre());
                statementDisciplina.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return 1;
        }
        return 0;
    }

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
                int idPais = deportistasBD.getInt("id_pais");
                PaisDAO paisDAO = FactoryDAO.getPaisDAO();
                Pais pais = paisDAO.encontrar(idPais);
                DisciplinaDAO disciplinaDAO = FactoryDAO.getDisciplinaDAO();
                int idDeportista = deportistasBD.getInt("id");
                List<Disciplina> disciplinas = disciplinaDAO.getDisciplinasDeportista(idDeportista);

                // Se lo agrega a la lista a devolver
                listasDeportistas.add(new Deportista(nombre, apellido, email, telefono, pais, disciplinas, idDeportista));
            }
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listasDeportistas;
    }

    @Override
    public int getIdDeportista(Deportista deportista){
        Connection connection = MiConnection.getCon();
        int id = 0;
        try {
            String sql = "SELECT id FROM deportista WHERE (nombres=? and apellidos=? and email=? and telefono=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deportista.getNombres());
            statement.setString(2, deportista.getApellidos());
            statement.setString(3, deportista.getEmail());
            statement.setString(4, deportista.getTelefono());
            ResultSet deportistaBD = statement.executeQuery();

            if (deportistaBD.isBeforeFirst()){
                id = deportistaBD.getInt("id");
            }

        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }
        return  id;
    }
}
