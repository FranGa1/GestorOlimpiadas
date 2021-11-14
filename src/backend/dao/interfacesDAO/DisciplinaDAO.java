package backend.dao.interfacesDAO;

import objetos.Disciplina;

import java.util.List;

public interface DisciplinaDAO {
    List<Disciplina> getDisciplinas() throws Exception;
    List<String> getDisciplinasAsStrings() throws Exception;
    List<Disciplina> getDisciplinasSeleccionadas(List<Integer> disciplinasSeleccionadas) throws Exception;
    int getIDDisciplina(Disciplina disciplina) throws Exception;
}
