package com.hillel.fedorenko.jdbc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static java.util.Objects.nonNull;

public class StoreConnection {
    public static Connection provideConnection() {
        Properties properties = new Properties();
        try (InputStream inputStream = StoreConnection
                .class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot read properties");
            return null;
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/store", properties);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Cannot provide connection");
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Cannot close connection");
        }
    }

    public static int update(String sql) {
        Connection connection = provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                return statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection(connection);
            }
        }
        return 0;
    }
}
