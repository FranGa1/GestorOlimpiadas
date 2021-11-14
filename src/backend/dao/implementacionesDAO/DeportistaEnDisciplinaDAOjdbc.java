package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.DeportistaEnDisciplinaDAO;
import objetos.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class DeportistaEnDisciplinaDAOjdbc implements DeportistaEnDisciplinaDAO {

    /**
     * Asocia e
     * @param disciplinasDeportista
     * @param idDeportista
     */
    @Override
    public void cargarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws Exception {
        Connection connection = MiConnection.getCon();
        String sql = "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) VALUES(?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (Disciplina disciplina : disciplinasDeportista) {
            statement.setInt(1, idDeportista);
            statement.setInt(2, FactoryDAO.getDisciplinaDAO().getIDDisciplina(new Disciplina(disciplina.getNombre())));
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarDisciplinasDeportista(int idDeportista) throws Exception{
        Connection connection = MiConnection.getCon();
        // Se borra al deportista de la tabla deportista
        String sql = "DELETE FROM deportista_en_disciplina WHERE id_deportista=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDeportista);
        statement.executeUpdate();
    }

    @Override
    public void editarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws Exception{
        eliminarDisciplinasDeportista(idDeportista);
        cargarDisciplinasDeportista(disciplinasDeportista, idDeportista);
    }

    /**
     * Retorna una lista con los id's de las disciplinas del deportista
     * @param idDeportista
     * @return listaDisciplinas
     */
    @Override
    public List<Integer> getIDsDisciplinasDeportista(int idDeportista) throws Exception{
        Connection connection = MiConnection.getCon();
        List<Integer> listaDisciplinas = new LinkedList<>();

            String sql = "SELECT id_disciplina FROM deportista_en_disciplina WHERE id_deportista=?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.clearParameters();
            statement.setInt(1, idDeportista);
            ResultSet disciplinasBD = statement.executeQuery();

            while (disciplinasBD.next()){
                listaDisciplinas.add(disciplinasBD.getInt("id_disciplina"));
            }

        return listaDisciplinas;
    }

    /**
     * Se obtiene una lista de las disciplinas a las que pertenece un deportista en base a su id
     * @param idDeportista id del deportista.
     * @return lista con objetos de tipo disciplinas.
     * @throws Exception en el caso de un problama con la base de datos u otros.
     */
    @Override
    public List<Disciplina> getDisciplinasDeportista(int idDeportista) throws Exception {
        Connection connection = MiConnection.getCon();
        List<Integer> listaIdsDisciplinas = FactoryDAO.getDeporEnDisciplinaDAO().getIDsDisciplinasDeportista(idDeportista);

        return FactoryDAO.getDisciplinaDAO().getDisciplinasSeleccionadas(listaIdsDisciplinas);
    }
}
