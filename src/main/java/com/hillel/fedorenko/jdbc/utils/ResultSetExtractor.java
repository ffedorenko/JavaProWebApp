package com.hillel.fedorenko.jdbc.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetExtractor <T> {
    T extract (ResultSet resultSet) throws SQLException;
}
