package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.interfaces.DisciplinaDAO;
import objetos.Disciplina;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
