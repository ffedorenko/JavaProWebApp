package com.hillel.fedorenko.jdbc.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

public class StoreConnection {
    public static Connection provideConnection() {
        try {
            Context envContext = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/store");
            return ds.getConnection();
        } catch (SQLException | NamingException e) {
            System.err.println("Cannot get connection");
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
