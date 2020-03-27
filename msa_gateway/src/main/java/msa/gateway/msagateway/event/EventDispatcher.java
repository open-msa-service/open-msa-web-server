package msa.gateway.msagateway.event;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 이벤트 버스와의 통신을 처리
 */
@Component
public class EventDispatcher {

    private RabbitTemplate rabbitTemplate;

    // Account 정보를 전달하기 위한 exchange
    private String gateWayExchange;

    // 특정 이벤트를 전송하기 위한 라우팅 키
    private String gateWaySolvedRoutingKey;

    @Autowired
    EventDispatcher(final RabbitTemplate rabbitTemplate,
                    @Value("${msa.exchange}") final String gateWayExchange,
                    @Value("${gateway.solved.key}") final String gateWaySolvedRoutingKey){
        this.rabbitTemplate = rabbitTemplate;
        this.gateWayExchange = gateWayExchange;
        this.gateWaySolvedRoutingKey = gateWaySolvedRoutingKey;
    }

    public void send(final GatewaySolvedEvent gatewaySolvedEvent){
        rabbitTemplate.convertAndSend(
                gateWayExchange,
                gateWaySolvedRoutingKey,
                gatewaySolvedEvent
        );
    }

}
