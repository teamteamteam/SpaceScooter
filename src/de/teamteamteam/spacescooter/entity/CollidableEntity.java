package de.teamteamteam.spacescooter.entity;


public abstract class CollidableEntity extends Entity {

	/**
	 * Position of the Entity
	 */
	protected int x;
	protected int y;
	
	/**
	 * Dimensions of the Entity
	 */
	protected int width;
	protected int height;

    /**
     * Health Points of the Object
     */
	protected int heakthPoints;

    /**
     * Shieldpoints of the Object
     */
	protected int shieldPoints;
	
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.width;
	}

}
