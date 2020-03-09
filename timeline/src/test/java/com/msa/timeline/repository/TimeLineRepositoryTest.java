package com.msa.timeline.repository;

import com.msa.timeline.domain.TimeLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeLineRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineRepositoryTest.class);

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Test
    @DisplayName("타임라인 리스트 테스트")
    void timeLineListTest(){
        List<String> userIdList = new ArrayList<>();
        userIdList.add("test");

        List<Long> timeIdList = timeLineRepository.timeLineIdList(userIdList);

        for(Long timeLine : timeIdList)
            logger.info("timeId : {}",timeLine);

        List<TimeLine> timeLines = timeLineRepository.timeLineList(timeIdList);
        for(TimeLine timeLine : timeLines)
            logger.info("timeLine Content : {}", timeLine.getContent());
    }

}