package com.msa.gateway.service.impl;


import com.msa.gateway.domain.Account;
import com.msa.gateway.domain.UserRole;
import com.msa.gateway.repository.AccountRepository;
import com.msa.gateway.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private static final String BASE_URL = "http://localhost:8082/user";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private RestTemplate restTemplate;

    @Autowired
    MemberServiceImpl(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    @Override
    public ResponseEntity<Object> memberSignUp(Account account) {
        account.setUserRole(UserRole.USER);

        String password = account.getPassword();
        account.setPassword(passwordEncoder.encode(password));
        ResponseEntity<Object> responseEntity = null;
        // 아이디 중복체크
        if(accountRepository.existsByUserId(account.getUserId())){
            throw new DuplicateKeyException("");
        }
        try{
            accountRepository.save(account);
            responseEntity = restTemplate.postForEntity(BASE_URL+"/signup", account, Object.class);
            int status = responseEntity.getStatusCodeValue();
            if(status != 200){
                throw new DataIntegrityViolationException("");
            }
        }catch (DataIntegrityViolationException ex){ // gateway error
            throw new DataIntegrityViolationException("", ex);
        }catch (HttpClientErrorException ex){ // restTemplate error
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

}
