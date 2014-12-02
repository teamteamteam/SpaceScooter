package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

/**
 * Obstacles are static or floating things that do damage on collision.
 */
public abstract class Obstacle extends CollidableEntity {

	public Obstacle(int x, int y) {
		super(x, y);
	}

	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void collideWith(Collidable entity) {
		// TODO Auto-generated method stub

	}

}
