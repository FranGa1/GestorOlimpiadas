package backend.dao.interfacesDAO;

import objetos.Pais;

import java.util.List;

public interface PaisDAO {
    int cargar(Pais nuevoPais);
    int eliminar(Pais paisEliminar);
    int editar(Pais paisEditar);
    boolean existe(Pais pais);
    Pais encontrar(int id);
    List<Pais> getPaises();
    List<String> getPaisesAsStrings();
}
