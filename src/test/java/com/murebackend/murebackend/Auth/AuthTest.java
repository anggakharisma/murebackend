package com.murebackend.murebackend.Auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.murebackend.murebackend.BaseTest;

public class AuthTest extends BaseTest {

    @BeforeEach
    public void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");
    }

    @Test
    void authenticateTest() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "adminxxx@admin.com");
        body.put("name", "admin");
        body.put("password", "admin");

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        mockMvc.perform(post("/api/auth/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Login success"));
    }

    @Test
    void authenticateUserNotFoundTest() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "adminxxx@admin.com");
        body.put("password", "admin");
        body.put("name", "admin");

        mockMvc.perform(post("/api/users/register/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        Map<String, Object> bodyEmail = new HashMap<>();
        bodyEmail.put("email", "sadminxxx@admin.com");
        bodyEmail.put("password", "123qweasd");
        mockMvc.perform(post("/api/auth/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bodyEmail))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Can't found that user"));
    }

    @Test
    void authenticateWrongPasswordTest() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "adminxxx@admin.com");
        body.put("password", "admin");
        body.put("name", "admin");

        mockMvc.perform(post("/api/users/register/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

        Map<String, Object> bodyEmail = new HashMap<>();
        bodyEmail.put("email", "adminxxx@admin.com");
        bodyEmail.put("password", "123qweasd");
        mockMvc.perform(post("/api/auth/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bodyEmail))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("Wrong password ??"));
    }

    @Test
    void authenticateNoRequestBody() throws Exception {
        mockMvc.perform(post("/api/auth/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").value("The body empty, you can't do that :("));

    }
}