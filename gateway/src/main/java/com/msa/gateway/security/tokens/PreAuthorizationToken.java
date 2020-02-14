package com.msa.gateway.security.tokens;

import com.msa.gateway.dtos.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String username, String password){
        super(username, password);
    }

    public PreAuthorizationToken(FormLoginDto dto){
        this(dto.getId(), dto.getPassword());
    }

    public String getUsername(){
        return (String)super.getPrincipal();
    }

    public String getUserPassword(){
        return (String)super.getCredentials();
    }
}
