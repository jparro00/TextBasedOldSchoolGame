package com.spacegame.ships;

import com.spacegame.player.player;

public class WarriorPeaceKeeper extends AbstractShip {

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner

	public WarriorPeaceKeeper(String name) {
		super(200, 50000, 25000, 25000, Shields.HEAVY, Shields.HEAVY, 500, 0, 500, name, false, Type.FIGHTER,
				Speed.MEDIUM, null);

	}

}
