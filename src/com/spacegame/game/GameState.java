package com.spacegame.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by jparrott on 9/11/2016.
 */
public class GameState {

    private final static Logger log = LogManager.getLogger(GameState.class);

    private GameState(){

    }

    private static GameState instance = null;

    public static GameState getInstance() {
        if(instance == null){
            log.warn("GameState is being accessed and has not been initialized.  GameState == " + instance);
        }

        return instance;
    }

    public static GameState initGameState() throws IllegalAccessException{
        if(instance != null){
            IllegalAccessException ex = new IllegalAccessException("GameState has already been initialized and cannot be reinitialized");
            log.error(ex);
            throw ex;
        }


        instance = new GameState();


        return instance;
    }

    public void registerPlayer(Player player){
        //TODO: perform all the initialization for a new player
    }

    public void movePlayer(String playerName){

    }

    //TODO: add convenience methods for gettings stuff from world
}
