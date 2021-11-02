package backend.dao.interfaces;

import objetos.Disciplina;

import java.util.List;

public interface DisciplinaDAO {
    List<Disciplina> getDisciplinas();
    List<Disciplina> getDisciplinasDeportista(int idDeportista);
}
