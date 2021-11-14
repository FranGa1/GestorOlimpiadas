package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.interfacesDAO.DisciplinaDAO;
import objetos.Disciplina;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DisciplinaDAOjdbc implements DisciplinaDAO {
    @Override
    public List<Disciplina> getDisciplinas() throws Exception{
        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinas = new LinkedList<>();

        String sql = "SELECT * FROM disciplina";
        Statement statement = connection.createStatement();
        ResultSet disciplinasBD = statement.executeQuery(sql);

        while (disciplinasBD.next()){
            listaDisciplinas.add(new Disciplina(disciplinasBD.getString("nombre")));
        }

        return listaDisciplinas;
    }

    /**
     * Se obtiene una lista de nombres de desiciplinas
     * @return Lista de Strings
     */
    @Override
    public List<String> getDisciplinasAsStrings() throws Exception{
        Connection connection = MiConnection.getCon();
        List<String> listaDisciplinasAsStrings = new LinkedList<>();

        String sql = "SELECT * FROM disciplina";
        Statement statement = connection.createStatement();
        ResultSet disciplinasBD = statement.executeQuery(sql);

        while (disciplinasBD.next()){
            listaDisciplinasAsStrings.add(disciplinasBD.getString("nombre"));
        }

        return listaDisciplinasAsStrings;
    }

    @Override
    public List<Disciplina> getDisciplinasSeleccionadas(List<Integer> disciplinasSeleccionadas) throws Exception{
        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinas= new LinkedList<>();
        String sql = "SELECT * FROM disciplina WHERE id IN (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        // Setea en la sentencia sql todos los posibles ids de las disciplinas del deportista
        statement.setString(1, Arrays.toString(disciplinasSeleccionadas.toArray()).replace("[", "").replace("]", ""));
        ResultSet disciplinasBD = statement.executeQuery();

        while (disciplinasBD.next())
            listaDisciplinas.add(new Disciplina(disciplinasBD.getString("nombre")));

        return listaDisciplinas;
    }

    @Override
    public int getIDDisciplina(Disciplina disciplina) throws Exception{
        Connection connection = MiConnection.getCon();
        String sql = "SELECT id FROM disciplina WHERE nombre=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        // Setea en la sentencia sql todos los posibles ids de las disciplinas del deportista
        statement.setString(1, disciplina.getNombre());
        ResultSet disciplinasBD = statement.executeQuery();
        disciplinasBD.next();
        return disciplinasBD.getInt("id");
    }
}
