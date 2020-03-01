package com.msa.member.controller;


import com.msa.member.domain.Member;
import com.msa.member.service.FileUploadDownloadService;
import com.msa.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class MemberController {

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private FileUploadDownloadService fileUploadDownloadService;

    @GetMapping("/info/id/{id}")
    ResponseEntity<Object> memberFindById(@PathVariable Long id){
        return memberService.memberSearchById(id);
    }

    @GetMapping("/info/userId/{userId}")
    ResponseEntity<Object> memberFindByUserId(@PathVariable String userId){
        return memberService.memberSearchByUserId(userId);
    }

    @PostMapping(value = "/signup")
    ResponseEntity<Object> memberSignup(@RequestBody Member member){
        return memberService.memberSignUp(member);
    }

    @PutMapping(value = "/profile")
    ResponseEntity<Object> memberUpdateProfile(@RequestPart("file") MultipartFile[] file, @RequestPart("member")String members){
        return memberService.memberUpdateProfile(file, members);
    }

}
