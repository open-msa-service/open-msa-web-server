package msa.gateway.msagateway.controller;


import msa.gateway.msagateway.domain.Account;
import msa.gateway.msagateway.domain.ResponseMessage;
import msa.gateway.msagateway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountService accountService;
    private ResponseMessage responseMessage;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    ResponseEntity<ResponseMessage> memberRegister(@RequestBody Account account){
        accountService.memberRegister(account);
        responseMessage = new ResponseMessage("", "회원가입을 완료했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


}
