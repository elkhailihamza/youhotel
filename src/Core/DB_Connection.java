package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static Connection connectionInstance;

    private DB_Connection() {}

    public static Connection getConnection() {
        String user = "root";
        String password = "iLaRAsPEdRAN";
        String url = "jdbc:postgresql://localhost:5432/youhotel";
        try {
            if (connectionInstance == null || connectionInstance.isClosed()) {
                connectionInstance = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException e) {
            System.out.println("Driver not found or Connection err: "+e);
        }
        return connectionInstance;
    }

}
