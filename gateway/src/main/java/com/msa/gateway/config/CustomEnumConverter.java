package com.msa.gateway.config;

import com.msa.gateway.domain.UserRole;
import org.springframework.core.convert.converter.Converter;

public class CustomEnumConverter implements Converter<String, UserRole> {
    @Override
    public UserRole convert(String s) {
        try{
            return UserRole.valueOf(s);
        }catch (Exception e){
            return null;
        }
    }
}
