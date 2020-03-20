package msa.demo.timeline.service;

import msa.demo.timeline.domain.Scope;
import msa.demo.timeline.domain.TimeLine;
import msa.demo.timeline.repository.TimeLineRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TimeLineServiceImplTest {

    @Autowired
    private TimeLineRepository timeLIneRepository;

    @Autowired
    private TimeLineService timeLineService;

    @Test
    @Disabled
    void deleteTimeLineById(){
        Long timeId = 1L;
        timeLineService.deleteTimeLineByTimeId(null);
    }

    @Test
    void updateTimeLine(){
        TimeLine timeLine = new TimeLine();

        timeLineService.updateTImeLine(timeLine);
    }


    @Test
    @Disabled
    void writeTimeLine(){
        TimeLine timeLine = new TimeLine();
        timeLine.setContent("게시글 테스트");
        timeLine.setScope(Scope.ALL);
        timeLine.setUpdateTime(LocalDateTime.now());
        timeLine.setUserId("test");
        timeLine.setUsername("jongmin");

//        timeLineService.writeTimeLine(timeLine);
    }



}