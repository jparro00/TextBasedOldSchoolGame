package com.spacegame.object.ship;

public class EscapePod extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public EscapePod(String name) {
		super(5, 0, 500, 500, Shields.NONE, Shields.NONE, 0, 0, 0, name, false, Type.ESCAPE_POD, Speed.XSLOW, null);

	}

}
