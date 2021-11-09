package backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class MiConnection {
    private static Connection con = null;

    private static void createCon() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tokyo2021_e3", "root", "12345");
            } catch (java.sql.SQLException e) {
                System.out.println("Error de SQL: " + e.getMessage());
            }
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void login(String user, String password){
        if (validCredentials(user, password)){
            createCon();
        }
        else {
            con = null;
        }
    }

    public static boolean validCredentials(String username, String password){
        return (username.compareTo("root") == 0) && (password.compareTo("12345") == 0);
    }

    public static boolean nullConnection(){
        return getCon() == null;
    }
}
