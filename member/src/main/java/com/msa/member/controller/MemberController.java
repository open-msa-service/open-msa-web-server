package com.msa.member.controller;


import com.msa.member.domain.Member;
import com.msa.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

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

}
