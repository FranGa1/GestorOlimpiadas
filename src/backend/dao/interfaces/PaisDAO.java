package backend.dao.interfaces;

import objetos.Pais;

import java.util.List;

public interface PaisDAO {
    void cargar(Pais nuevoPais);
    void eliminar(Pais paisEliminar);
    void editar(Pais paisEditar, String nombre);
    Pais encontrar(Pais pais);
    List<Pais> getPaises();
}
