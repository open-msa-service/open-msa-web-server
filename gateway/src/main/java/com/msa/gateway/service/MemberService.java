package com.msa.gateway.service;


import com.msa.gateway.domain.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    ResponseEntity<Object> memberSignUp(Account account);

}
