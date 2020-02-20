package com.msa.gateway.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.gateway.dtos.TokenDto;
import com.msa.gateway.security.AccountContext;
import com.msa.gateway.security.jwt.JwtFactory;
import com.msa.gateway.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String HEADER_STRING = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    private JwtFactory jwtFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        AccountContext context = (AccountContext)token.getPrincipal();

        String tokenString = jwtFactory.generateToken(context);

        processResponse(httpServletResponse, writeDto(tokenString)); // 만들어진 token을 header에 넣어준다.
    }

    private TokenDto writeDto(String token){
        return new TokenDto(token);
    }

    private void processResponse(HttpServletResponse response, TokenDto dto) throws JsonProcessingException, IOException{
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + dto.getToken());
    }


}
