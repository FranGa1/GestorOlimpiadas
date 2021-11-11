package backend.dao.interfacesDAO;

import objetos.Deportista;

import java.util.List;

public interface DeportistaDAO {
    void cargar(Deportista deportistaNuevo);
    void eliminar(Deportista deportistaEliminar);
    void editar(Deportista deportistaEditar);
    List<Deportista> getDeportistas();
}
