package msa.member.msamember.event;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Member;
import msa.member.msamember.domain.UserRole;
import msa.member.msamember.service.MemberService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;


/**
 *  이벤트를 받고 연관된 비즈니스 로직을 동작시킴
 */
@Slf4j
@Component
public class EventHandler {

    private final MemberService memberService;

    public EventHandler(MemberService memberService) {
        this.memberService = memberService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @RabbitListener(queues = "gateway_member_queue")
    void handleGatewaySolved(final GatewaySolvedEvent event){
        log.info("GatewaySolvedEvent 수신 : {}", event.getAccount());
        Member member = new Member();
        try {
            JsonNode jsonNode = new ObjectMapper().readValue(event.getAccount(), JsonNode.class);
            member.setUserId(jsonNode.get("userId").asText());
            member.setPassword(encoder().encode(jsonNode.get("password").asText()));
            member.setUsername(jsonNode.get("username").asText());
            member.setSocialId(jsonNode.get("social_id").asText());
            member.setEmail(jsonNode.get("email").asText());
            member.setPhoneNumber(jsonNode.get("phoneNumber").asText());
            member.setUpdateTime(LocalDateTime.now());
            member.setUserRole(UserRole.ROLE_USER);
        } catch (IOException e) {
            try {
                throw new IOException("String to Object 변환에 실패했습니다.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try{
            memberService.memberSignUp(member);
        }catch (final Exception e){
            log.error("Gateway Solved Event 처리 시 에러", e);
            throw new AmqpRejectAndDontRequeueException("이벤트 수신시 에러가 발생했습니다.");
        }


    }



}
