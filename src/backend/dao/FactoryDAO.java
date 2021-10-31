package backend.dao;

import backend.dao.implementaciones.DeportistaDAOjdbc;
import backend.dao.implementaciones.DisciplinaDAOjdbc;
import backend.dao.implementaciones.PaisDAOjdbc;
import backend.dao.interfaces.DeportistaDAO;
import backend.dao.interfaces.DisciplinaDAO;
import backend.dao.interfaces.PaisDAO;

public class FactoryDAO {
    public static DeportistaDAO getDeportistaDAO(){
        return new DeportistaDAOjdbc();
    }

    public static PaisDAO getPaisDAO(){
        return new PaisDAOjdbc();
    }

    public static DisciplinaDAO getDisciplinaDAO() { return new DisciplinaDAOjdbc(); }
}
