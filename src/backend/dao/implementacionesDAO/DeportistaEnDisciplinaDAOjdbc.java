package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.interfacesDAO.DeportistaEnDisciplinaDAO;
import objetos.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeportistaEnDisciplinaDAOjdbc implements DeportistaEnDisciplinaDAO {

    /**
     * Asocia e
     * @param disciplinasDeportista
     * @param idDeportista
     */
    @Override
    public void cargarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) {
        Connection connection = MiConnection.getCon();

        try {
            // Se le asignan las disciplinas al deportista
            String sql = "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) VALUES(?,(SELECT id FROM disciplina WHERE nombre=?))";
            PreparedStatement statement = connection.prepareStatement(sql);
            for (Disciplina disciplina : disciplinasDeportista) {
                statement.setInt(1, idDeportista);
                statement.setString(2, disciplina.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }
    }

    @Override
    public void eliminarDisciplinasDeportista(int idDeportista){
        Connection connection = MiConnection.getCon();
        try {
            // Se borra al deportista de la tabla deportista
            String sql = "DELETE FROM deportista WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDeportista);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }


    }
}
