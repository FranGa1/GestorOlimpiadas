package backend.dao.implementacionesDAO;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfacesDAO.DisciplinaDAO;
import objetos.Disciplina;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DisciplinaDAOjdbc implements DisciplinaDAO {
    @Override
    public List<Disciplina> getDisciplinas() {
        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinas = new LinkedList<>();

        try {
            String sql = "SELECT * FROM disciplina";
            Statement statement = connection.createStatement();
            ResultSet disciplinasBD = statement.executeQuery(sql);

            while (disciplinasBD.next()){
                listaDisciplinas.add(new Disciplina(disciplinasBD.getString("nombre")));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listaDisciplinas;
    }

    /**
     * Se obtiene una lista de nombres de desiciplinas
     * @return Lista de Strings
     */
    @Override
    public List<String> getDisciplinasAsStrings(){
        Connection connection = MiConnection.getCon();
        List<String> listaDisciplinasAsStrings = new LinkedList<>();

        try {
            String sql = "SELECT * FROM disciplina";
            Statement statement = connection.createStatement();
            ResultSet disciplinasBD = statement.executeQuery(sql);

            while (disciplinasBD.next()){
                listaDisciplinasAsStrings.add(disciplinasBD.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listaDisciplinasAsStrings;
    }

    /**
     * Se obtiene una lista de
     * @param idDeportista
     * @return
     */
    @Override
    public List<Disciplina> getDisciplinasDeportista(int idDeportista){
        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinasAsStrings = new LinkedList<>();
        List<Integer> listaIdsDisciplinas = FactoryDAO.getDeporEnDisciplinaDAO().getDisciplinasDeportista(idDeportista);
        try {
//            for (Integer idDisciplina : listaIdsDisciplinas){
                String sql = "SELECT * FROM disciplina WHERE id IN (?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                Disciplina[] asdf =new Disciplina[5];
                Array array = connection.createArrayOf("Disciplina", listaIdsDisciplinas.toArray());
                statement.setArray(1, array);
                ResultSet disciplinasBD = statement.executeQuery();

                while (disciplinasBD.next())
                    listaDisciplinasAsStrings.add(new Disciplina(disciplinasBD.getString("nombre")));
//            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }
        return listaDisciplinasAsStrings;
    }

    /**
     * Se obtiene una lista de
     * @param idDeportista
     * @return
     */
    @Override
    public List<String> getDisciplinasDeportistaAsStrings(int idDeportista){
        Connection connection = MiConnection.getCon();
        List<String> listaDisciplinasAsStrings = new LinkedList<>();

        try {
            String sql = "SELECT nombre FROM disciplina WHERE id IN (SELECT id_disciplina FROM deportista_en_disciplina WHERE id_deportista = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDeportista);
            ResultSet disciplinasBD = statement.executeQuery();

            while (disciplinasBD.next()){
                listaDisciplinasAsStrings.add(disciplinasBD.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }
        return listaDisciplinasAsStrings;
    }
}
