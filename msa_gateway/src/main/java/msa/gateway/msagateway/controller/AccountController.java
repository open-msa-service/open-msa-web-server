package msa.gateway.msagateway.controller;


import msa.gateway.msagateway.domain.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    @RequestMapping("api/users/me")
    public ResponseEntity<Account> profile(){

        // Build some dummy data to return for testing
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = user.getUsername();
        String password = user.getPassword();

        Account account = new Account();
        account.setUserId(userId);
        account.setPassword(password);

        return ResponseEntity.ok(account);
    }


}
