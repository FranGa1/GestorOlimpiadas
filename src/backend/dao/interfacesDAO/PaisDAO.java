package backend.dao.interfacesDAO;

import objetos.Pais;

import java.sql.SQLException;
import java.util.List;

public interface PaisDAO {
    void cargar(Pais nuevoPais) throws SQLException;
    void eliminar(Pais paisEliminar) throws SQLException;
    void editar(Pais paisEditar) throws SQLException;
    boolean existe(Pais pais) throws SQLException;
    Pais encontrar(int id) throws SQLException;
    List<Pais> getPaises();
    List<String> getPaisesAsStrings() throws SQLException;
}
