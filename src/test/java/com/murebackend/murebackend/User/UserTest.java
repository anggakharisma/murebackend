package com.murebackend.murebackend.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

	@Autowired
	MockMvc mockMvc;	

	@Test
	void registerTest() throws Exception {
		mockMvc.perform(get("/api/users/"))
			.andExpect(status().is2xxSuccessful());
	}
}
