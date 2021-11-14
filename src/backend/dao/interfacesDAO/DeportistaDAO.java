package backend.dao.interfacesDAO;

import objetos.Deportista;

import java.util.List;

public interface DeportistaDAO {
    void cargar(Deportista deportistaNuevo) throws Exception;
    void eliminar(Deportista deportistaEliminar) throws Exception;
    void editar(Deportista deportistaEditar) throws Exception;
    List<Deportista> getDeportistas() throws Exception;
}
