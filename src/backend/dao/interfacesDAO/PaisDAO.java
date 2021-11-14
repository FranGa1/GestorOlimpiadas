package backend.dao.interfacesDAO;

import objetos.Pais;

import java.util.List;

public interface PaisDAO {
    void cargar(Pais nuevoPais) throws Exception;
    void eliminar(Pais paisEliminar) throws Exception;
    void editar(Pais paisEditar) throws Exception;
    boolean existe(Pais pais) throws Exception;
    Pais encontrar(int id) throws Exception;
    List<Pais> getPaises() throws Exception;
    List<String> getPaisesAsStrings() throws Exception;
}
