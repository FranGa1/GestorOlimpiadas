package backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class MiConnection {
    private static Connection con = null;
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", "root", "fran");
        } catch (java.sql.SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
    public static Connection getCon() {
        return con;
    }

    public static boolean validCredentials(String username, String password){
        return (username.compareTo("root") == 0) && (password.compareTo("fran") == 0);
    }
}
