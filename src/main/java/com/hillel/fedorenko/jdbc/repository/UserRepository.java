package com.hillel.fedorenko.jdbc.repository;

import com.hillel.fedorenko.jdbc.connection.StoreConnection;
import com.hillel.fedorenko.jdbc.entity.User;
import com.hillel.fedorenko.jdbc.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.Objects.nonNull;

public class UserRepository {

	public Optional<User> findByNameAndPassword(String name, String password) {
		Connection connection = StoreConnection.provideConnection();

		if (nonNull(connection)) {
			try (PreparedStatement statement = connection
					.prepareStatement("select * from user.users where name = ? and password = ?")) {
				statement.setString(1, name);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					return Optional.of(new User(resultSet.getInt("id"), resultSet.getString("name"),
							resultSet.getString("password"), UserRole.valueOf(resultSet.getString("role"))));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return Optional.empty();
			} finally {
				StoreConnection.closeConnection(connection);
			}
		}
		return Optional.empty();
	}

}
