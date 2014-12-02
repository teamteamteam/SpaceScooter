package de.teamteamteam.spacescooter.entity.obstacle;

import de.teamteamteam.spacescooter.entity.CollidableEntity;

/**
 * Obstacles are static or floating things that do damage on collision.
 */
public abstract class Obstacle extends CollidableEntity {

	public Obstacle(int x, int y) {
		super(x, y);
	}

}
