package com.msa.member.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.domain.Member;
import com.msa.member.dtos.ResponseMessage;
import com.msa.member.repository.MemberRepository;
import com.msa.member.service.FileUploadDownloadService;
import com.msa.member.service.MemberService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FileUploadDownloadService fileUploadDownloadService;

    private ResponseMessage responseMessage;

    @Override
    public ResponseEntity<Object> memberSearchById(Long id){
        Member member = Optional.of(memberRepository.findById(id)).get()
                .orElseThrow(NoSuchElementException::new);
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "id:" + id + "의 회원정보를 조회했습니다.", null);
        responseMessage.setData(member, "member");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> memberSearchByUserId(String userId) {
        Member member = Optional.of(memberRepository.findByUserId(userId)).get()
                .orElseThrow(NoSuchElementException::new);
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "사용자 ID:" + userId + "의 회원정보를 조회했습니다.", null);
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
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "회원가입에 성공했습니다.", null);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> memberUpdateProfile(MultipartFile[] files, String members) {

        Member member = convertStringToMember(members);

        try{
            if(files.length != 0){
                setFileNames(files[0], member);
                fileUploadDownloadService.storeFile(files[0]);
            }

            memberRepository.updateMember(member);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("회원정보 수정에 실패했습니다.", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "회원정보 수정에 성공했습니다.", null);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    private void setFileNames(MultipartFile files, Member member) {
        String fileNames = files.getOriginalFilename();
        fileNames = "/static/images/" + fileNames;
        member.setProfileHref(fileNames);
    }

    private Member convertStringToMember(String members) {
        ObjectMapper objectMapper = new ObjectMapper();
        Member member = null;
        try{
            member = objectMapper.readValue(members, Member.class);
        } catch (JsonProcessingException e) {
            throw new DataIntegrityViolationException("", e);
        }
        return member;
    }
}
