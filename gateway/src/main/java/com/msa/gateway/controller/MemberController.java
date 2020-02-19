package com.msa.gateway.controller;


import com.msa.gateway.security.tokens.PostAuthorizationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MemberController {

    @GetMapping("/signup")
    public String getUsername(Authentication authentication){

        return null;
    }
}
