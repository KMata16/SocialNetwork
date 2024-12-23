package com.spring.social.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.social.entity.Users;
import com.spring.social.service.FollowersService;
import com.spring.social.service.UsersService;

@WebMvcTest(SocialMediaController.class)
public class SocialMediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsersService usersService;

    @MockitoBean
    private FollowersService followersService;

    Users userOne;

    @BeforeEach
    void setUp() {
        userOne = Users.builder().accountId(1).username("TestUser").password("password123").build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testAddFollower() {

    }

    @Test
    void testDeleteFollower() {

    }

    @Test
    void testGetAllFollowers() {

    }

    @Test
    void testGetAllFollowing() {

    }

    @Test
    void testLoginUser() {

    }

    @Test
    void testPostUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(userOne);

        when(usersService.persistUsers(userOne)).thenReturn(userOne);

        this.mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}
