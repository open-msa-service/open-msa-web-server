package msa.member.msamember;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Member;
import msa.member.msamember.repository.MemberRepository;
import msa.member.msamember.service.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MsamemberApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(MsamemberApplicationTests.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    void searchMemberByUsername(){
        String username = "테스";
        memberRepository.findByUserId("test");
//        memberRepository.findByUsernameIgnoreCaseContaining(username);
    }

    @Test
    @Disabled
    void contextLoads() throws IOException {
        String tempString = "{\"id\":null,\"userId\":\"test\",\"password\":\"test\",\"username\":null,\"role\":null,\"access_token\":null,\"access_token_validity\":null,\"refresh_token\":null,\"social_id\":null,\"email\":null,\"phoneNumber\":null,\"authorities\":null,\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true}";
        JsonNode object = new ObjectMapper().readValue(tempString, JsonNode.class);

        log.info(String.valueOf(object.get("userId")));
    }

}
