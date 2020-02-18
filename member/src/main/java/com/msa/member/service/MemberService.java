package com.msa.member.service;

import com.msa.member.domain.Member;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<Object> memberSearchById(Long id);

    ResponseEntity<Object> memberSearchByUserId(String userId);

    ResponseEntity<Object> memberSignUp(Member member);

}
