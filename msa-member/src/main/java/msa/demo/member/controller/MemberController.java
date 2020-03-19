package msa.demo.member.controller;


import msa.demo.member.domain.Member;
import msa.demo.member.domain.ResponseMessage;
import msa.demo.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MemberController {

    private ResponseMessage responseMessage;

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{userId}")
    ResponseEntity<ResponseMessage> getMemberInfoByUsername(@PathVariable String userId){
        Member member = memberService.findMemberInfoByUserId(userId);
        responseMessage = new ResponseMessage(member, "회원정보를 가져오는데 성공했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    ResponseEntity<ResponseMessage> findUserByUsername(@PathVariable String username){
        List<Member> members = memberService.findAllMemberByUsername(username);
        responseMessage = new ResponseMessage(members, "회원정보를 가져오는데 성공했습니다.");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseMessage> updateUserInfo(@RequestPart("file") MultipartFile[] file, @RequestPart("member")String members){
        memberService.updateUserInfo(file, members);
        responseMessage = new ResponseMessage("", "회원정보 수정에 성공했습니다.");
        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }


}
