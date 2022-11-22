package com.murebackend.murebackend.Auth;

import com.murebackend.murebackend.Config.JwtTokenUtil;
import com.murebackend.murebackend.User.User;
import com.murebackend.murebackend.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@PostMapping("/auth")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
		try {
			User user = userRepository.findByEmail(authRequest.getEmail());
			log.info(user.getEmail());

			if(!passwordEncoder.matches(authRequest.getPassword(),
					user.getPassword())) {
				Map<String, Object> responseErr = new HashMap<>();
				responseErr.put("message", "Wrong password ??");
				return new ResponseEntity<>(responseErr, HttpStatus.BAD_REQUEST);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("message", "Login success");
			response.put("token", jwtTokenUtil.generateAccessToken(user));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(EmptyResultDataAccessException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Can't found that user");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
