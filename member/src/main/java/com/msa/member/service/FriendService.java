package com.msa.member.service;

import org.springframework.http.ResponseEntity;

public interface FriendService {

    ResponseEntity<Object> makeFriendShip(String userId1, String userId2);

    ResponseEntity<Object> acceptFriendShip(String userId1, String userId2);

    ResponseEntity<Object> friendList(String userId);

    ResponseEntity<Object> friendRequestList(String userId);

}
