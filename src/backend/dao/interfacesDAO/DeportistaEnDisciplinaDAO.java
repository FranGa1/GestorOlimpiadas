package backend.dao.interfacesDAO;

import objetos.Disciplina;

import java.sql.SQLException;
import java.util.List;

public interface DeportistaEnDisciplinaDAO {
    void cargarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws SQLException  ;
    void eliminarDisciplinasDeportista(int idDeportista) throws SQLException;
    void editarDisciplinasDeportista(List<Disciplina> disciplinasDeportista, int idDeportista) throws SQLException;
    List<Integer>  getIDsDisciplinasDeportista(int idDeportista) throws SQLException;
    List<Disciplina> getDisciplinasDeportista(int idDeportista) throws SQLException;
}

