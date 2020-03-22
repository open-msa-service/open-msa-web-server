package msa.gateway.msagateway.security.inter;

import msa.gateway.msagateway.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateWayUserRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);
}