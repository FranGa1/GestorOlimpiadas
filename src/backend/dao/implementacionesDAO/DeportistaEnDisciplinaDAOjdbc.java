package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.interfacesDAO.DeportistaEnDisciplinaDAO;
import objetos.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
            String sql = "DELETE FROM deportista_en_disciplina WHERE id_deportista=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDeportista);
            statement.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }
    }

    @Override
    public void editarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista){
        eliminarDisciplinasDeportista(idDeportista);
        cargarDisciplinasDeportista(disciplinasDeportista, idDeportista);
    }

    /**
     * Retorna una lista con los id's de las disciplinas del deportista
     * @param idDeportista
     * @return listaDisciplinas
     */
    @Override
    public List<Integer> getDisciplinasDeportista(int idDeportista){
        Connection connection = MiConnection.getCon();
        List<Integer> listaDisciplinas = new LinkedList<>();

        try {
            String sql = "SELECT id_disciplina FROM deportista_en_disciplina WHERE id_deportista=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.clearParameters();
            statement.setInt(1, idDeportista);
            ResultSet disciplinasBD = statement.executeQuery();

            while (disciplinasBD.next()){
                listaDisciplinas.add(disciplinasBD.getInt("id_disciplina"));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e);
        }
        return listaDisciplinas;
    }

}
