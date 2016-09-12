package com.spacegame.object;

import com.spacegame.game.Player;

public class Ship {

	public final static int SHIELDS_NONE = 0;
	public final static int SHIELDS_LIGHT = 100;
	public final static int SHIELDS_MEDIUM = 400;
	public final static int SHIELDS_HEAVY = 750;
	public final static int SHIELDS_COMBAT = 1500;

	public final static double SPEED_XSLOW = .10;
	public final static double SPEED_SLOW = .25;
	public final static double SPEED_MEDIUM = .33;
	public final static double SPEED_FAST = .5;
	public final static double SPEED_XFAST = 1;

	private int cargoHolds;
	private int shipCost;
	private int heath;
	private int maxHealth;
	private int shields;
	private int maxShields;
	private int power;
	private int droneCount;
	private int maxDroneCount;
	private double speed;
	private String name;
	private Player owner;
	boolean hasWarp;
	private final Class shipClass;
	private final Type type;
	private String modelName;

	private Ship(String name, Player owner, Type type) {

		this.name = name;
		this.owner = owner;
		this.type = type;
		this.cargoHolds = type.cargoHolds;
		this.shipCost = type.shipCost;
		this.heath = type.heath;
		this.maxHealth = type.maxHealth;
		this.shields = type.shields;
		this.maxShields = type.maxShields;
		this.power = type.power;
		this.droneCount = type.droneCount;
		this.maxDroneCount = type.maxDroneCount;
		this.speed = type.speed;
		this.hasWarp = type.hasWarp;
		this.shipClass = type.shipClass;
		this.modelName = type.modelName;
	}

	public enum Class {
		ESCAPE_POD, TRADER, FIGHTER, EXPLORER
	}

	public enum Type {

		// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|hasWarp|shipClass|speed
		MERCHANT_ONE(25, 1000, 10000, 10000,SHIELDS_LIGHT, SHIELDS_LIGHT, 200, 0, 200, SPEED_MEDIUM, false, Class.TRADER, "Bob"),
		MERCHANT_TWO(100, 10000, 20000, 20000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_SLOW, false, Class.TRADER, "Bob"),
		MERCHANT_THREE(75, 50000, 30000, 30000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_MEDIUM, false, Class.TRADER, "Bob"),

		SCOUT_ONE(800, 45000, 15000, 15000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_XFAST, false, Class.EXPLORER, "Bob"),
		SCOUT_TWO(800, 45000, 15000, 15000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_XFAST, false, Class.EXPLORER, "Bob"),
		SCOUT_THREE(800, 45000, 15000, 15000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_FAST, false, Class.EXPLORER, "Bob"),

		WARRIOR_ONE(800, 45000, 15000, 15000,SHIELDS_HEAVY, SHIELDS_HEAVY, 200, 0, 200, SPEED_MEDIUM, false, Class.FIGHTER, "Bob"),
		WARRIOR_TWO(800, 45000, 15000, 30000,SHIELDS_COMBAT, SHIELDS_COMBAT, 200, 0, 200, SPEED_MEDIUM, false, Class.FIGHTER, "Bob"),
		WARRIOR_THREE(800, 45000, 15000, 15000,SHIELDS_COMBAT, SHIELDS_COMBAT, 200, 0, 200, SPEED_SLOW, false, Class.FIGHTER, "Bob"),

		ESCAPE_POD(5, 0, 1000, 1000,SHIELDS_NONE, SHIELDS_NONE, 200, 0, 200, SPEED_XSLOW, false, Class.ESCAPE_POD, "Escape Pod");

		public final int cargoHolds;
		public final int shipCost;
		public final int heath;
		public final int maxHealth;
		public final int shields;
		public final int maxShields;
		public final int power;
		public final int droneCount;
		public final int maxDroneCount;
		public final double speed;
		public final boolean hasWarp;
		public final Class shipClass;
		public final String modelName;

		// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|shipClass|speed|owner
		// super(800, 45000, 15000, 15000, SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, name, false, Type.TRADER,
		//MERCHANT_BARGE(800, 45000, 15000, 15000,SHIELDS_MEDIUM, SHIELDS_MEDIUM, 200, 0, 200, SPEED_SLOW, false, com.spacegame.object.ship.Class.TRADER)


		Type(int cargoHolds, int shipCost, int heath, int maxHealth, int shields, int maxShields, int power, int droneCount, int maxDroneCount, double speed, boolean hasWarp, Class shipClass, String modelName){
			this.cargoHolds = cargoHolds;
			this.shipCost = shipCost;
			this.heath = heath;
			this.maxHealth = maxHealth;
			this.shields = shields;
			this.maxShields = maxShields;
			this.power = power;
			this.droneCount = droneCount;
			this.maxDroneCount = maxDroneCount;
			this.speed = speed;
			this.hasWarp = hasWarp;
			this.shipClass = shipClass;
			this.modelName = modelName;
		}

	}



	public void render() {
		System.out.println("somthing");
	}

	public int getCargoHolds() {
		return cargoHolds;
	}

	public void setCargoHolds(int cargoHolds) {
		this.cargoHolds = cargoHolds;
	}

	public int getShipCost() {
		return shipCost;
	}

	public void setShipCost(int shipCost) {
		this.shipCost = shipCost;
	}

	public int getHeath() {
		return heath;
	}

	public void setHeath(int heath) {
		this.heath = heath;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getShields() {
		return shields;
	}

	public void setShields(int shields) {
		this.shields = shields;
	}

	public int getMaxShields() {
		return maxShields;
	}

	public void setMaxShields(int maxShields) {
		this.maxShields = maxShields;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getDroneCount() {
		return droneCount;
	}

	public void setDroneCount(int droneCount) {
		this.droneCount = droneCount;
	}

	public int getMaxDroneCount() {
		return maxDroneCount;
	}

	public void setMaxDroneCount(int maxDroneCount) {
		this.maxDroneCount = maxDroneCount;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public boolean isHasWarp() {
		return hasWarp;
	}

	public void setHasWarp(boolean hasWarp) {
		this.hasWarp = hasWarp;
	}

	public Class getShipClass() {
		return shipClass;
	}



}
