package backend.dao.interfacesDAO;

import objetos.Disciplina;
import java.util.List;

public interface DeportistaEnDisciplinaDAO {
    void cargarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws Exception;
    void eliminarDisciplinasDeportista(int idDeportista) throws Exception;
    void editarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws Exception;
    List<Integer>  getIDsDisciplinasDeportista(int idDeportista) throws Exception;
    List<Disciplina> getDisciplinasDeportista(int idDeportista) throws Exception;
}

