package com.spacegame.object.ship;

public class WarriorCapitalCruiser extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public WarriorCapitalCruiser(String name) {
		super(400, 150000, 40000, 40000, Shields.COMBAT, Shields.COMBAT, 900, 0, 1000, name, true, Type.FIGHTER,
				Speed.SLOW, null);

	}

}
