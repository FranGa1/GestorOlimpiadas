package backend.dao.interfacesDAO;

import objetos.Disciplina;

import java.util.List;

public interface DisciplinaDAO {
    List<Disciplina> getDisciplinas();
    List<String> getDisciplinasAsStrings();
    List<Disciplina> getDisciplinasDeportista(int idDeportista);
    List<String> getDisciplinasDeportistaAsStrings(int idDeportista);
}
