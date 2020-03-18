package com.msa.timeline.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.timeline.domain.Comment;
import com.msa.timeline.domain.TimeLine;
import com.msa.timeline.repository.CommentRepository;
import com.msa.timeline.service.TimeLineService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TimeLineService timeLineService;


    @Test
    @DisplayName("타임라인과 연동 테스트")
    void commentsCombineTimeLine() throws JsonProcessingException {
        String userId = "test";
        ResponseEntity<Object> responseEntity = timeLineService.searchAllTimeLineByUserId(userId);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(responseEntity.getBody());

        logger.info("getBody : {}", body);

    }


    @Test
    @Disabled
    @DisplayName("댓글 작성 테스트")
    void writeCommentTest(){
        Comment comment = new Comment();
        TimeLine timeLine = new TimeLine();
        timeLine.setTimeId(30L);

        String userId = "test";
        comment.setUserId(userId);
        comment.setTimeId(timeLine);
        comment.setContent("테스트 댓글");

        Comment commentResult = commentRepository.save(comment);
        assertEquals("test", commentResult.getUserId());
    }

}