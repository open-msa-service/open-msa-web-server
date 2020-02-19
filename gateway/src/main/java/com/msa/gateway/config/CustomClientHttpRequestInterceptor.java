package com.msa.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        logRequestDetails(httpRequest);
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }

    private void logRequestDetails(HttpRequest request){
        logger.info("Headers : {}", request.getHeaders());
        logger.info("Request Method : {}", request.getMethod());
        logger.info("Request URI : {}", request.getURI());
    }
}
