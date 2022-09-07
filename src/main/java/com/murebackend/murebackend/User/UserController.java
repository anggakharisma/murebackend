package com.murebackend.murebackend.User;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, Object>> getAllUser() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "user created");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
		try {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "user" + user.getName() + "created");
			userRepository.save(user);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
