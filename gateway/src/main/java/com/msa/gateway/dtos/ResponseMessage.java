package com.msa.gateway.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter @Setter
public class ResponseMessage {

    private int status;
    private Map<String, Object> data;
    private String message;
    private String error;
    private String time;

    public ResponseMessage(int status, String message, String error){
        this.status = status;
        this.message = message;
        this.error = error;
        this.time = String.valueOf(LocalDateTime.now());
    }

    public void setData(Object data, String dataName){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(dataName, data);
        this.data = dataMap;
    }

}
