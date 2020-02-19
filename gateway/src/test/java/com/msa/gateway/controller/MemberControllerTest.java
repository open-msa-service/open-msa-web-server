package com.msa.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.gateway.domain.Account;
import com.msa.gateway.domain.UserRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    private static final String BASE_URL = "http://localhost:8082/user";

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    @Autowired
    private ObjectMapper mapper;

    private RestTemplate restTemplate;

    @Autowired
    MemberControllerTest(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 연동 TEST")
    void combineSignupTest() throws Exception {
        Account account = new Account();
        account.setUserId("testId7");
        account.setEmail("test@3");
        account.setPassword("test");
        account.setUsername("jongmin");
        account.setUserRole(UserRole.USER);
        account.setPhoneNumber("01011112222");
        String requestObject = mapper.writeValueAsString(account);
        MvcResult mvcResult = mockMvc.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestObject)).andDo(print()).andReturn();
        log.info("Response Status : {}", mvcResult.getResponse().getStatus());
        log.info("Response Body : {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @Disabled
    @DisplayName("회원가입 test")
    void signupTest() throws JsonProcessingException {
        Account account = new Account();
        account.setUserId("testId5");
        account.setEmail("test@3");
        account.setPassword("test");
        account.setUsername("jongmin");
        account.setUserRole(UserRole.USER);
        account.setPhoneNumber("01011112222");

        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(BASE_URL+"/signup", account, Object.class);
        String getbody = mapper.writeValueAsString(responseEntity.getBody());
        log.info("Response status : {}", responseEntity.getStatusCode());
        log.info("Response body : {}", getbody);
    }
}