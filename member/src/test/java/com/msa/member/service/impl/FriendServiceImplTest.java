package com.msa.member.service.impl;

import com.msa.member.domain.Friend;
import com.msa.member.repository.FriendRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FriendServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(FriendServiceImplTest.class);

    @Autowired
    private FriendRepository friendRepository;

    @Test
    @DisplayName("친구 맺기")
    void makeFriendShip(){
        String userId1 = "test";
        String userId2 = "test2";

//        if(friendRepository.countFriendByUserId1AndUserId2(userId1, userId2) == 1){
//            logger.info("이미 친구관계입니다.");
//        }else{
//            Friend friend = new Friend(userId1, userId2);
//            friendRepository.save(friend);
//
//            Friend friend1 = new Friend(userId2, userId1);
//            friendRepository.save(friend1);
//            logger.info("친구 완료");
//        }

    }

}