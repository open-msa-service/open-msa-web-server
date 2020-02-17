package com.msa.member.service.impl;

import com.msa.member.domain.Member;
import com.msa.member.repository.MemberRepository;
import com.msa.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member memberSearchById(Long id){
        return Optional.of(memberRepository.findById(id)).get()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "가입하지 않은 아이디이거나, 잘못된 비밀번호 입니다."));
    }

    @Override
    public Member memberSearchByUserId(String userId) {
        return Optional.of(memberRepository.findByUserId(userId)).get()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "가입하지 않은 아이디이거나, 잘못된 비밀번호 입니다."));
    }
}
