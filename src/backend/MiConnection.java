package backend;

import java.sql.Connection;
import java.sql.DriverManager;

public class MiConnection {
    private static Connection con = null;

<<<<<<< HEAD
    private static final String accesPassword = "fran";
    private static final String accessUsername = "root";

=======
>>>>>>> 54826b58b3797160c41cec07006df56b7bd5f4d4
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
