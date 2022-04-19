package org.example.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private final Connection connection;

    public DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "1212");
    }

    public Connection getConnection() {
        return connection;
    }
}
