package msa.gateway.msagateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MsaGatewayApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        String password = "test";
        System.out.println(passwordEncoder.encode(password));
    }

}
