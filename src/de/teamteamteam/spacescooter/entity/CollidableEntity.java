package de.teamteamteam.spacescooter.entity;

import de.teamteamteam.spacescooter.entity.spi.Collidable;

/**
 * The CollidableEntity provides pure methods to handle collisions.
 */
public abstract class CollidableEntity extends Entity implements Collidable {

	/**
	 * Damage other LivingEntities take when colliding with this.
	 */
	private int collisionDamage;
	
	
	/**
	 * Default constructor.
	 */
	public CollidableEntity(int x, int y) {
		super(x, y);
	}
	
	
	/**
	 * Handle collisions based on what the LivingEntity collided with.
	 * Triggers damage calculations for itself only.
	 * Override to add specific behaviour, e.g. a Player picking up Items.
	 */
	public abstract void collideWith(Collidable entity);


	/**
	 * Set the collision damage of the Entity.
	 */
	public void setCollisionDamage(int d) {
		this.collisionDamage = d;
	}

	/**
	 * Get the current collision damage of the Entity.
	 */
	public int getCollisionDamage() {
		return this.collisionDamage;
	}
	
}
