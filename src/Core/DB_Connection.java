package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static Connection connectionInstance;

    public static Connection getConnection() {
        String user = "root";
        String password = "iLaRAsPEdRAN";
        String url = "jdbc:postgresql://localhost:5432/youhotel";
        try {
            if (connectionInstance == null) {
                connectionInstance = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e);
        }
        return connectionInstance;
    }

}
