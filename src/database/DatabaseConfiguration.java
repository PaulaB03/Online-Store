package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/online_store";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static Connection databaseConnection;

    private DatabaseConfiguration() {}

    public static Connection getDatabaseConnection() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            }
        }
        catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

        return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Error closing the database: " + e.getMessage());
        }
    }
}
