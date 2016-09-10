package com.spacegame.network;

import com.spacegame.game.Player;
import netgame.common.Hub;
import netgame.common.DisconnectMessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jparrott on 9/3/2016.
 */
public class Server extends Hub{

    private final static int PORT = 37829;
    private final static String DIRECTORY_SAVE = "./sav/";

    private Map<Integer, Player> playerIdMap;


    /**
     * Creates a Hub listening on a specified port, and starts a thread for
     * processing messages that are received from clients.
     *
     * @param port the port on which the server will listen.
     * @throws IOException if it is not possible to create a listening socket on the specified port.
     */
    public Server(int port) throws IOException {
        super(port);
        playerIdMap = new HashMap<>();
    }

    public static void main(String[] args) {
        try {
            new Server(PORT);
        }
        catch (IOException e) {
            System.out.println("Can't create listening socket.  Shutting down.");
        }
    }

    @Override
    public void messageReceived(int playerID, java.lang.Object message){
        System.out.println("messageReceived()");
        System.out.println("playerID: " + playerID);
        System.out.println("message: " + message);

        if(message instanceof Request){

            Request request = (Request)message;
            String command = request.getCommand();

            Response response = new Response("Recieved message: " + command);
            if(Response.HEADER_CLEAR_REQUEST.equals(command)){
                response.setClearRequest(true);
            }
            sendToOne(playerID, response);

        }
    }

    @Override
    protected void playerConnected(int playerID) {
    }

    @Override
    protected void playerDisconnected(int playerID) {
        Response response = new Response("player " + playerIdMap.get(playerID) + " has left the game");
        sendToAll(response);
        Player player = playerIdMap.remove(playerID);

        //Save player object for the next time the player joins
        try(
                FileOutputStream fout = new FileOutputStream(".\\sav\\" + player.getName() + ".ser");
                ObjectOutputStream oos = new ObjectOutputStream(fout);
        ){
            oos.writeObject(player);
        }catch (IOException io){
            io.printStackTrace();
        }

    }

    /**
     * Send client player information
     *
     * @param playerID the ID number of the player who is connecting.
     * @param in a stream from which messages from the client can be read.
     * @param out a stream to which message to the client can be written.  After writing
     *    a message to this stream, it is important to call out.flush() to make sure
     *    that the message is actually transmitted.
     * @throws IOException
     */
    @Override
    protected void extraHandshake(int playerID, ObjectInputStream in,
                                  ObjectOutputStream out) throws IOException {
        System.out.println("enter extraHandshake()");
        try{
            String playerName = (String)in.readObject();
            System.out.println("playerName: " + playerName);

            System.out.println("playerIdMap: " + playerIdMap);
            System.out.println("player exists: " + playerIdMap.containsValue(playerID));
            boolean playerAlreadyRegistered = false;
            for(Player player : playerIdMap.values()){
                if(playerName.equals(player.getName())){
                    playerAlreadyRegistered = true;
                    break;
                }
            }
            if(playerAlreadyRegistered){
                System.out.println("disconnecting client");
                DisconnectMessage disconnectMessage = new DisconnectMessage("Username " + playerName + " is already in use");
                out.writeObject(disconnectMessage);
                out.flush();
            }
            else{
                initializePlayer(playerID, playerName);
                out.writeObject("Success");
                out.flush();
            }

        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        System.out.println("exit extraHandshake()");
    }

    /**
     * Checks if a player save already exists on this server and initializes new Player object
     * from the save if one is found.  Otherwise returns a new Player object
     * @param playerID
     * @param playerName
     * @return
     */
    public Player initializePlayer(int playerID, String playerName){
        System.out.println("enter initializePlayer()");

        Player player;

        //check if this player has a save already
        File playerSave = new File(DIRECTORY_SAVE + playerName + ".ser");
        if(playerSave.exists()){
            try(
                    FileInputStream fIn = new FileInputStream(playerSave);
                    ObjectInputStream ois = new ObjectInputStream(fIn);
            ){
                player = (Player)ois.readObject();
            }catch (IOException | ClassCastException | ClassNotFoundException ex) {
                System.out.println("unable to load player save file.  Initializing new player");
                ex.printStackTrace();

                //initialize new player if there was an issue loading the file
                player = new Player(playerName);
            }
        }
        else {
            System.out.println("Creating new player");
            player = new Player(playerName);
        }

        playerIdMap.put(playerID, player);
        sendToAll(new Response("player " + playerName + " has joined the game"));
        return player;
    }

}

