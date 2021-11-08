package backend.dao.interfaces;

import objetos.Deportista;

import java.util.List;

public interface DeportistaDAO {
    int cargar(Deportista deportistaNuevo);
    int eliminar(Deportista deportistaEliminar);
    int editar(Deportista deportistaEditar);
    int getIdDeportista(Deportista deportista);
    List<Deportista> getDeportistas();
}
