package com.msa.gateway.config;

import com.msa.gateway.filters.ErrorFilter;
import com.msa.gateway.filters.PostFilter;
import com.msa.gateway.filters.PreFilter;
import com.msa.gateway.filters.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfig {

    @Bean
    public PreFilter preFilter(){
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }

    @Bean
    public RouteFilter routeFilter(){
        return new RouteFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

}
