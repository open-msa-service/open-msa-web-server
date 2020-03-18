package com.msa.member.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.member.domain.Member;
import com.msa.member.domain.State;
import com.msa.member.dtos.ResponseMessage;
import com.msa.member.repository.FriendRepository;
import com.msa.member.repository.MemberRepository;
import com.msa.member.service.FileUploadDownloadService;
import com.msa.member.service.MemberService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

    private static final String BASE_URL = "http://localhost:8083/time";
    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired private MemberRepository memberRepository;

    @Autowired private FriendRepository friendRepository;

    @Autowired private FileUploadDownloadService fileUploadDownloadService;

    private ResponseMessage responseMessage;
    private RestTemplate restTemplate;

    @Autowired
    MemberServiceImpl(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Override
    public ResponseEntity<Object> memberSearchById(Long id){
        Member member = Optional.of(memberRepository.findById(id)).get()
                .orElseThrow(NoSuchElementException::new);
        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "id:" + id + "의 회원정보를 조회했습니다.", null);
        responseMessage.setData(member, "member");
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> mainTimeLineList(String userId) {
        List<String> friendId = null;
        ResponseEntity<Object> responseEntity = null;
        Map<String, Object> responseMap = new HashMap<>();
        try{
            friendId = friendRepository.findAllFriendByUserId(userId);
            friendId.add(userId);

            responseEntity = restTemplate.postForEntity(BASE_URL+"/main/timeList", friendId, Object.class);
            handleTimelineRestTemplateAndData(userId, responseEntity, responseMap);
        }catch (DataIntegrityViolationException ex){ // member error
            throw new DataIntegrityViolationException("", ex);
        }catch (HttpClientErrorException ex){ // restTemplate error
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ResponseMessage(responseEntity.getStatusCodeValue(), "게시물을 성공적으로 가지고 왔습니다.",null);
        responseMessage.setData(responseMap);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
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
    public ResponseEntity<Object> memberSerarchMyTimeline(String userId) {
        ResponseEntity<Object> responseEntity = null;
        Map<String, Object> responseMap = new HashMap<>();
        try{
            responseEntity = restTemplate.getForEntity(BASE_URL+"/user/"+userId, Object.class);
            handleTimelineRestTemplateAndData(userId, responseEntity, responseMap);

        }catch (DataIntegrityViolationException ex){ // member error
            throw new DataIntegrityViolationException("", ex);
        }catch (HttpClientErrorException ex){ // restTemplate error
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ResponseMessage(responseEntity.getStatusCodeValue(), "게시물을 성공적으로 가지고 왔습니다.",null);
        responseMessage.setData(responseMap);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> memberSearchTimeLine(String userId1, String userId2) {
        // userId1 : my Id, userId2 : search Id
        ResponseEntity<Object> responseEntity = null;
        Map<String, Object> responseMap = new HashMap<>();
        boolean isFriend = false;
        try{
            // "/user/{userId}/{isFriend}"
            int counts = friendRepository.countFriendByUserId1AndUserId2AndState(userId1, userId2, State.FRIEND);
            if(counts != 0 || userId1.equals(userId2)){
                isFriend = true;
            }
            responseEntity = restTemplate.getForEntity(BASE_URL+"/user/" + userId2 + "/" + isFriend, Object.class);
            handleTimelineRestTemplateAndData(userId2, responseEntity, responseMap);

            // 이미 친구 요청을 한 경우 친구요청 버튼을 숨기기위해서 다시 한번 조회한다.
            int counts2 = friendRepository.countFriendByUserId1AndUserId2AndState(userId1, userId2, State.WAIT);
            if(counts2 != 0){
                isFriend = true;
            }
            responseMap.put("isFriend", isFriend);
        }catch (DataIntegrityViolationException ex){ // member error
            throw new DataIntegrityViolationException("", ex);
        }catch (HttpClientErrorException ex){ // restTemplate error
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ResponseMessage(responseEntity.getStatusCodeValue(), "게시물을 성공적으로 가지고 왔습니다.",null);
        responseMessage.setData(responseMap);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    private void handleTimelineRestTemplateAndData(String userId2, ResponseEntity<Object> responseEntity, Map<String, Object> responseMap) {
        int status = responseEntity.getStatusCodeValue();
        if (status != 200) {
            throw new DataIntegrityViolationException("");
        }

        String timeline = getTimeLineData(responseEntity);
        Member member = Optional.of(memberRepository.findByUserId(userId2).get()).orElseThrow(() -> new DataIntegrityViolationException(""));

        responseMap.put("timeline", timeline);
        responseMap.put("member", member);
    }

    private String getTimeLineData(ResponseEntity<Object> responseEntity) {
        HashMap<String, Object> responseBody = (HashMap<String, Object>) responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();

        String tempString;
        try {
            assert responseBody != null;
            tempString = objectMapper.writeValueAsString(responseBody.get("data"));
            JsonNode jsonNode = objectMapper.readValue(tempString, JsonNode.class).get("timeline");
            tempString = objectMapper.writeValueAsString(jsonNode);

        } catch (JsonProcessingException e) {
            throw new DataIntegrityViolationException("");
        }
        return tempString;
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
            String profileHref = member.getProfileHref();
            String userId = member.getUserId();
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put("userId", userId);
            requestMap.put("profileHref", profileHref);

            ObjectMapper objectMapper = new ObjectMapper();
            Object request = objectMapper.writeValueAsString(requestMap);

            restTemplate.put(BASE_URL+"/profile", request, Object.class);
            memberRepository.updateMember(member);
        }catch (DataIntegrityViolationException | JsonProcessingException ex){
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



    @Override
    public ResponseEntity<Object> memberSearchByName(String username) {

        List<Member> members = null;

        try{
            members = memberRepository.findMemberByUsernameIgnoreCaseContaining(username);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("회원 조회에 실패했습니다.", ex);
        }

        responseMessage = new ResponseMessage(HttpStatus.OK.value(), "회원조회에 성공했습니다.", null);
        responseMessage.setData(members, "member");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
