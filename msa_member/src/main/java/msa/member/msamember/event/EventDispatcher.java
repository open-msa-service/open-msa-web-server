package msa.member.msamember.event;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 이벤트 버스와의 통신 처리
 */
@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    // Member관련 정보를 전달하기 위한 익스체인지
    private String memberExchange;

    // 특정 이벤트를 전송하기 위한 라우팅 키
    private String memberSolvedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate, @Value("${msa.exchange}")final String memberExchange, @Value("${msa.solved.key}")final String memberSolvedRoutingKey){
        this.rabbitTemplate = rabbitTemplate;
        this.memberExchange = memberExchange;
        this.memberSolvedRoutingKey = memberSolvedRoutingKey;
    }

    public void send(final MemberSolvedEvent memberSolvedEvent){
        rabbitTemplate.convertAndSend(
                memberExchange,
                memberSolvedRoutingKey,
                memberSolvedEvent
        );
    }


}
