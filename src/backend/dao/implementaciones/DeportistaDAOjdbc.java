package backend.dao.implementaciones;

import backend.MiConnection;
import backend.dao.FactoryDAO;
import backend.dao.interfaces.DeportistaDAO;
import backend.dao.interfaces.PaisDAO;
import objetos.Deportista;
import objetos.Disciplina;
import objetos.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DeportistaDAOjdbc implements DeportistaDAO {

    /**
     * Se carga un deportista en la base de datos
     * @param deportistaNuevo
     */
    @Override
    public int cargar(Deportista deportistaNuevo) {

        // Se establece la conexion a la BD
        Connection connection = MiConnection.getCon();

        try {
            // Se inserta en la tabla deportista al deportistaNuevo
            String sql =  "INSERT INTO deportista (apellidos, nombres, email, telefono, id_pais) VALUES(?,?,?,?,(SELECT id FROM pais WHERE nombre=?))";
            PreparedStatement statementDeportista = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statementDeportista.setString(1, deportistaNuevo.getApellido());
            statementDeportista.setString(2, deportistaNuevo.getNombre());
            statementDeportista.setString(3, deportistaNuevo.getEmail());
            statementDeportista.setString(4, deportistaNuevo.getTelefono());
            statementDeportista.setString(5, deportistaNuevo.getPais().getNombre());
            statementDeportista.executeUpdate();

            // Se obtiene el id del deportista recien agregado
            ResultSet generatedKeysResultSet = statementDeportista.getGeneratedKeys();
            generatedKeysResultSet.next();
            int idDeportista = generatedKeysResultSet.getInt(1);

            // Se le asignan las disciplinas al deportista
            sql =  "INSERT INTO deportista_en_disciplina(id_deportista, id_disciplina) VALUES(?,(SELECT id FROM disciplina WHERE nombre=?))";
            PreparedStatement statementDisciplina = connection.prepareStatement(sql);

            ArrayList<Disciplina> disciplinasDeportista = deportistaNuevo.getDisciplinas();
            for(Disciplina disciplina : disciplinasDeportista){
                statementDisciplina.setInt(1, idDeportista);
                statementDisciplina.setString(2, disciplina.getNombre());
                statementDisciplina.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            return 1;
        }
        return 0;
    }

    /**
     * Se elimina un deportista de la base de datos
     * @param deportistaEliminar
     */
    @Override
    public int eliminar(Deportista deportistaEliminar) {

        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();

        try {
            String sql = "DELETE FROM deportista WHERE (email=? and nombres=? and apellidos=? and telefono=?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, deportistaEliminar.getEmail());
            statement.setString(2, deportistaEliminar.getNombre());
            statement.setString(3, deportistaEliminar.getApellido());
            statement.setString(4, deportistaEliminar.getTelefono());
            statement.executeUpdate();

            sql = "DELETE FROM deportista_en_disciplina WHERE id_deportista=?";
            statement = connection.prepareStatement(sql);
            // get id deportista statement.setInt();
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
            return 1;
        }

        return 0;
    }

    @Override
    public int editar(Deportista deportistaEditar, Deportista deportistaEditado) {

        return 0;
    }

    @Override
    public List<Deportista> getDeportistas() {
        // Se establece la conexcion con la base de datos
        Connection connection = MiConnection.getCon();
        List<Deportista> listasDeportistas = new LinkedList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet deportistasBD = statement.executeQuery(sql);

            while (deportistasBD.next()){
                String apellido = deportistasBD.getString("apellidos");
                String nombre = deportistasBD.getString("nombres");
                String telefono = deportistasBD.getString("telefono");
                String email = deportistasBD.getString("email");
                int idPais = Integer.parseInt(deportistasBD.getString("id_pais"));
                PaisDAO paisDAO = FactoryDAO.getPaisDAO();
                Pais pais = paisDAO.encontrar(idPais);

                // IMPORTANTE FALTA AGREGAR EL ARRAY LIST DE DISCIPLINAS

                listasDeportistas.add(new Deportista(nombre, apellido, email, telefono, pais));
            }
        } catch (SQLException e){
            System.out.println("Error de SQL: "+e.getMessage());
        }

        return listasDeportistas;
    }
}
