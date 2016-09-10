package com.spacegame.game;

import com.spacegame.object.World;

import java.util.Set;

/**
 * Created by jparrott on 9/3/2016.
 */
public class GameEngine {

    private World world;
    private Set<Player> players;

    public GameEngine(){

    }

    public void init(){
        this.world = World.generate(9,9);

    }

}







