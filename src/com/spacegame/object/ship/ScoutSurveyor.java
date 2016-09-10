package com.spacegame.object.ship;

public class ScoutSurveyor extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public ScoutSurveyor(String name) {
		super(300, 45000, 20000, 20000, Shields.HEAVY, Shields.HEAVY, 350, 0, 300, name, true, Type.EXPLORER,
				Speed.XFAST, null);

	}

}
