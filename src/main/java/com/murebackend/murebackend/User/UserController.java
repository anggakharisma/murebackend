package com.murebackend.murebackend.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.murebackend.murebackend.Request.UserRequest;
import com.murebackend.murebackend.Role.Role;
import com.murebackend.murebackend.Role.RoleRepository;
import com.murebackend.murebackend.Utils.FileUploadUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/image")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile multipartFile)
			throws IOException {

		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String fileCode = FileUploadUtil.saveFile(fileName, multipartFile, "images/user");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());

		String fileNameFull = fileCode + "-" + fileName;
		userRepository.updateImage(user, fileNameFull);

		Map<String, Object> response = new HashMap<>();
		response.put("full_name", fileCode + "-" + fileName);
		response.put("download_url", "/images/user/" + fileNameFull);
		response.put("message", "File upload completed successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest user) {
		try {
			Map<String, Object> response = new HashMap<>();
			Long userId = userRepository
					.save(new User(user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword())));

			if (user.roleName == null || user.roleName.isEmpty()) {
				user.roleName = "ROLE_USER";
			}

			Role role = roleRepository.findByName(user.roleName);
			userRepository.addRole(role.getId(), userId);

			response.put("message", userId + " registered");
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", "User already registered");
			return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
		} catch (Exception e) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
