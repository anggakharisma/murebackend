package com.murebackend.murebackend.User;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
public class UserTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@AfterEach
	void tearDown() throws Exception {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
	}

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

	@Test
	void sameEmailShouldFailTest() throws Exception {
		Map<String, Object> body = new HashMap<>();
		body.put("email", "adminxxx@admin.com");
		body.put("name", "admin");
		body.put("password", "admin");

		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(body))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
	}

	@Test
	void emailNamePasswordEmptyTest() throws Exception {
		Map<String, Object> body = new HashMap<>();
		body.put("email", "adminxxx@admin.com");
		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
