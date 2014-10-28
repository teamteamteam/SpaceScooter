package de.teamteamteam.spacescooter.entity;

public abstract class CollidableEntity extends Entity implements Collidable{

	public CollidableEntity(int x, int y) {
		super(x, y);
	}

	/**
	 * Dimensions of the Entity
	 */
	protected int width;
	protected int height;

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.width;
	}

}
