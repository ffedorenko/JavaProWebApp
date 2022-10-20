package com.hillel.fedorenko.jdbc.utils;


import com.hillel.fedorenko.jdbc.entity.User;
import com.hillel.fedorenko.jdbc.repository.UserRepository;

import java.util.Optional;

public class UserService {

	private final UserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}

	public Optional<User> findByNameAndPassword(String name, String password) {
		return userRepository.findByNameAndPassword(name, password);
	}

}
