package com.msa.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Map;
import static net.logstash.logback.argument.StructuredArguments.value;

public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest request = new RequestWrapper(httpServletRequest);
        final HttpServletResponse response = new ResponseWrapper(httpServletResponse);

        Map<String, Object> requestMap = LoggingUtil.makeLoggingRequestMap(request);

        filterChain.doFilter(request, response);

        Map<String, Object> responseMap = LoggingUtil.makeLoggingResponseMap(response);

        logger.info("", value("req", requestMap), value("res", responseMap));
        ((ResponseWrapper)response).copyBodyToResponse();
    }
}
