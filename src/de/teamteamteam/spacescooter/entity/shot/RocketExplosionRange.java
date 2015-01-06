package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class RocketExplosionRange extends Shot{

	int lifetime = 0;
	
	public RocketExplosionRange(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y, shootDirection, shootSpeed, damageValue, filename);
	}

	/**
	 * Lifetime of the rocket explosion range.
	 */
	public void update() {
		lifetime++;
		if(lifetime>5){
			this.remove();
		}
	}
	
	@Override
	public void collideWith(Collidable entity) {
	}
}
