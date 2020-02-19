package com.msa.gateway.controller;

import com.msa.gateway.domain.Account;
import com.msa.gateway.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping(value = "/signup")
    ResponseEntity<Object> memberSignUp(@RequestBody Account account){
        return memberService.memberSignUp(account);
    }

}
