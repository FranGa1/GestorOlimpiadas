package backend.dao.implementacionesDAO;

import backend.MiConnection;
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

    @Override
    public List<Disciplina> getDisciplinasDeportista(int idDeportista){
        Connection connection = MiConnection.getCon();
        List<Disciplina> listaDisciplinas = new LinkedList<>();

        try {
            String sql = "SELECT * FROM disciplina WHERE id_deportista=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idDeportista);
            ResultSet disciplinasBD = statement.executeQuery(sql);

            while (disciplinasBD.next()){
                listaDisciplinas.add(new Disciplina(disciplinasBD.getString("nombre")));
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listaDisciplinas;
    }
}
