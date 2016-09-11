package com.spacegame.network;

import com.spacegame.object.Alert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jparrott on 9/5/2016.
 */
public class RenderRequest implements Serializable{

    public static final String HEADER_CLEAR_REQUEST = "clear-terminal";

    private Map<String, String> headers;
    private String body;
    private List<Alert> alerts;

    public RenderRequest(Map<String, String> headers, String body, List<Alert> alerts) {
        this.headers = headers;
        this.body = body;
        this.alerts = alerts;
    }

    public RenderRequest(String body) {
        this.body = body;
        this.headers = new HashMap<>();
        this.alerts = new ArrayList<>();

    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public void addAlert(Alert alert){
        this.alerts.add(alert);
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
