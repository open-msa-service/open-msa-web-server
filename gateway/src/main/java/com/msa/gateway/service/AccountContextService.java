package com.msa.gateway.service;

import com.msa.gateway.domain.Account;
import com.msa.gateway.repository.AccountRepository;
import com.msa.gateway.security.AccountContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AccountContextService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(()->new NoSuchElementException("해당하는 사용자가 존재하지 않습니다."));
        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account){
        return AccountContext.fromAccountModel(account);
    }
}
