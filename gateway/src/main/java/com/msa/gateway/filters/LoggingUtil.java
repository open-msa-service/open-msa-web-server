package com.msa.gateway.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoggingUtil {

    public static Map<String, Object> makeLoggingRequestMap(final HttpServletRequest request){
        // request info
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("url", request.getRequestURL().toString());
        requestMap.put("queryString", request.getQueryString());
        requestMap.put("method", request.getMethod());
        requestMap.put("remoteAddr", request.getRemoteAddr());
        requestMap.put("remoteHost", request.getRemoteHost());
        requestMap.put("remoteUser", request.getRemoteUser());
        requestMap.put("encoding", request.getCharacterEncoding());

        // request header
        Map<String, Object> requestHeaderMap = new HashMap<>();
        Enumeration<String> requestHeaderNameList = request.getHeaderNames();
        while(requestHeaderNameList.hasMoreElements()){
            String headerName = requestHeaderNameList.nextElement();
            requestHeaderMap.put(headerName, request.getHeader(headerName));
        }
        requestMap.put("header", requestHeaderMap);

        // request body
        try{
            Object requestBody = ((RequestWrapper)request).convertToObject();
            requestMap.put("body", requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestMap;
    }


    public static Map<String, Object> makeLoggingResponseMap(final HttpServletResponse response){
        // response info
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("status", response.getStatus());

        // response header
        Map<String, Object> responseHeaderMap = new HashMap<>();
        Collection<String> responseHeaderNameList = response.getHeaderNames();
        responseHeaderNameList.forEach(v -> responseHeaderMap.put(v, response.getHeader(v)));
        responseMap.put("header", responseHeaderMap);

        // response Body
        try{
            Object responseBody = ((ResponseWrapper)response).convertToObject();
            responseMap.put("body", responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseMap;
    }

}
