package de.teamteamteam.spacescooter.entity.obstacle;

import de.teamteamteam.spacescooter.entity.CollidableEntity;
import de.teamteamteam.spacescooter.entity.spi.Collidable;

/**
 * Obstacles are static or floating things that do damage on collision.
 */
public abstract class Obstacle extends CollidableEntity {

	public Obstacle(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Obstacles do not care about collisions. Mostly.
	 * Override this if you think different.
	 */
	@Override
	public void collideWith(Collidable entity) {

	}
	
	/**
	 * Patch through the update() call.
	 */
	@Override
	public void update() {
		super.update();
	}

}
