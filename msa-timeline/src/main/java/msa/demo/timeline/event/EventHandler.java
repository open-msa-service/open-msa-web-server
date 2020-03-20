package msa.demo.timeline.event;


import msa.demo.timeline.service.TimeLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    
}
