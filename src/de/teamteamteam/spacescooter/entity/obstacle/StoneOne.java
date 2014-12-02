package de.teamteamteam.spacescooter.entity.obstacle;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

/**
 * First proof of concept obstacle.
 * A simple stone that moves to the left.
 */
public class StoneOne extends Obstacle {

	public StoneOne(int x, int y) {
		super(x, y);
		this.setImage("images/stones/stone01.png");
		this.setCollisionDamage(9001);
	}

	public void update() {
		this.transpose(-1, 0);
	}

	@Override
	public void collideWith(Collidable entity) {

	}

}
