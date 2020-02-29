package com.msa.gateway.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RequestWrapper extends HttpServletRequestWrapper {

    private ObjectMapper objectMapper;

    private byte[] httpRequestBodyByteArray;
    private ByteArrayInputStream bis;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        this.objectMapper = new ObjectMapper();

        try{
            this.httpRequestBodyByteArray = StreamUtils.copyToByteArray(request.getInputStream());
            this.bis = new ByteArrayInputStream(httpRequestBodyByteArray);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ServletInputStream getInputStream(){
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return bis.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                return;
            }

            @Override
            public int read() throws IOException {
                return bis.read();
            }
        };
    }

    public Object convertToObject() throws IOException{
        if(httpRequestBodyByteArray.length == 0) return null; // body가 비어있을 경우 처리.
        return objectMapper.readValue(httpRequestBodyByteArray, Object.class);
    }

}
