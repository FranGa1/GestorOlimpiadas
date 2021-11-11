package backend.dao.interfacesDAO;

import objetos.Pais;

import java.util.List;

public interface PaisDAO {
    void cargar(Pais nuevoPais);
    void eliminar(Pais paisEliminar);
    void editar(Pais paisEditar);
    boolean existe(Pais pais);
    Pais encontrar(int id);
    List<Pais> getPaises();
    List<String> getPaisesAsStrings();
}
