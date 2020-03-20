package msa.demo.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.demo.member.domain.Member;
import msa.demo.member.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(MemberController.class)
class MemberControllerTest {

    private static final Logger log = LoggerFactory.getLogger(MemberControllerTest.class);

    @MockBean
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    // 이 객체는 initFields() 메서드를 이용해 자동으로 초기화
    private JacksonTester<Member> jsonMember;

    @BeforeEach
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    void getUserInfoByUserId() throws Exception {
        String userId = "test";

        MockHttpServletResponse response = mockMvc.perform(get("/user/" + userId)).andReturn().getResponse();

        log.info("response : {}", response.getContentAsString());
    }

    @Test
    void getAllUserInfoByUsername() throws Exception {
        String username = "jongmin";

        MockHttpServletResponse response = mockMvc.perform(get("/user/search/" + username)).andReturn().getResponse();

        log.info("response : {}", response.getContentAsString());
    }

}