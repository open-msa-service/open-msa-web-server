package msa.member.msamember.controller;

import lombok.extern.slf4j.Slf4j;
import msa.member.msamember.domain.Member;
import msa.member.msamember.domain.ResponseMessage;
import msa.member.msamember.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    private ResponseMessage responseMessage;

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<ResponseMessage> getMemberInfoByUsername(@PathVariable String userId) {
        Member member = memberService.findMemberInfoByUserId(userId);
        responseMessage = new ResponseMessage(member, "회원정보를 가져오는데 성공했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    ResponseEntity<ResponseMessage> findUserByUsername(@PathVariable String username) {
        String userName = new String(username.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        log.info("MemberController findUserByUsername method username :::: {}", userName);
        List<Member> members = memberService.findAllMemberByUsername(userName);
        responseMessage = new ResponseMessage(members, "회원정보를 가져오는데 성공했습니다.");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseMessage> updateUserInfo(@RequestPart("file") MultipartFile[] file, @RequestPart("member") String members) {
        memberService.updateUserInfo(file, members);
        responseMessage = new ResponseMessage("", "회원정보 수정에 성공했습니다.");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }


}
