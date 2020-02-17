package com.msa.gateway.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.msa.gateway.security.AccountContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Component
public class JwtDecoder {

    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    public AccountContext decodeJwt(String token){
        DecodedJWT decodedJWT = isValidToken(token).orElseThrow(()->new IndexOutOfBoundsException("유효한 토큰이 아닙니다."));

        String username = decodedJWT.getClaim("USERNAME").asString();
        String role = decodedJWT.getClaim("USER_ROLE").asString();

        return new AccountContext(username, "1234", role);
    }

    private Optional<DecodedJWT> isValidToken(String token){
        DecodedJWT jwt = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256("jwtkey");
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }

}
