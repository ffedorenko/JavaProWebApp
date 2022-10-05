package com.hillel.fedorenko.jdbc.repository;

import com.hillel.fedorenko.jdbc.connection.StoreConnection;
import com.hillel.fedorenko.jdbc.utils.ResultSetExtractor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.util.Objects.nonNull;

public class BaseRepository<T> extends SqlQuery {
    public T getById(int id, String sql, ResultSetExtractor<T> entityExtractor) {
        Connection connection = StoreConnection.provideConnection();
        if (nonNull(connection)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                T res = null;
                while (resultSet.next()) {
                    res = entityExtractor.extract(resultSet);
                }
                return res;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("SQLException");
            } finally {
                StoreConnection.closeConnection(connection);
            }
        }
        return null;
    }
}
