package com.pranay.service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.pranay.model.User;
import com.pranay.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository ur;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// USER ROLE REGISTER
	public User register(User user) {
		if (this.ur.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email alreay exists");
		}
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setRoles(Arrays.asList("USER"));
		this.ur.save(newUser);
		return newUser;
	}

	public User adminRegister(User user) {
		if (this.ur.findByEmail(user.getEmail()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email alreay exists");
		}
		User newAdmin = new User();
		newAdmin.setName(user.getName());
		newAdmin.setEmail(user.getEmail());
		newAdmin.setPassword(encoder.encode(user.getPassword()));
		newAdmin.setRoles(Arrays.asList("USER"));
		this.ur.save(newAdmin);
		return newAdmin;
	}


}
