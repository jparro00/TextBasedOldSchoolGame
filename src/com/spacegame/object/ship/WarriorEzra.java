package com.spacegame.object.ship;

public class WarriorEzra extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public WarriorEzra(String name) {
		super(75, 15000, 15000, 15000, Shields.MEDIUM, Shields.MEDIUM, 300, 0, 300, name, false, Type.FIGHTER,
				Speed.MEDIUM, null);

	}

}
