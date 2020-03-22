package msa.gateway.msagateway.security.inter;

import msa.gateway.msagateway.domain.Account;

public interface GateWayUserService {

    public Account findByUserId(String userId);
    public Account save(Account account);

}
