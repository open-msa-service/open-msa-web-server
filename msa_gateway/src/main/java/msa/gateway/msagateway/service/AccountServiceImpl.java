package msa.gateway.msagateway.service;


import lombok.extern.slf4j.Slf4j;
import msa.gateway.msagateway.domain.Account;
import msa.gateway.msagateway.domain.UserRole;
import msa.gateway.msagateway.event.EventDispatcher;
import msa.gateway.msagateway.event.GatewaySolvedEvent;
import msa.gateway.msagateway.security.AccountRepository;
import msa.gateway.msagateway.security.SecurityConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final EventDispatcher eventDispatcher;

    @Autowired
    private SecurityConfig securityConfig;

    public AccountServiceImpl(AccountRepository accountRepository, EventDispatcher eventDispatcher) {
        this.accountRepository = accountRepository;
        this.eventDispatcher = eventDispatcher;
    }

    /**
     * 회 원 가 입
     * @param account
     */
    @Transactional
    @Override
    public void memberRegister(Account account) {
        // 이벤트를 발생시켜 member table에 값을 전송한다.
        try {
            String password = securityConfig.passwordEncoding(account.getPassword());
            account.setPassword(password);
            account.setRole(UserRole.ROLE_USER);
            log.info("AccountService memberRegister :::: Send Member Event");
            eventDispatcher.send(new GatewaySolvedEvent(new ObjectMapper().writeValueAsString(account)));
        } catch (Exception e) {
            log.info("AccountService :::: eventDispatcher 에러 발생");
            try {
                throw new IOException("회원가입에 실패했습니다.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Account 객체 저장
        accountRepository.save(account);
    }
}
