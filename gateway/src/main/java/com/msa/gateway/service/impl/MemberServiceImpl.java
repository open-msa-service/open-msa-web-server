package com.msa.gateway.service.impl;


import com.msa.gateway.domain.Account;
import com.msa.gateway.repository.AccountRepository;
import com.msa.gateway.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberServiceImpl implements MemberService {

    private static final String BASE_URL = "http://localhost:8082/user";

    @Autowired
    private AccountRepository accountRepository;

    private RestTemplate restTemplate;

    @Autowired
    MemberServiceImpl(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Override
    public ResponseEntity<Object> memberSignup(Account account) {
        try{
            accountRepository.save(account);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("", ex);
        }
        return restTemplate.postForEntity(BASE_URL+"/signup", account, Object.class);
    }
}
