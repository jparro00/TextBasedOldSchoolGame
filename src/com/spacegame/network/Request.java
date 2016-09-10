package com.spacegame.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jparrott on 9/5/2016.
 */
public class Request implements Serializable{

    private Map<String, String> headers;
    private String command;

    public Request(String command){
        this.command = command;
        this.headers = new HashMap<>();
    }

    public Request(String command, Map<String, String> headers){
        this.command = command;
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

}
