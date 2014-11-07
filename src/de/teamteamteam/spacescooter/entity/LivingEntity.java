package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;
import java.util.List;

import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.explosion.Explosion;
import de.teamteamteam.spacescooter.entity.item.Items;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.screen.Screen;
import de.teamteamteam.spacescooter.utility.GameConfig;

/**
 * A LivingEntity is an Entity that is able to take damage.
 * It can collide with other Collidable Entities.
 * It knows about its collision box, which is based on width and height.
 * Also, it contains the generic logic about health points and shield points and
 * takes care of damage calculations.
 */
public abstract class LivingEntity extends Entity implements Collidable {

	/**
	 * Damage other LivingEntities take when colliding with this.
	 */
	private int collisionDamage;

	/**
	 * The LivingEntities health points.
	 */
	private int healthPoints;

	/**
	 * The LivingEntities shield points.
	 */
	private int shieldPoints;
	
	
	/**
	 * Default constructor.
	 */
	public LivingEntity(int x, int y) {
		super(x, y);
	}

	
	/**
	 * Create a Rectangle containing all information to check for 
	 * intersection with other Rectangles.
	 */
	public Rectangle getCollisionBox() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(),
				this.getHeight());
	}

	/**
	 * Update logic containing basic collision detection and collision handling.
	 */
	public void update() {
		if(!(this instanceof ShootingEntity)) return; //Only check collisions for ShootingEntity.
		List<Entity> entities = Screen.currentScreen.getEntities();
		for (Entity e : entities) {
			if (e.equals(this)) //Do not collide with myself!
				continue;
			if(!(e instanceof Collidable)) //Do not collide with non-collidable!
				continue;
			Collidable ce = (Collidable) e;
			if (ce.getCollisionBox().intersects(this.getCollisionBox())) {
				this.collideWith(ce);
			}
		}
	}

	/**
	 * Handle collisions based on what the LivingEntity collided with.
	 * Triggers damage calculations for both LivingEntities involved.
	 */
	public void collideWith(Collidable entity) {
		//this instanceof ShootingEntity
		if(entity instanceof Shot) {
			Shot s = (Shot) entity;
			this.takeDamage(s.getDamageValue());
			s.takeDamage(this.getCollisionDamage());
		}
		if(entity instanceof Player && (!(this instanceof Player))) {
			Player player = (Player) entity;
			player.takeDamage(this.getCollisionDamage());
			this.takeDamage(player.getCollisionDamage());
		}
		if(entity instanceof Enemy && (!(this instanceof Enemy))) {
			Enemy enemy = (Enemy) entity;
			enemy.takeDamage(this.getCollisionDamage());
			this.takeDamage(enemy.getCollisionDamage());
		}
		if(this instanceof Player && entity instanceof Items){
			Items item = (Items) entity;
			item.setHealthPoints(0);
			item.remove();
		}
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
		return this.collisionDamage;
	}

	/**
	 * Determine whether the LivingEntity is still alive.
	 */
	public boolean isAlive() {
		return healthPoints > 0;
	}

	/**
	 * Process incoming damage by calculating remaining health points
	 * and shield points.
	 * Also check for the need of triggering an explosion if dead.
	 */
	public void takeDamage(int damage) {
		if(this instanceof Shot) {
			if(GameConfig.DEBUG) System.out.println("Shot took damage: " + damage + "left: "+this.getHealthPoints()+" (" + this + ")");
		}
		// TODO: shield and health logic
		this.healthPoints -= damage;
		if (this.isAlive() == false) {
			if(GameConfig.DEBUG) System.out.println(this + " ist gestorben. RIP");
			this.explode();
			this.remove();
		}
	}

	/**
	 * The default way the LivingEntity explodes.
	 * Override this method for a different explosion behaviour.
	 */
	public void explode() {
		new Explosion(this.getX(), this.getY());
	}

	/**
	 * Set the current health points.
	 */
	public void setHealthPoints(int hp) {
		this.healthPoints = hp;
	}

	/**
	 * Get the current health points.
	 */
	public int getHealthPoints() {
		return this.healthPoints;
	}

	/**
	 * Set the current shield points.
	 */
	public void setShieldPoints(int sp) {
		this.shieldPoints = sp;
	}

	/**
	 * Get the current shield points.
	 */
	public int getShieldPoints() {
		return this.shieldPoints;
	}

}
