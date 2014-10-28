package de.teamteamteam.spacescooter.entity;

public abstract class Entity implements Updateable, Paintable {
	
	/**
	 * Entity position
	 */
	protected int x;
	protected int y;
	
	/**
	 * Constructor.
	 * All entities are within a static array list for our convenience.
	 */
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	
}
