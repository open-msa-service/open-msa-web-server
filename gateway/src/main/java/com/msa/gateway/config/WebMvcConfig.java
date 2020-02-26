package com.msa.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService(){
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new CustomEnumConverter());
        return f;
    }

}
