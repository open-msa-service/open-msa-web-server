package com.msa.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FriendRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(FriendRepositoryTest.class);

    @Autowired private FriendRepository friendRepository;

    @Test
    @DisplayName("친구목록 Id 테스트")
    void allFriendIdList(){
        String userId = "test";
        List<String> list = friendRepository.findAllFriendByUserId(userId);
        for(String str : list)
            logger.info("User id : {}", str);
    }

}