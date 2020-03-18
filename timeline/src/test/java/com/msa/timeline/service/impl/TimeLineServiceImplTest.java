package com.msa.timeline.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.timeline.domain.*;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.repository.LikeRepository;
import com.msa.timeline.repository.TimeLineRepository;
import com.msa.timeline.service.TimeLineService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeLineServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineServiceImplTest.class);

    @Autowired private TimeLineService timeLineService;
    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;


    @Test
    @Disabled
    @DisplayName("게시글 삭제 테스트")
    void deleteTimelineByTimelineId(){
        final long timeId = 12L;
        assertThrows(EmptyResultDataAccessException.class, ()->timeLineService.deleteTimeLineById(timeId));

        final long timeId2 = 19L;
        assertDoesNotThrow(()->timeLineService.deleteTimeLineById(timeId2));
    }

    @Test
    @DisplayName("UserId에 맞는 타임라인 가져오기 테스트")
    void getTimeLineByUserId(){
        String userId = "test";
        List<TimeLine> timeLine = timeLineRepository.findByUserIdOrderByUpdateTimeDesc(userId);
        for(TimeLine timeLine1 : timeLine){
            assertEquals("test", timeLine1.getUserId());
            logger.info("Content : {}", timeLine1.getContent());
        }

    }

    @Test
    @Disabled
    @DisplayName("타임라인 & 댓글 & 좋아요 통합")
    void timeLineAndCommentsAndLikeIntegrateTest() throws JsonProcessingException {
        ResponseEntity<Object> responseEntity = timeLineService.searchAllTimeLine();
        ObjectMapper mapper = new ObjectMapper();
        String values = mapper.writeValueAsString(responseEntity.getBody());

        logger.info("Response Body : {}", values);

    }

    @Test
    @Disabled
    @DisplayName("타임라인 글쓰기 테스트")
    void writeTimeLineTest(){
        TimeLine timeLine = new TimeLine();
        timeLine.setUserId("testId9");
        timeLine.setContent("글쓰기 테스트를 진행하고 2");
        timeLine.setScope(Scope.ALL);
//        assertThrows(DataIntegrityViolationException.class, ()->timeLineService.writeTimeLine(timeLine));
        assertDoesNotThrow(()->timeLineRepository.save(timeLine));
    }

    @Test
    @Disabled
    @DisplayName("타임라인 수정 테스트")
    void writeModifyTimeLineTest(){
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(1L);
        timeLine.setContent("수정!!!222");
        timeLine.setUserId("testId9");
        timeLine.setScope(Scope.ALL);
//        assertDoesNotThrow(()->timeLineService.writeTimeLine(timeLine));
    }

}