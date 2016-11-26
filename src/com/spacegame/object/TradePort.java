package com.spacegame.object;

import com.spacegame.game.Renderable;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by jparrott on 9/3/2016.
 */
public class TradePort implements Renderable{
    private int credits;
    private int food;
    private int fuel;
    private int equipment;
    private int foodPrice;
    private int fuelPrice;
    private int equipmentPrice;
    private final static int foodCapacity = 10000;
    private final static int equipmentCapacity = 10000;
    private final static int fuelCapacity = 10000;
    private final static int foodBasePrice = 100;
    private final static int fuelBasePrice = 200;
    private final static int equipBasePrice = 300;

    public TradePort() {
        Random rand = new Random();
    }

    public TradePort(int credits, int food, int fuel, int equipment, int foodPrice, int fuelPrice, int equipmentPrice) {
        this.credits = credits;
        this.food = food;
        this.fuel = fuel;
        this.equipment = equipment;
        this.foodPrice = foodPrice;
        this.fuelPrice = fuelPrice;
        this.equipmentPrice = equipmentPrice;
    }

    @Override
    public void render() {

        System.out.println("Welcome to our Tradeport, I am assuming you are here to trade.");
        System.out.println("Here is what we have and buy/sell prices for each");
        System.out.println("We have "+food);
        System.out.println("We will buy food at "+ foodPrice*.80);
        System.out.println("We will sell food at " +foodPrice*1.2);
        System.out.println("We have "+fuel);
        System.out.println("We will buy fuel at "+ fuelPrice*.80);
        System.out.println("We will sell fuel at " +fuelPrice*1.2);
        System.out.println("We have "+equipment);
        System.out.println("We will buy equipment at "+ equipmentPrice*.80);
        System.out.println("We will sell equipment at " +equipmentPrice*1.2);
    }
    private void updatePrices()
    {
        //amount of food/fuel or equipment must be changed prior to calling this method
        if(food ==0) this.foodPrice = 10*foodBasePrice;
        else this.foodPrice = (foodCapacity*foodBasePrice)/food;
        if(fuel ==0) this.fuelPrice = 10*foodBasePrice;
        else this.fuelPrice = (fuelCapacity*fuelBasePrice)/fuel;
        if(equipment ==0) this.equipmentPrice = 10*equipBasePrice;
        else this.equipmentPrice = (equipmentCapacity*equipBasePrice)/equipment;
    }
    // User is buying Food from Spaceport
    public String buyFood(int num)
    {
        if(food == 0) return "I have no food to sell";
        else if(num >food)return "I do not have enough food for that purchase";
        else
        {
            this.food = food-num;
            this.credits = (int)(credits + num*foodPrice*1.2);
            updatePrices();
            return "ok";
        }
    }
    // User is buying Fuel from Spaceport
    public String buyFuel(int num)
    {
        if(fuel == 0) return "I have no fuel to sell";
        else if(num >fuel)return "I do not have enough fuel for that purchase";
        else
        {
            this.fuel = fuel-num;
            this.credits = (int)(credits + num*fuelPrice*.1.2);
            updatePrices();
            return "ok";
        }
    }
    // User is buying Equipment from Spaceport
    public String buyEquipment(int num)
    {
        if(equipment == 0) return "I have no equipment to sell";
        else if(num >equipment)return "I do not have enough equipment for that purchase";
        else
        {
            this.equipment = equipment-num;
            this.credits = (int)(credits + num*equipmentPrice*.1.2);
            updatePrices();
            return "ok";
        }
    }
    //User is selling food to the Spaceport
    public String sellFood(int num)
    {
        if(num*foodPrice*.8>credits) return "I do not have enough credits to buy that many";
        else
        {
            this.food = food+num;
            this.credits = (int)(credits - num*foodPrice*.80);
            updatePrices();
            return "ok";
        }
    }
    //User is selling fuel to the Spaceport
    public String sellFuel(int num)
    {
        if(num*fuelPrice*.8>credits) return "I do not have enough credits to buy that many";
        else
        {
            this.fuel = fuel+num;
            this.credits = (int)(credits - num*fuelPrice*.80);
            updatePrices();
            return "ok";
        }
    }
    //User is selling equipment to the spaceport
    public String sellEquipment(int num)
    {
        if(num*equipmentPrice*.8>credits) return "I do not have enough credits to buy that many";
        else
        {
            this.equipment = equipment+num;
            this.credits = (int)(credits - num*equipmentPrice*.80);
            updatePrices();
            return "ok";
        }
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

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public int getEquipmentPrice() {
        return equipmentPrice;
    }

    public void setEquipmentPrice(int equipmentPrice) {
        this.equipmentPrice = equipmentPrice;
    }

    public int getFood() {

        return food;
    }

}
