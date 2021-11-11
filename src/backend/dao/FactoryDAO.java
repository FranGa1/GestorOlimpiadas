package backend.dao;

import backend.dao.implementacionesDAO.DeportistaDAOjdbc;
import backend.dao.implementacionesDAO.DeportistaEnDisciplinaDAOjdbc;
import backend.dao.implementacionesDAO.DisciplinaDAOjdbc;
import backend.dao.implementacionesDAO.PaisDAOjdbc;
import backend.dao.interfacesDAO.DeportistaDAO;
import backend.dao.interfacesDAO.DeportistaEnDisciplinaDAO;
import backend.dao.interfacesDAO.DisciplinaDAO;
import backend.dao.interfacesDAO.PaisDAO;

public class FactoryDAO {
    public static DeportistaDAO getDeportistaDAO(){
        return new DeportistaDAOjdbc();
    }

    public static PaisDAO getPaisDAO(){
        return new PaisDAOjdbc();
    }

    public static DisciplinaDAO getDisciplinaDAO() { return new DisciplinaDAOjdbc(); }

    public static DeportistaEnDisciplinaDAO getDeporEnDisciplinaDAO() { return new DeportistaEnDisciplinaDAOjdbc(); }
}
