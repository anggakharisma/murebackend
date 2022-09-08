package com.murebackend.murebackend.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void registerTest() throws Exception {
		Map<String, Object> body = new HashMap<>();
		body.put("email", "adminxxx@admin.com");
		body.put("name", "admin");
		body.put("password", "admin");
		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

void registerSameEmailFailed() throws Exception {
		Map<String, Object> body = new HashMap<>();
		body.put("email", "adminxxx@admin.com");
		body.put("name", "admin");
		body.put("password", "admin");

		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}
}
