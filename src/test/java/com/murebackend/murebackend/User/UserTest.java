package com.murebackend.murebackend.User;

import com.murebackend.murebackend.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;


public class UserTest  extends BaseTest {

	@AfterEach
	public void tearDown() {
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
		mockMvc.perform(post("/api/users/register")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
