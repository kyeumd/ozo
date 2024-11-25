package com.szsleedongkyeum.user.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szsleedongkyeum.user.api.request.LoginRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    @DisplayName("로그인_성공시_토큰을_반환한다")
    void loginTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest("dongTak", "dongtak1");
        String loginRequestString = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/szs/login")
                   .contentType("application/json")
                   .content(loginRequestString)
               )
               .andExpect(status().isOk())
               .andReturn().getResponse().getContentAsString();
    }

    @BeforeEach
    public void setUpData() {
        jdbcTemplate.execute(
            "INSERT INTO USERS(creator_user_id, password, name, reg_no) "
                + "VALUES ('dongTak', 'e4b7c5949ec030a894d9a659ed64276bf8f792a219be0139aedbe89d1ee2efd43df16dd0ca2f6c8a5cb184d90a5fae3c', '동탁', 'zErsYCDjfQ1kf959khrR4A==')");
    }

}