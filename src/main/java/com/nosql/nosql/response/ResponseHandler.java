package com.nosql.nosql.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("timestamp", new Date());
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        return new ResponseEntity<>(map, status);
    }
}
