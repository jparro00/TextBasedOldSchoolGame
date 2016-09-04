package com.spacegame.ships;

import com.spacegame.player.player;

public class ScoutEchoX extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public ScoutEchoX(String name) {
		super(400, 140000, 30000, 30000, Shields.HEAVY, Shields.HEAVY, 600, 0, 600, name, true, Type.EXPLORER,
				Speed.FAST, null);

	}

}
