package com.msa.gateway.controller;

import com.msa.gateway.security.tokens.PostAuthorizationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication){
        PostAuthorizationToken token = (PostAuthorizationToken)authentication;
        return token.getAccountContext().getUsername();
    }

}
