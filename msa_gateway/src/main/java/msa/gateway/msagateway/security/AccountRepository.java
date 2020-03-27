package msa.gateway.msagateway.security;

import msa.gateway.msagateway.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUserId(String userId);

}
