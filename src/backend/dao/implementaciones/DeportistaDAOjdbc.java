package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.interfaces.DeportistaDAO;
import objetos.Deportista;
import objetos.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeportistaDAOjdbc implements DeportistaDAO {

    /**
     * Se carga un deportista en la base de datos
     * @param deportistaNuevo
     */
    @Override
    public void cargar(Deportista deportistaNuevo) {

        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta en la tabla deportista al deportistaNuevo
            String sql =  "INSERT INTO deportista (apellidos, nombres, email, telefono, id_pais) VALUES(?,?,?,?,?)";
            PreparedStatement statementDeportista = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statementDeportista.setString(1, deportistaNuevo.getApellido());
            statementDeportista.setString(2, deportistaNuevo.getNombre());
            statementDeportista.setString(3, deportistaNuevo.getEmail());
            statementDeportista.setString(4, deportistaNuevo.getTelefono());
            statementDeportista.setInt(5, deportistaNuevo.getPais().getID());
            statementDeportista.executeUpdate();

            // Se obtiene el id del deportista recien agregado
            ResultSet generatedKeysResultSet = statementDeportista.getGeneratedKeys();
            generatedKeysResultSet.next();
            int idDeportista = generatedKeysResultSet.getInt(1);

            // Se le asignan las disciplinas al deportista
            sql =  "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) VALUES(?,?)";
            PreparedStatement statementDisciplina = connection.prepareStatement(sql);

            ArrayList<Disciplina> disciplinasDeportista = deportistaNuevo.getDisciplinas();
            for(Disciplina disciplina : disciplinasDeportista){
                statementDisciplina.setInt(1, idDeportista);
                statementDisciplina.setInt(2, disciplina.getID());
                statementDisciplina.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }

    /**
     * Se elimina un deportista de la base de datos
     * @param deportistaEliminar
     */
    @Override
    public void eliminar(Deportista deportistaEliminar) {
//        String sql = "DELETE FROM deportista WHERE "
    }

    @Override
    public void editar(Deportista deportistaEditar, Deportista deportistaEditado) {

    }

    @Override
    public List<Deportista> getDeportistas() {
        return null;
    }
}
