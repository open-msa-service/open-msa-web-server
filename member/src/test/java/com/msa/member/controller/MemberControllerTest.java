package com.msa.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.domain.Member;
import com.msa.member.domain.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(MemberControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원 Id 조회")
    void findById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/info/id/2").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print()).andExpect(status().isBadRequest()).andReturn();
        String expectedResponseBody = objectMapper.writeValueAsString(mvcResult.getResponse().getContentAsString());
        logger.info("ResponseBody : {}", expectedResponseBody);
    }

    @Test
    @Disabled
    @DisplayName("회원가입")
    void signupTest() throws Exception {
        Member member = new Member();
        member.setUserId("jm0520");
        member.setPassword("test");
        member.setUsername("jongminlee");
        member.setUserRole(UserRole.USER);
        member.setEmail("test@test.com");
        member.setPhoneNumber("01011112222");
        String content = objectMapper.writeValueAsString(member);
        MvcResult mvcResult = mockMvc.perform(post("/user/signup").content(content).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String responseString = objectMapper.writeValueAsString(mvcResult.getResponse().getContentAsString());
        logger.info("ResponseBody : {}", responseString);
    }

}