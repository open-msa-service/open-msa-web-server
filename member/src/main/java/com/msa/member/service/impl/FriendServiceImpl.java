package com.msa.member.service.impl;


import com.msa.member.domain.Friend;
import com.msa.member.domain.Member;
import com.msa.member.domain.State;
import com.msa.member.dtos.ResponseMessage;
import com.msa.member.repository.FriendRepository;
import com.msa.member.repository.MemberRepository;
import com.msa.member.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendServiceImpl implements FriendService {

    private static final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepository memberRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> makeFriendShip(String userId1, String userId2) {
        // userId1 이 userId2에 요청
        try{
            if(friendRepository.countFriendByUserId1AndUserId2AndState(userId1, userId2, State.FRIEND) != 1
            && friendRepository.countFriendByUserId1AndUserId2AndState(userId1, userId2, State.WAIT) == 0){
                Friend friend = new Friend(userId1, userId2, State.WAIT);
                friendRepository.save(friend);
            }
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("친구 요청에 실패했습니다.", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "친구 요청이 성공했습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> acceptFriendShip(String userId1, String userId2) {
        // userId1이 userId2d의 요청을 수락
        // DB: userId1 == Entity userId2
        try{
                Friend friend = new Friend(userId1, userId2, State.FRIEND);
                friend.setState(State.FRIEND);
                friendRepository.save(friend);
                Friend friend2 = new Friend(userId2, userId1, State.FRIEND); // 기존 update
                friend2.setState(State.FRIEND);
                friendRepository.updateFriend(friend2);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("친구 맺기에 실패했습니다.", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "친구가 되었습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> friendList(String userId) {

        List<Friend> friends = null;
        try{
            friends = friendRepository.findFriendByUserId1AndState(userId, State.FRIEND);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("친구목록 가져오기에 실패했습니다.", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "친구목록을 가져왔습니다.", null);
        responseMessage.setData(friends, "friend");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> friendRequestList(String userId) {
        List<Friend> friends = null;
        List<Friend> requestFriend = null;
        List<Member> members = new ArrayList<>();
        List<Member> requestMember = new ArrayList<>();
        try{
            friends = friendRepository.findFriendByUserId1AndState(userId, State.FRIEND);
            requestFriend = friendRepository.findFriendByUserId2AndState(userId, State.WAIT);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("친구목록 가져오기에 실패했습니다.", ex);
        }
        for(Friend friend : friends){
            String friendId = friend.getUserId2();
            Member member = Optional.of(memberRepository.findByUserId(friendId)).get().orElseThrow(NoSuchElementException::new);
            members.add(member);
        }
        for(Friend friend : requestFriend){
            String friendId = friend.getUserId1();
            Member member = Optional.of(memberRepository.findByUserId(friendId)).get().orElseThrow(NoSuchElementException::new);
            requestMember.add(member);
        }


        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "친구목록을 가져왔습니다.", null);
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("friend", members);
        requestMap.put("requestFriend", requestMember);
        responseMessage.setData(requestMap);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
