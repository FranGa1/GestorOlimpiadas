package backend.dao.interfacesDAO;

import objetos.Deportista;

import java.util.List;

public interface DeportistaDAO {
    int cargar(Deportista deportistaNuevo);
    int eliminar(Deportista deportistaEliminar);
    int editar(Deportista deportistaEditar);
    List<Deportista> getDeportistas();
}
