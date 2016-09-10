package com.spacegame.object;

import com.spacegame.game.Renderable;

/**
 * Created by jparrott on 9/3/2016.
 */
public class Sector implements Renderable {

    private Planet planet;
    private TradePort tradePort;
    private int level;
    private final int x;
    private final int y;


    public Sector(int x, int y, Planet planet, TradePort tradePort, int level) {
        this.x = x;
        this.y = y;
        this.planet = planet;
        this.tradePort = tradePort;
        this.level = level;
    }

    public String getName(){
        String x = String.valueOf((char)(this.x + 65));
        String y = String.valueOf(this.y + 1);
        return x + y;
    }

    public void render(){
        System.out.println("you have entered sector " + getName());

        if(planet != null){
            System.out.println("there is a planet here - " + planet.getName());
            planet.render();
        }
        if(tradePort != null){
            System.out.println("there is a trade port here");
            tradePort.render();
        }
    }

    //randomly generate a sector with a planet and a tradeport
    public static Sector generate(int x, int y){
        return new Sector(x, y, null, null, 0);
    }

    public static void main(String[] args){
        Sector sector = new Sector(0,0,new Planet(), new TradePort(), 0);
        System.out.println(sector.getName());
    }
}






