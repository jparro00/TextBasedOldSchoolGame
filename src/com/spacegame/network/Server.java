package com.spacegame.network;

import com.spacegame.game.Player;
import netgame.common.Hub;
import netgame.common.DisconnectMessage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jparrott on 9/3/2016.
 */
public class Server extends Hub{


    private final static int PORT = 37829;
    private final static String DIRECTORY_SAVE = "./sav/";
    private final static Logger log = LogManager.getLogger(Server.class);

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
        log.debug("messageReceived()");
        log.debug("playerID: " + playerID);
        log.debug("message: " + message);

        if(message instanceof Request){

            Request request = (Request)message;
            String command = request.getCommand();

            RenderRequest renderRequest = new RenderRequest("Recieved message: " + command);
            if(RenderRequest.HEADER_CLEAR_REQUEST.equals(command)){
                renderRequest.setClearRequest(true);
            }
            sendToOne(playerID, renderRequest);

        }
    }

    @Override
    protected void playerConnected(int playerID) {

    }

    @Override
    protected void playerDisconnected(int playerID) {
        log.info("Enter playerDisconnected()");
        log.debug("param playerID: " + playerID);

        RenderRequest renderRequest = new RenderRequest("player " + playerIdMap.get(playerID) + " has left the game");
        sendToAll(renderRequest);
        Player player = playerIdMap.remove(playerID);

        //Save player object for the next time the player joins
        try(
                FileOutputStream fout = new FileOutputStream(".\\sav\\" + player.getName() + ".ser");
                ObjectOutputStream oos = new ObjectOutputStream(fout);
        ){
            oos.writeObject(player);
        }catch (IOException io){
            log.error(io);
            io.printStackTrace();
        }
        log.info("Enter playerDisconnected()");
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
        class Local {};
        String methodName = Local.class.getEnclosingMethod().getName();
        log.info("enter " + methodName + "()");

        try{
            String playerName = (String)in.readObject();
            log.debug("playerName: " + playerName);

            log.debug("playerIdMap: " + playerIdMap);
            log.debug("player exists: " + playerIdMap.containsValue(playerID));
            boolean playerAlreadyRegistered = false;
            for(Player player : playerIdMap.values()){
                if(playerName.equals(player.getName())){
                    playerAlreadyRegistered = true;
                    break;
                }
            }
            if(playerAlreadyRegistered){
                log.debug("disconnecting client");
                DisconnectMessage disconnectMessage = new DisconnectMessage("Username " + playerName + " is already in use");
                out.writeObject(disconnectMessage);
                out.flush();
            }
            else{
                initializePlayer(playerID, playerName);
                out.writeObject("Success");
                out.flush();
            }

        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        log.info("exit " + methodName + "()");
    }

    /**
     * Checks if a player save already exists on this server and initializes new Player object
     * from the save if one is found.  Otherwise returns a new Player object
     * @param playerID
     * @param playerName
     * @return
     */
    public Player initializePlayer(int playerID, String playerName){
        log.debug("enter initializePlayer()");

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
                log.debug("unable to load player save file.  Initializing new player");
                ex.printStackTrace();

                //initialize new player if there was an issue loading the file
                player = new Player(playerName);
            }
        }
        else {
            log.debug("Creating new player");
            player = new Player(playerName);
        }

        playerIdMap.put(playerID, player);
        sendToAll(new RenderRequest("player " + playerName + " has joined the game"));
        return player;
    }

}

