package com.msa.timeline.controller;

import com.msa.timeline.service.TimeLineService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class TimeLineControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(TimeLineControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TimeLineService timeLineService;



    @Test
    @DisplayName("사용자 게시글 목록 가져오기 Test")
    void searchByUserId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/time/user/test"))
                .andDo(print()).andReturn();
        logger.info(mvcResult.getResponse().getContentAsString());
    }

}