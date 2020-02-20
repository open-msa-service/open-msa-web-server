package com.msa.gateway.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.msa.gateway.security.AccountContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);
    private static final int EXPIRATION_TIME = 864_000_000; // 10days
    private static String singingKey = "jwtkey";

    public String generateToken(AccountContext context){
        String token = null;

        try{
            token = JWT.create()
                    .withIssuer("jongmin")
                    .withClaim("USERNAME", context.getAccount().getUserId())
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(generateAlgorithm());
        }catch (Exception e){
            log.error(e.getMessage());
        }

       return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(singingKey);
    }
}
