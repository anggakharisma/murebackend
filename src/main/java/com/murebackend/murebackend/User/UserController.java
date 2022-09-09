package com.murebackend.murebackend.User;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public ResponseEntity<?> getAllUser() {
		return new ResponseEntity<>("users", HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			if (user.getName().isEmpty()) return new ResponseEntity<>("Name cannot be empty", HttpStatus.BAD_REQUEST);
			Map<String, Object> response = new HashMap<>();
			userRepository.save(new User(user.getName(), user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword())));

			response.put("message", user.getName() + " registered");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "User already registered");
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		}
	}
}
