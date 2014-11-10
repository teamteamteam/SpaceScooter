package de.teamteamteam.spacescooter.entity.spi;

/**
 * Interface providing everything needed for collision handling.
 */
public interface Collidable {
	
	/**
	 * Get X-Position of the Collidable.
	 */
	public int getX();

	/**
	 * Get Y-Position of the Collidable.
	 */
	public int getY();
	
	/**
	 * Get the width of the Collidable.
	 */
	public int getWidth();

	/**
	 * Get the height of the Collidable.
	 */
	public int getHeight();
	
	/**
	 * Notify the Collidable that a collision happened.
	 */
	public void collideWith(Collidable entity);
}