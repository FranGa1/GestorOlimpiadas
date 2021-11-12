package backend.dao.interfacesDAO;

import objetos.Disciplina;

import java.sql.SQLException;
import java.util.List;

public interface DisciplinaDAO {
    List<Disciplina> getDisciplinas() throws SQLException;
    List<String> getDisciplinasAsStrings() throws SQLException;
    List<Disciplina> getDisciplinasSeleccionadas(List<Integer> disciplinasSeleccionadas) throws SQLException;
}
