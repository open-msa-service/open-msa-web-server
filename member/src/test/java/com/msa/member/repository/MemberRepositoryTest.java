package com.msa.member.repository;

import com.msa.member.domain.Member;
import com.msa.member.domain.UserRole;
import com.msa.member.service.MemberService;
import org.junit.jupiter.api.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(MemberRepositoryTest.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    private Member member = new Member();

    @BeforeEach
    void settingMember(){
        this.member.setUserId("testId");
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
    @DisplayName("Member 조회 테스트")
    void memberSearchTest(){
        assertEquals("jongmin", memberService.memberSearchById(1L).getUsername());
        assertThrows(ResponseStatusException.class, ()->memberService.memberSearchById(2L)); // 404 Bad Request
        assertEquals("test@test.com", memberService.memberSearchByUserId("testId").getEmail());
    }

    @Test
    @DisplayName("회원가입 테스트")
    void memberSignupTest(){

    }

}