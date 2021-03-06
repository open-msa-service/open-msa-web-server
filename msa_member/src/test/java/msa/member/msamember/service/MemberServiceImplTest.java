package msa.member.msamember.service;


import msa.member.msamember.domain.Member;
import msa.member.msamember.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


@SpringBootTest
class MemberServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImplTest.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    void searchMemberByUsername(){
        String username = "테스";
        List<Member> list = memberRepository.findByUsernameIgnoreCaseContaining(username);
        for(Member member : list){
            logger.info("member info :::: {}", member.getUsername());
        }
    }

    @Test
    @Disabled
    void findByAllMemberByUsername() {
        String username = "ee";
        List<Member> members = memberService.findAllMemberByUsername(username);

        for (Member member : members)
            System.out.println(member);
    }

    @Test
    @Disabled
    void updateMemberInfo() {
        Member member = new Member();
//        memberService.updateUserInfo(numember);
    }

    @Test
    @Disabled
    @Order(value = 2)
    void getMemberInfoByUserId() throws UserPrincipalNotFoundException {
        String userId = "test";
        Member member = memberService.findMemberInfoByUserId(userId);
        logger.info("Member info : {}", member);
    }

    @Test
    @Disabled
    void memberSignUpTest() {
        Member member = new Member();
        member.setUserId("test");
        member.setEmail("jm0520@hanmail.net");
        member.setIntroduceMessage("ㅎㅇ");
        member.setPassword("1234");
        member.setPhoneNumber("01010101010");
        member.setProfileHref("");
        member.setUsername("jongmin");
        member.setUpdateTime(LocalDateTime.now());

        memberService.memberSignUp(member);
    }

}