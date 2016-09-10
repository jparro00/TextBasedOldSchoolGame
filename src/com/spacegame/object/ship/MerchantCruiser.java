package com.spacegame.object.ship;

public class MerchantCruiser extends AbstractShip{

	// cargoHolds|shipCost|heath|maxHealth|shields|maxShields|power|droneCount|maxDroneCount|name|hasWarp|type|speed|owner
	
	public MerchantCruiser(String name) {
		super(600, 125000, 25000, 25000, Shields.HEAVY, Shields.HEAVY, 500, 0, 400, name, true,
				Type.TRADER, Speed.MEDIUM, null);
		
	}
	

}
