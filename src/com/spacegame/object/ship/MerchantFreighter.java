package com.spacegame.object.ship;

public class MerchantFreighter extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public MerchantFreighter(String name) {
		super(150, 5000, 10000, 10000, Shields.LIGHT, Shields.LIGHT, 100, 0, 200, name, false, Type.TRADER,
				Speed.MEDIUM, null);
	}

}
