package com.murebackend.murebackend.Auth;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

	@PostMapping("/auth")
	public ResponseEntity<?> authenticateUser() {
		return new ResponseEntity<>("authentaicated", HttpStatus.OK);
	}
}
