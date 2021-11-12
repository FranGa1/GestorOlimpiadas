package backend.dao.interfacesDAO;

import objetos.Disciplina;

import java.util.List;

public interface DeportistaEnDisciplinaDAO {
    void cargarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista);
    void eliminarDisciplinasDeportista(int idDeportista);
    void editarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista);
    List<Integer>  getDisciplinasDeportista(int idDeportista);
}
