package com.spacegame.object.ship;

public class ScoutWisp extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public ScoutWisp(String name) {
		super(100, 14000, 12500, 12500, Shields.LIGHT, Shields.LIGHT, 200, 0, 150, name, true, Type.EXPLORER,
				Speed.XFAST, null);

	}

}
