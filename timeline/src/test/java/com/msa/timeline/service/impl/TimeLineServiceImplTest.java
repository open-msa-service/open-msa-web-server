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
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TimeLineServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineServiceImplTest.class);

    @Autowired private TimeLineService timeLineService;
    @Autowired private TimeLineRepository timeLineRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private LikeRepository likeRepository;

    @Test
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

    @Test
    @Disabled
    @DisplayName("댓글 쓰기 테스트")
    void writeCommentsTest() throws JsonProcessingException {
        Comment comment = new Comment();
        comment.setContent("댓글1이다.");
        comment.setUserId("testId8");

        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(1L);
//        comment.setTimeId(timeLine);

        ResponseEntity<Object> responseEntity = timeLineService.writeComments(comment);
        ObjectMapper mapper = new ObjectMapper();
        String values = mapper.writeValueAsString(responseEntity.getBody());

        logger.info("Response Body : {}", values);

//        assertDoesNotThrow(()->commentRepository.save(comment));
    }

    @Test
    @Disabled
    @DisplayName("좋아요 누르기, 취소 테스트")
    void clickLikeTest() throws JsonProcessingException {
        Like like = new Like();
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(1L);
//        like.setTimeId(timeLine);
        like.setUserId("testId8");
        like.setLikeType(LikeType.TIMELINE);

//        ResponseEntity<Object> responseEntity = timeLineService.clickLikes(like);
//        ObjectMapper mapper = new ObjectMapper();
//        String values = mapper.writeValueAsString(responseEntity.getBody());

//        logger.info("Response Body : {}", values);

//        ResponseEntity<Object> responseEntity2 = timeLineService.clickLikes(like);
//        String values2 = mapper.writeValueAsString(responseEntity.getBody());
//
//        logger.info("Response Body : {}", values2);

    }

}