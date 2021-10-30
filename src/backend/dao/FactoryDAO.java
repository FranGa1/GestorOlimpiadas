package backend.dao;

import backend.dao.implementaciones.DeportistaDAOjdbc;
import backend.dao.implementaciones.PaisDAOjdbc;
import backend.dao.interfaces.DeportistaDAO;
import backend.dao.interfaces.PaisDAO;

public class FactoryDAO {
    public static DeportistaDAO getDeportistaDAO(){
        return new DeportistaDAOjdbc();
    }

    public static PaisDAO getPaisDAO(){
        return new PaisDAOjdbc();
    }
}
