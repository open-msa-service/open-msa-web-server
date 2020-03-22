package msa.gateway.msagateway.security.inter.impl;

import lombok.extern.slf4j.Slf4j;
import msa.gateway.msagateway.domain.Account;
import msa.gateway.msagateway.security.inter.GateWayUserRepository;
import msa.gateway.msagateway.security.inter.GateWayUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class GateWayUserServiceImpl implements GateWayUserService {

    @Autowired
    private GateWayUserRepository gateWayUserRepository;

    @Override
    public Account findByUserId(String userId) {
        log.info("GateWayUserServiceImpl.findByUserId :::: {}", userId);
        return gateWayUserRepository.findByUsername(userId);
    }

    @Override
    public Account save(Account account) {
        log.info("GateWayUserServiceImpl.save :::: {}", account.toString());
        return gateWayUserRepository.save(account);
    }
}
