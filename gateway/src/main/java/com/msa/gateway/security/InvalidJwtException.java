package com.msa.gateway.security;

public class InvalidJwtException extends RuntimeException{

    public InvalidJwtException(String msg){
        super(msg);
    }
}
