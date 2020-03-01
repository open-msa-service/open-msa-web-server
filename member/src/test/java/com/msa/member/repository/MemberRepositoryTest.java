package com.msa.member.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.domain.Member;
import com.msa.member.domain.UserRole;
import com.msa.member.service.MemberService;
import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MemberRepositoryTest.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    private Member member = new Member();
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    @DisplayName("회원정보 수정 테스트")
    void memberProfileUpdateTeest(){
        member.setUserId("test");
        member.setStatusMessage("상태 메시지!");
        member.setEmail("test@test.com");
        member.setPhoneNumber("010-2222-3333");
        member.setProfileHref("/static/images/default_image.png");
        member.setIntroduceMessage("");

        assertDoesNotThrow(()->memberRepository.updateMember(member));

    }


    @BeforeEach
    @Disabled
    void settingMember(){
        this.member.setUserId("testId2");
        this.member.setPassword("test");
        this.member.setUsername("jongmin");
        this.member.setUserRole(UserRole.USER);
        this.member.setEmail("test@test.com");
        this.member.setPhoneNumber("01011112222");
    }

    @Test
    @Disabled
    @DisplayName("Member 저장 테스트")
    void membersaveTest(){
        Member resultMember = memberRepository.save(member);

        assertEquals("jongmin", resultMember.getUsername());
        logger.info("UPDATE TIME : {}", resultMember.getUpdateTime());
    }

    @Test
    @Disabled
    @DisplayName("Member 조회 테스트")
    void memberSearchTest() throws JsonProcessingException {
        ResponseEntity<Object> response = memberService.memberSearchById(1L);
        String values = mapper.writeValueAsString(response.getBody());
        logger.info("Response Body : {}", values);
        assertThrows(NoSuchElementException.class, ()->memberService.memberSearchById(2L)); // 400 Bad Request
    }

    @Test
    @Disabled
    @DisplayName("회원가입 테스트")
    void memberSignupTest() throws JsonProcessingException {
        ResponseEntity<Object> response = memberService.memberSignUp(member);
        String values = mapper.writeValueAsString(response.getBody());
        logger.info("Response Body : {}", values);
    }

}