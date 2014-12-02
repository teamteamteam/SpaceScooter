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
	 * Whether or not this CollidableEntity can collide.
	 */
	private boolean canCollide;

	/**
	 * Whether this CollidableEntity will damage things it collides with.
	 */
	private boolean damaging;
	
	
	/**
	 * Default constructor. Initializes sane defaults.
	 */
	public CollidableEntity(int x, int y) {
		super(x, y);
		this.setCollide(true);
		this.setDamaging(true);
	}
	
	
	/**
	 * Handle collisions based on what the LivingEntity collided with.
	 * Triggers damage calculations for itself only.
	 * Override to add specific behaviour, e.g. a Player picking up Items.
	 */
	public abstract void collideWith(Collidable entity);

	/**
	 * Tell whether the Collidable is currently
	 * an active Collidable.
	 */
	public boolean canCollide() {
		return this.canCollide;
	}
	
	/**
	 * Set whether the Collidable is currently active.
	 */
	public void setCollide(boolean canCollide) {
		this.canCollide = canCollide;
	}

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
		if(!this.getDamaging()) return 0;
		return this.collisionDamage;
	}
	
	/**
	 * Set whether the Collidable will damage things it hits.
	 * This defaults to true on construction and will only be changed manually.
	 */
	public void setDamaging(boolean damaging) {
		this.damaging = damaging;
	}
	
	/**
	 * Tell whether the Collidable will damage things it hits.
	 * This defaults to true on construction and will only be changed manually.
	 */
	public boolean getDamaging(){
		return this.damaging;
	}
	
}
