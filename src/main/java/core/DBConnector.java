package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/vet_management_db";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "648207";

    private DBConnector() {}


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
            throw new SQLException("Driver not found.", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}