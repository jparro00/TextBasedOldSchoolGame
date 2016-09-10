package com.spacegame.network;

/**
 * Created by jparrott on 9/5/2016.
 */

import com.google.gson.JsonParseException;
import com.spacegame.network.Request;

import java.io.*;
import java.net.*;

public class PlayerThread extends Thread {
    private Socket socket = null;

    public PlayerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    @Override
    public void run() {

        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        ) {
            String outputLine;
            Request request;
            String requestString;
            Object requestObject;

            //while ((requestString = in.readObject().toString()) != null) {
            while ((requestObject = in.readObject()) != null) {
                System.out.println("from client: " + requestObject);
                try{
                    //todo: do something with the request
                    //request = Request.fromJson(requestString);
                    request = (Request)requestObject;
                    //outputLine = request.toJson();
                    //System.out.println("from server: " + outputLine);
                    //out.println(outputLine);
                }catch(JsonParseException jp){
                    out.println("not a requeste object");
                    jp.printStackTrace();
                }
                //todo: add handling for ending connection
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cnf){
            cnf.printStackTrace();
        }
    }
}

