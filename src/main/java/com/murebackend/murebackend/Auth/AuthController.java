package com.murebackend.murebackend.Auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murebackend.murebackend.Config.JwtTokenUtil;
import com.murebackend.murebackend.Role.Role;
import com.murebackend.murebackend.User.User;
import com.murebackend.murebackend.User.UserDTO;
import com.murebackend.murebackend.User.UserRepository;

import lombok.extern.slf4j.Slf4j;

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

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/auth")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
		try {
			User user = userRepository.findByEmail(authRequest.getEmail());
			List<Role> roles = userRepository.getUserRoles(user);
			user.setRoles(roles);
			UserDTO userDetail = modelMapper.map(user, UserDTO.class);

			if(!passwordEncoder.matches(authRequest.getPassword(),
					user.getPassword())) {
				Map<String, Object> responseErr = new HashMap<>();
				responseErr.put("message", "Wrong password ??");
				return new ResponseEntity<>(responseErr, HttpStatus.BAD_REQUEST);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("message", "Login success");
			response.put("userInfo", userDetail);
			response.put("token", jwtTokenUtil.generateAccessToken(user));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(EmptyResultDataAccessException e) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Can't found that user");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Whoops something went wrong");
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
