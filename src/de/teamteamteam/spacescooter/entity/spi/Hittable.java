package de.teamteamteam.spacescooter.entity.spi;

/**
 * Interface providing everything needed to handle taking damage.
 */
public interface Hittable {

	/**
	 * Notify the Hittable that it took damage.
	 */
	public void takeDamage(int damage);
	
	/**
	 * Tell whether the Hittable is still alive.
	 */
	public boolean isAlive();
	
	/**
	 * Get the Hittables current health points.
	 */
	public int getHealthPoints();
	
	/**
	 * Set the Hittables current health points.
	 */
	public void setHealthPoints(int healthPoints);

	/**
	 * Get the Hittables current shield points.
	 */
	public int getShieldPoints();
	
	/**
	 * Set the Hittables current shield points.
	 */
	public void setShieldPoints(int shieldPoints);
	

	
}
