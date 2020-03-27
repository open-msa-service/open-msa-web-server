package msa.gateway.msagateway.security;

import lombok.extern.slf4j.Slf4j;
import msa.gateway.msagateway.domain.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId);
        if(account == null){
            throw new UsernameNotFoundException("로그인에 실패 했습니다.");
        }
        log.info("UserDetailsService :::: {}", account.toString());
        account.setUsername(account.getUserId());
        return account;
    }

}