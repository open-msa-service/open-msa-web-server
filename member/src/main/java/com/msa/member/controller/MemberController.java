package com.msa.member.controller;


import com.msa.member.domain.Member;
import com.msa.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/info/{id}")
    public Member memberFindById(@PathVariable Long id){
        return memberService.memberSearchById(id);
    }


}
