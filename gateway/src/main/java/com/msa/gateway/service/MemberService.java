package com.msa.gateway.service;


import com.msa.gateway.domain.Account;
import org.springframework.http.ResponseEntity;


public interface MemberService {

    ResponseEntity<Object> memberSignup(Account account);

}
