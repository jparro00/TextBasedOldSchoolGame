package com.spacegame.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jparrott on 9/5/2016.
 */
public class Response implements Serializable{

    public static final String HEADER_CLEAR_REQUEST = "clear-terminal";

    private Map<String, String> headers;
    private String body;

    public Response(String body) {
        this.body = body;
        this.headers = new HashMap<>();
    }

    public Response(Map<String, String> headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public void setClearRequest(boolean clear){
        headers.put(HEADER_CLEAR_REQUEST, String.valueOf(clear));
    }
    public boolean isClearRequest(){
        String clearRequest = headers.get(HEADER_CLEAR_REQUEST);
        return ("true".equalsIgnoreCase(clearRequest));
    }

    public void putHeader(String key, String value){
        headers.put(key, value);
    }
    public String getHeader(String key){
        return headers.get(key);
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
