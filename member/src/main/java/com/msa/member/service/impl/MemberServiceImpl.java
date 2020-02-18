package com.msa.member.service.impl;

import com.msa.member.domain.Member;
import com.msa.member.dtos.ResponseMessage;
import com.msa.member.repository.MemberRepository;
import com.msa.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> memberSearchById(Long id){
        Member member = Optional.of(memberRepository.findById(id)).get()
                .orElseThrow(NoSuchElementException::new);
        responseMessage = new ResponseMessage(HttpStatus.OK, "id:" + id + "의 회원정보를 조회했습니다.", null);
        responseMessage.setData(member, "member");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> memberSearchByUserId(String userId) {
        Member member = Optional.of(memberRepository.findByUserId(userId)).get()
                .orElseThrow(NoSuchElementException::new);
        responseMessage = new ResponseMessage(HttpStatus.OK, "사용자 ID:" + userId + "의 회원정보를 조회했습니다.", null);
        responseMessage.setData(member, "member");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> memberSignUp(Member member) {
        try{
            memberRepository.save(member);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        responseMessage = new ResponseMessage(HttpStatus.OK, "회원가입에 성공했습니다.", null);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }
}
