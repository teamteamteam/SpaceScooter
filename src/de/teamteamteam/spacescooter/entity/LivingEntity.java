package de.teamteamteam.spacescooter.entity;

import java.awt.Rectangle;

import de.teamteamteam.spacescooter.datastructure.Score;
import de.teamteamteam.spacescooter.entity.enemy.Enemy;
import de.teamteamteam.spacescooter.entity.shot.Shot;
import de.teamteamteam.spacescooter.entity.spi.Collidable;
import de.teamteamteam.spacescooter.entity.spi.Hittable;
import de.teamteamteam.spacescooter.gui.Credits;
import de.teamteamteam.spacescooter.utility.GameConfig;

/**
 * A LivingEntity is an Entity that is able to take damage.
 * It can collide with other Collidable Entities.
 * It knows about its collision box, which is based on width and height.
 * Also, it contains the generic logic about health points and shield points and
 * takes care of damage calculations.
 */
public abstract class LivingEntity extends Entity implements Collidable, Hittable {

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
	 * The LivingEntities shield points.
	 */
	private int ScorePoints;
	
	
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
	 * Handle collisions based on what the LivingEntity collided with.
	 * Triggers damage calculations for itself only.
	 * Override to add specific behaviour, e.g. a Player picking up Items.
	 */
	public void collideWith(Collidable entity) {
		if(entity instanceof Shot) {
			Shot s = (Shot) entity;
			if(this instanceof Enemy && s.getDirection() == Shot.LEFT) return;
			if(this instanceof Player && s.getDirection() == Shot.RIGHT) return;
			this.takeDamage(s.getDamageValue());
		}
		if(entity instanceof Player && (!(this instanceof Player))) {
			Player player = (Player) entity;
			this.takeDamage(player.getCollisionDamage());
		}
		if(entity instanceof Enemy && (!(this instanceof Enemy))) {
			Enemy enemy = (Enemy) entity;
			this.takeDamage(enemy.getCollisionDamage());
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
		//Skip everything if already dead.
		if(this.isAlive() == false) return;
		// TODO: shield and health logic
		this.healthPoints -= damage;
		if (this.isAlive() == false) {
			//Set the correct values for gui indicators
			this.healthPoints = 0;
			this.shieldPoints = 0;
			Score.addScore(ScorePoints);
			if(this instanceof Enemy){ // Add 1 credit for the shop        
				Credits.setCredits(Credits.getCredits() + 1);
			}
			if(GameConfig.DEBUG) System.out.println(this + " ist gestorben. RIP");
			this.die();
		}
	}

	/**
	 * The default way the LivingEntity explodes.
	 * Override this method for a different explosion behaviour.
	 */
	public void explode() {}
	
	/**
	 * The default way the LivingEntity dies.
	 * Override this method for a different death behaviour.
	 */
	public void die() {
		this.explode();
		this.remove();
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
	
	/**
	 * Set the current score points.
	 */
	public void setScore(int s) {
		this.ScorePoints = s;
	}

}
