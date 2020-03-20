package msa.demo.timeline.event;


import msa.demo.timeline.service.TimeLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 이벤트를 받고 연관된 비즈니스 로직을 동작시킴
 */

@Component
public class EventHandler {

    private static final Logger log = LoggerFactory.getLogger(EventHandler.class);

    private TimeLineService timeLineService;

    public EventHandler(TimeLineService timeLineService) {
        this.timeLineService = timeLineService;
    }

    @RabbitListener(queues = "${msa.queue}")
    void handleMemberSolved(final MemberSolvedEvent event){
        log.info("Member Solved Event 수신 : {}", event.getUserId());
        try{
            timeLineService.updateMemberProfileHref(event.getUserId(), event.getProfileHref());
        }catch (final Exception e){
            log.error("MemberSolvedEvent 처리시 에러", e);
            // 헤딩 이벤트가 다시 큐로 돌아가거나 두 번 처리되지 않도록 예외 발생
            throw new AmqpRejectAndDontRequeueException("프로필 사진 수정 중에 에러가 발생했습니다.");
        }
    }
    
}
