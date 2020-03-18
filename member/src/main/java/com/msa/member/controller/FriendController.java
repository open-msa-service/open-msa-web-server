package com.msa.member.controller;

import com.msa.member.domain.Friend;
import com.msa.member.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    // 친구 요청
    @PostMapping("/request")
    ResponseEntity<Object> requestFriend(@RequestBody Friend friend) {
        return friendService.makeFriendShip(friend.getUserId1(), friend.getUserId2());
    }

    // 친구 요청 수락
    @PutMapping("/accept")
    ResponseEntity<Object> acceptFriend(@RequestBody Friend friend) {
        return friendService.acceptFriendShip(friend.getUserId1(), friend.getUserId2());
    }

    // 친구 목록 + 요청 친구목록
    @GetMapping("/allList/{userId}")
    ResponseEntity<Object> allFriendsList(@PathVariable String userId) {
        return friendService.friendRequestList(userId);
    }

    // only 친구 목록
    @GetMapping("/friendList/{userId}")
    ResponseEntity<Object> userFriendsList(@PathVariable String userId) {
        return friendService.friendList(userId);
    }


}
