package com.spacegame.object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Created by Joshua on 9/11/2016.
 */
public class Battle {

    private final static Logger log = LogManager.getLogger(Battle.class);


    public static double attackMod2(double min, double max) {
        Random atkDice = new Random();
        double attackMod = min + (max - min) * atkDice.nextDouble();
        return attackMod;

    }
}
