package com.murebackend.murebackend;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class MurebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MurebackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@GetMapping("/")
	public ResponseEntity<?> check() {
		Map<String, Object> response = new HashMap<>();

		response.put("message", "its on");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
