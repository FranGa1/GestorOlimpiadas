package backend.dao.interfacesDAO;

import objetos.Deportista;

import java.sql.SQLException;
import java.util.List;

public interface DeportistaDAO {
    void cargar(Deportista deportistaNuevo) throws SQLException;
    void eliminar(Deportista deportistaEliminar) throws SQLException;
    void editar(Deportista deportistaEditar) throws SQLException;
    List<Deportista> getDeportistas() throws SQLException;
}
