package com.spacegame.object;

import com.spacegame.game.Renderable;

/**
 * Created by jparrott on 9/3/2016.
 */
public class Planet implements Renderable{
    private int poulation;
    private int size;
    private int drones;
    //private Resource resources;
    private int food;
    private int fuel;
    private int equipment;

    private int numOfGuns;
    private int devLevel;
    //private int maxDockingNum;
    private String name;

    public Planet(int poulation, int size, int drones, int food, int fuel,
                   int equipment, int numOfGuns, int devLevel, String name) {
        super();
        this.poulation = poulation;
        this.size = size;
        this.drones = drones;
        this.food = food;
        this.fuel = fuel;
        this.equipment = equipment;
        this.numOfGuns = numOfGuns;
        this.devLevel = devLevel;
        this.name = name;
    }
    public void render(){
        System.out.println(" I am a description,should probably change me");
    }

    public int getPoulation() {
        return poulation;
    }

    public void setPoulation(int poulation) {
        this.poulation = poulation;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDrones() {
        return drones;
    }

    public void setDrones(int drones) {
        this.drones = drones;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public int getNumOfGuns() {
        return numOfGuns;
    }

    public void setNumOfGuns(int numOfGuns) {
        this.numOfGuns = numOfGuns;
    }

    public int getDevLevel() {
        return devLevel;
    }

    public void setDevLevel(int devLevel) {
        this.devLevel = devLevel;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }







}
