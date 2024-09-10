package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github

public class DB_Connection {
    private static Connection connectionInstance;

    public DB_Connection() {
        String user = "";
        String password = "";
        String url = "";

        try {
            if (connectionInstance == null) {
                connectionInstance = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e);
        }
    }

    public Connection getConnection() {
        return connectionInstance;
    }

}
