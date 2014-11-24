package de.teamteamteam.spacescooter.entity.shot;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

public class Rocket extends Shot{

	public Rocket(int x, int y, int shootDirection, int shootSpeed, int damageValue, String filename) {
		super(x, y, shootDirection, shootSpeed, damageValue, filename);
		setImage("images/shots/rocket.png");
	}
	
	/**
	 * If the rocket collide with an enemy, the rocket create a big damage range
	 */
	@Override
	public void collideWith(Collidable entity) {
		new RocketExplosionRange(this.getX(), this.getY(), this.getDirection(), this.getSpeed(), this.getDamageValue(), "images/shots/rocket_explosion.png");
		super.collideWith(entity);
	}
}
