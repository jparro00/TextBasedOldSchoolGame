package com.spacegame.game;

import java.io.Serializable;

/**
 * Created by jparrott on 9/3/2016.
 */
public class Player implements Serializable{

    private String name;
    private int credits;

    public Player(String name){
        this.name = name;
        this.credits = 0;
    }

    public int addCredits(int credits){

        if((long)credits + (long)this.credits > Integer.MAX_VALUE){
            this.credits = Integer.MAX_VALUE;
        }else{
            this.credits += credits;
        }
        return this.credits;
    }
    public int removeCredits(int credits){
        if((long)this.credits - (long)credits < Integer.MIN_VALUE){
            this.credits = Integer.MIN_VALUE;
        }else{
            this.credits += credits;
        }
        return this.credits;
    }
    public void setCredits(int credits){
        this.credits = credits;
    }
    public int getCredits(){
        if(credits < 0){
            credits = 0;
        }
        return credits;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

}
