package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConnection {

    private static Connection con = null;

    public static Connection getCon() {
        return con;
    }

    public static void login(String user, String password){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", user, password);
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
            System.out.println("No es posible conectarse a la base de datos");
        }
    }

    public static void disconnect(){
        con = null;
    }

    public static boolean nullConnection(){
        return getCon() == null;
    }
}