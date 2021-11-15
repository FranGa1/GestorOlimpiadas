package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.interfacesDAO.DisciplinaDAO;
import objetos.Disciplina;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DisciplinaDAOjdbc implements DisciplinaDAO {

    /**
     * Se obtiene una lista de las Disciplinas disponibles en la BD
     * @return listaDisciplinas con la lista de objetos Disciplinas
     * @throws Exception en el caso de un problema con la base de datos u otros.
     */
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
     * Se obtiene una lista nombres de las disciplinas en la base de datos.
     * @return listaDisciplinasAsStrings una lista de Strings.
     * @throws Exception en el caso de un problama con la base de datos u otros.
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

    /**
     * Se obtienen determinadas disciplinas
     * @param disciplinasSeleccionadas lista de ids de disciplinas deseadas.
     * @return listaDisciplinas con objetos de tipo Disciplina.
     * @throws Exception en el caso de un problama con la base de datos u otros.
     */
    @Override
    public List<Disciplina> getDisciplinasSeleccionadas(List<Integer> disciplinasSeleccionadas) throws Exception{

        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinas= new LinkedList<>();
        String sql = "SELECT * FROM disciplina WHERE id IN (?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        // Setea en la sentencia sql todos los posibles ids de las disciplinas del deportista
        statement.setString(1, Arrays.toString(disciplinasSeleccionadas.toArray()).replace("[", "").replace("]", ""));
        ResultSet disciplinasBD = statement.executeQuery();

        // Se agrega a la lista a devolver las disciplinas obtenidas
        while (disciplinasBD.next())
            listaDisciplinas.add(new Disciplina(disciplinasBD.getString("nombre")));

        return listaDisciplinas;
    }

    /**
     * Se obtiene el id de una disciplina en base a su nombre
     * @param disciplina de la cual se quiere obtener el id.
     * @return id de la disciplina deseada
     * @throws Exception en el caso de un problama con la base de datos u otros.
     */
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
