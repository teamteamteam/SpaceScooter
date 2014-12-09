package de.teamteamteam.spacescooter.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import de.teamteamteam.spacescooter.brain.GameConfig;
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
	 * Height of the hitbox.
	 */
	private int hitboxHeight;
	
	/**
	 * Width of the hitbox.
	 */
	private int hitboxWidth;
	
	
	/**
	 * Default constructor. Initializes sane defaults.
	 */
	public CollidableEntity(int x, int y) {
		super(x, y);
		this.setCollide(true);
		this.setDamaging(true);
	}
	
	/**
	 * Get X-Position of the Collidable.
	 */
	public int getHitboxX() {
		return this.getCenteredX() - (this.hitboxWidth / 2);
	}

	/**
	 * Get Y-Position of the Collidable.
	 */
	public int getHitboxY() {
		return this.getCenteredY() - (this.hitboxHeight / 2);
	}
	
	/**
	 * Get the width of the Collidable.
	 */
	public int getHitboxWidth() {
		return this.hitboxWidth;
	}

	/**
	 * Set the width of the Collidable.
	 */
	public void setHitboxWidth(int hitboxWidth) {
		this.hitboxWidth = hitboxWidth;
	}
	
	/**
	 * Get the height of the Collidable.
	 */
	public int getHitboxHeight() {
		return this.hitboxHeight;
	}
	
	/**
	 * Set the height of the Collidable.
	 */
	public void setHitboxHeight(int hitboxHeight) {
		this.hitboxHeight = hitboxHeight;
	}
	
	/**
	 * Set the dimensions of the CollidableEntities hitbox.
	 */
	public void setHitboxDimenstions(int width, int height) {
		this.hitboxWidth = width;
		this.hitboxHeight = height;
	}
	
	/**
	 * Overriding the Entities setImageDimension to (ab)use their width/height
	 * for reuse in the hitbox.
	 */
	@Override
	public void setImageDimensions(int width, int height) {
		super.setImageDimensions(width, height);
		this.hitboxWidth = this.getImageWidth();
		this.hitboxHeight = this.getImageHeight();
	}
	
	/**
	 * Adding a debugging option to draw the hitbox in red.
	 */
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		if(GameConfig.DEBUG) {
			g.setColor(new Color(255,0,0));
			g.drawRect(this.getHitboxX(), this.getHitboxY(), this.hitboxWidth, this.hitboxHeight);
		}
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
