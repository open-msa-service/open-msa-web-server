package com.msa.timeline.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.timeline.domain.Like;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.repository.TimeLineRepository;
import com.msa.timeline.service.LikeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LikeServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(LikeServiceImplTest.class);

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private LikeService likeService;

    @Test
    @DisplayName("TimeLine Like 연동 테스트")
    void timelineComBineLike(){
        List<TimeLine> timeLines = timeLineRepository.findAll();
        for(TimeLine timeLine : timeLines)
            logger.info(timeLine.getContent() + ", " + timeLine.getLikes().size());
    }


    @Test
    @DisplayName("좋아요 클릭시")
    void clickLike() throws JsonProcessingException {
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(30L);
        Like like = new Like();
        like.setUserId("test");
        like.setTimeId(timeLine);
        ResponseEntity<Object> responseEntity = likeService.clickLikeService(like);

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(responseEntity.getBody());

        logger.info("Response Body : {}", result);
    }

}