package com.msa.member.service;

import com.msa.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

    ResponseEntity<Object> memberSearchById(Long id);

    ResponseEntity<Object> memberSearchByUserId(String userId);

    ResponseEntity<Object> memberSignUp(Member member);

    ResponseEntity<Object> memberUpdateProfile(MultipartFile[] files, String members);

    ResponseEntity<Object> memberSerarchMyTimeline(String userId);

    ResponseEntity<Object> memberSearchByName(String username);

    ResponseEntity<Object> memberSearchTimeLine(String userId1, String userId2);

    ResponseEntity<Object> mainTimeLineList(String userId);

}
