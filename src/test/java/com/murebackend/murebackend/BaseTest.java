package com.murebackend.murebackend;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "spring.profiles.active:test")
@AutoConfigureMockMvc
public class BaseTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public ObjectMapper objectMapper;

}