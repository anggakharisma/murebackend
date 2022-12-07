package com.murebackend.murebackend.User;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import com.murebackend.murebackend.Song.Song;
import com.murebackend.murebackend.Utils.FileDownloadUtil;
import com.murebackend.murebackend.Utils.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;


	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/image/{fileCode}")
	public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
		Song song = new Song("asd", 1924);
		FileDownloadUtil downloadUtil = new FileDownloadUtil();
		UrlResource resource = null;

		try {
			resource = downloadUtil.getFileAsResource(fileCode);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
				.body(resource);
	}

	@PostMapping("/image")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile multipartFile)
			throws IOException {

		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		String fileCode = FileUploadUtil.saveFile(fileName, multipartFile);

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userDetails.getUsername());

		userRepository.updateImage(user, fileCode);

		Map<String, Object> response = new HashMap<>();
		response.put("full_name", fileCode + "-" + fileName);
		response.put("code", fileCode);
		response.put("download_uri", "/files/" + fileCode);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		try {
			Map<String, Object> response = new HashMap<>();
			userRepository.save(new User(user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword())));

			response.put("message", user.getName() + " registered");
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
