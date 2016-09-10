package com.spacegame.ships;

import com.spacegame.player.player;

public class MerchantBarge extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public MerchantBarge(String name) {
		super(800, 45000, 15000, 15000, Shields.MEDIUM, Shields.MEDIUM, 200, 0, 200, name, false, Type.TRADER,
				Speed.SLOW, null);

	}

}
