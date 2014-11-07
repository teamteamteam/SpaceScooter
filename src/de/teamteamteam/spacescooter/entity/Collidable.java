package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;

/**
 * Interface providing everything needed for collision handling.
 */
public interface Collidable {
	
	/**
	 * Provide information about the position and dimensions of the Entity.
	 */
	public Rectangle getCollisionBox();
	
	/**
	 * Notify the Collidable that a collision happened.
	 */
	public void collideWith(Collidable entity);
}
