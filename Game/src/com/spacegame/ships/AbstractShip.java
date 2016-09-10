package com.spacegame.ships;

import com.spacegame.player.player;

public abstract class AbstractShip {

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
	private player owner;
	boolean hasWarp;
	private Type type;

	public AbstractShip(int cargoHolds, int shipCost, int heath, int maxHealth, int shields, int maxShields, int power,
			int droneCount, int maxDroneCount, String name, boolean hasWarp, Type type, double speed, player owner) {

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
		this.name = name;
		this.owner = owner;
		this.hasWarp = hasWarp;
		this.type = type;
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

	public player getOwner() {
		return owner;
	}

	public void setOwner(player owner) {
		this.owner = owner;
	}

	public boolean isHasWarp() {
		return hasWarp;
	}

	public void setHasWarp(boolean hasWarp) {
		this.hasWarp = hasWarp;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
